package com.github.rishabh9.riko.upstox.websockets.exceptions;

/**
 * Thrown when making a web request to Upstox fails.
 */
public class WebRequestException extends RuntimeException {
    public WebRequestException(String message) {
        super(message);
    }

    public WebRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
