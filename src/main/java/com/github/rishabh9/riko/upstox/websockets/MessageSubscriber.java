package com.github.rishabh9.riko.upstox.websockets;

import com.github.rishabh9.riko.upstox.websockets.messages.WebSocketMessage;

import java.util.UUID;
import java.util.concurrent.Flow;

/**
 * By implementing this interface you ensure that each subscriber is
 * identifiable during the debugging process.
 */
public interface MessageSubscriber extends Flow.Subscriber<WebSocketMessage> {

    /**
     * @return Unique name to identify this subscriber
     */
    default String getName() {
        return "websocket-subscriber-" + UUID.randomUUID().toString();
    }
}
