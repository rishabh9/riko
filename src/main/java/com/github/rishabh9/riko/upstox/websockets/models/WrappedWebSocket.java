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
