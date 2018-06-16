package com.github.rishabh9.riko.upstox.common.models;

import javax.annotation.Nonnull;

public class ApiCredentialsBuilder {
    private String apiKey;
    private String apiSecret;

    public ApiCredentialsBuilder withApiKey(@Nonnull String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ApiCredentialsBuilder withApiSecret(@Nonnull String apiSecret) {
        this.apiSecret = apiSecret;
        return this;
    }

    public ApiCredentials build() {
        return new ApiCredentials(apiKey, apiSecret);
    }
}