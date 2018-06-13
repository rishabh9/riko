package com.github.rishabh9.upstox.login.models;

public class TokenRequestBuilder {
    private String code;
    private String grantType = "authorization_code";
    private String redirectUri;

    public TokenRequestBuilder withCode(final String code) {
        this.code = code;
        return this;
    }

    public TokenRequestBuilder withGrantType(final String grantType) {
        this.grantType = grantType;
        return this;
    }

    public TokenRequestBuilder withRedirectUri(final String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public TokenRequest build() {
        return new TokenRequest(code, grantType, redirectUri);
    }
}