package com.github.rishabh9.riko.upstox.websockets.messages;

import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;

/**
 * The generic web socket message.
 */
public abstract class WebSocketMessage {

    private final WrappedWebSocket sender;

    WebSocketMessage(final WrappedWebSocket sender) {
        this.sender = sender;
    }

    public WrappedWebSocket getSender() {
        return sender;
    }

}
