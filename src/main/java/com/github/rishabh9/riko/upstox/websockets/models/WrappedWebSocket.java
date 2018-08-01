/*
 * MIT License
 *
 * Copyright (c) 2018 Rishabh Joshi
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

package com.github.rishabh9.riko.upstox.websockets.models;

import com.google.common.base.MoreObjects;
import okhttp3.WebSocket;
import okio.ByteString;

import java.util.Objects;
import java.util.UUID;

public class WrappedWebSocket {

    private final WebSocket webSocket;
    private final String id;

    public WrappedWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
        this.id = "websocket-" + UUID.randomUUID().toString();
    }

    public String getSourceRequest() {
        return webSocket.toString();
    }

    public boolean sendMessage(String text) {
        return webSocket.send(text);
    }

    public boolean sendMessage(byte[] text) {
        return webSocket.send(ByteString.of(text));
    }

    public boolean close() {
        return webSocket.close(1000, "Closing socket as requested by user/client.");
    }

    public void cancel() {
        webSocket.cancel();
    }

    public Long queueSize() {
        return webSocket.queueSize();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrappedWebSocket that = (WrappedWebSocket) o;
        return Objects.equals(webSocket, that.webSocket) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webSocket, id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
