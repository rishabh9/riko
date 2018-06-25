package com.github.rishabh9.riko.upstox.websockets.exceptions;

/**
 * Thrown when the message subscribers are really slow in
 * receiving the messages.
 */
public class SlowSubscriberException extends RuntimeException {

    public SlowSubscriberException(String message) {
        super(message);
    }

    public SlowSubscriberException(String message, Throwable cause) {
        super(message, cause);
    }
}
