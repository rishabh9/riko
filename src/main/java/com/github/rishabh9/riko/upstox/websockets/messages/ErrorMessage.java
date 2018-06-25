package com.github.rishabh9.riko.upstox.websockets.messages;

import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;
import com.google.common.base.MoreObjects;
import okhttp3.Response;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Web socket error message.
 */
public class ErrorMessage extends WebSocketMessage {

    private final Throwable throwable;
    private final Response response;

    public ErrorMessage(final WrappedWebSocket sender,
                        final Throwable throwable,
                        @Nullable Response response) {
        super(sender);
        this.throwable = throwable;
        this.response = response;
    }

    /**
     * @return The reason for the error.
     */
    public String getReason() {
        return throwable.getMessage();
    }

    /**
     * @return The associated Throwable.
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * @return The HTTP Response body if it exists.
     * @throws IOException If unable to convert the HTTP response received, to String.
     */
    public Optional<String> getHttpResponse() throws IOException {
        if (null != response && response.body() != null) {
            return Optional.ofNullable(response.body().string());
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(throwable, that.throwable) &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(throwable, response);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("throwable", throwable)
                .add("response", response)
                .add("sender", getSender())
                .toString();
    }
}
