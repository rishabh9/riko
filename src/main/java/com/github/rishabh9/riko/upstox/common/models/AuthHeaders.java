package com.github.rishabh9.riko.upstox.common.models;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class AuthHeaders {

    private String token;
    private String apiKey;

    AuthHeaders(String token, String apiKey) {
        this.token = token;
        this.apiKey = apiKey;
    }

    public String getToken() {
        return token;
    }

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthHeaders that = (AuthHeaders) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(apiKey, that.apiKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, apiKey);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .add("apiKey", apiKey)
                .toString();
    }
}
