/*
 * MIT License
 *
 * Copyright (c) 2019 Rishabh Joshi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.rishabh9.riko.upstox.websockets;

import com.github.rishabh9.riko.upstox.websockets.exceptions.SlowSubscriberException;
import com.github.rishabh9.riko.upstox.websockets.messages.*;
import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * The web socket listener.
 * Responsible to receive the messages over the socket
 * and to publish them over to the subscribers.
 */
public final class MessageListener
        extends okhttp3.WebSocketListener
        implements Flow.Publisher<WebSocketMessage> {

    private static final Logger log = LogManager.getLogger(MessageListener.class);

    private static final int MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE = 3;
    private final SubmissionPublisher<WebSocketMessage> publisher;

    public MessageListener(@Nonnull List<MessageSubscriber> subscribers) {
        this.publisher =
                new SubmissionPublisher<>(
                        Executors.newWorkStealingPool(),
                        Flow.defaultBufferSize());

        Objects.requireNonNull(subscribers).forEach(publisher::subscribe);
    }

    private void publishMessage(WebSocketMessage message) {
        int lag = publisher.offer(message,
                MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE, TimeUnit.SECONDS,
                (subscriber, msg) -> {
                    // inform subscriber that it isn't receiving the messages.
                    // We can try again - but only once.
                    // But we won't try again, as the subscriber should be quick.
                    final String errorMsg = "Subscriber "
                            + ((MessageSubscriber) subscriber).getName()
                            + " is slow in receiving messages. Dropping message: "
                            + msg.toString();
                    subscriber.onError(new SlowSubscriberException(errorMsg));
                    return false; // If we do not want to re-try once again.
                    // return true; // If we want to re-try once
                });
        if (lag < 0) {
            log.warn("Dropping {} messages", -lag);
        } else {
            log.debug("The slowest consumer has {} messages in total to be picked up.", lag);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        publishMessage(
                new ConnectedMessage(
                        new WrappedWebSocket(webSocket), response.message()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMessage(WebSocket webSocket, String message) {
        super.onMessage(webSocket, message);
        publishMessage(
                new TextMessage(
                        new WrappedWebSocket(webSocket), message));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        publishMessage(
                new BinaryMessage(
                        new WrappedWebSocket(webSocket), bytes));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        publishMessage(
                new ClosingMessage(
                        new WrappedWebSocket(webSocket), code, reason));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        publishMessage(
                new DisconnectedMessage(
                        new WrappedWebSocket(webSocket), code, reason));
        publisher.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        publishMessage(
                new ErrorMessage(
                        new WrappedWebSocket(webSocket), t, response));
        publisher.closeExceptionally(t);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribe(Flow.Subscriber<? super WebSocketMessage> subscriber) {
        publisher.subscribe(subscriber);
    }
}
