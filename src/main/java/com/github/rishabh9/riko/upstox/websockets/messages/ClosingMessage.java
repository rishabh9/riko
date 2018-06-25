package com.github.rishabh9.riko.upstox.websockets.messages;

import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;
import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * Web socket closing message.
 */
public class ClosingMessage extends WebSocketMessage {

    private final String reason;
    private final int code;

    public ClosingMessage(final WrappedWebSocket sender,
                          final int code,
                          final String reason) {
        super(sender);
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClosingMessage that = (ClosingMessage) o;
        return code == that.code &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reason, code);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("reason", reason)
                .add("code", code)
                .add("sender", getSender())
                .toString();
    }
}
