package com.github.rishabh9.riko.exceptions;

public class ConfigurationMissingException extends RuntimeException {
    public ConfigurationMissingException(String message) {
        super(message);
    }

    public ConfigurationMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
