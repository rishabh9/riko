package com.github.rishabh9.riko.upstox.common.models;

public class AuthHeadersBuilder {
    private String token;
    private String apiKey;

    public AuthHeadersBuilder withToken(String token) {
        this.token = token;
        return this;
    }

    public AuthHeadersBuilder withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public AuthHeaders build() {
        return new AuthHeaders(token, apiKey);
    }
}