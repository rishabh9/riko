package com.github.rishabh9.riko.upstox.models;

import com.google.common.base.MoreObjects;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UpstoxResponse<T> {

    private int code;
    private String status;
    private OffsetDateTime timestamp;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

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
