/*
 * MIT License
 *
 * Copyright (c) 2019 Rishabh Joshi
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
