package com.github.rishabh9.riko.upstox.common.models;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Model for preparing the authentication/authorization headers for API calls.
 */
public class AuthHeaders {

    private String token;
    private String apiKey;

    public AuthHeaders(@Nonnull final String token,
                       @Nonnull final String apiKey) {

        this.token = Objects.requireNonNull(token);
        this.apiKey = Objects.requireNonNull(apiKey);
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
}
