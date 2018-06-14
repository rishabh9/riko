package com.github.rishabh9.riko.upstox.models;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Holds the Upstox API Key and Secret.
 */
public class ApiCredentials {

    private String apiKey;
    private String apiSecret;

    public ApiCredentials(@Nonnull String apiKey, @Nonnull String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiCredentials that = (ApiCredentials) o;
        return Objects.equals(apiKey, that.apiKey) &&
                Objects.equals(apiSecret, that.apiSecret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiKey, apiSecret);
    }
}
