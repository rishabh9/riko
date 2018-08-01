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

package com.github.rishabh9.riko.upstox.common.models;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * The wrapper for the response returned by the Upstox API.
 *
 * @param <T> The type of data returned in the response.
 */
public class UpstoxResponse<T> {

    private int code;
    private String status;
    private String timestamp;
    private String message;
    private T data;

    /**
     * @return HTTP response code.
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code HTTP response code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return HTTP status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status HTTP status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Timestamp of the response in ISO format.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp Timestamp of the response in ISO format.
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The actual payload.
     */
    public T getData() {
        return data;
    }

    /**
     * @param data The actual payload.
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpstoxResponse<?> upstoxResponse = (UpstoxResponse<?>) o;
        return code == upstoxResponse.code &&
                Objects.equals(status, upstoxResponse.status) &&
                Objects.equals(timestamp, upstoxResponse.timestamp) &&
                Objects.equals(message, upstoxResponse.message) &&
                Objects.equals(data, upstoxResponse.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, status, timestamp, message, data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("status", status)
                .add("timestamp", timestamp)
                .add("message", message)
                .add("data", data)
                .toString();
    }
}
