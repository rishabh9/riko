package com.github.rishabh9.riko.upstox.websockets.messages;

import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;
import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Web socket text message.
 */
public class TextMessage extends WebSocketMessage {

    private final String message;

    public TextMessage(final WrappedWebSocket sender, final String message) {
        super(sender);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextMessage that = (TextMessage) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("message", message)
                .add("sender", getSender())
                .toString();
    }
}
