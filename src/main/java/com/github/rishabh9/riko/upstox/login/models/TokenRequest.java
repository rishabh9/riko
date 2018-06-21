package com.github.rishabh9.riko.upstox.login.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import java.util.Objects;

public class TokenRequest {

    private final String code;

    @SerializedName("grant_type")
    private final String grantType;

    @SerializedName("redirect_uri")
    private final String redirectUri;

    public String getCode() {
        return code;
    }

    public TokenRequest(@Nonnull String code, @Nonnull String grantType, @Nonnull String redirectUri) {
        this.code = code;
        this.grantType = grantType;
        this.redirectUri = redirectUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenRequest that = (TokenRequest) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(grantType, that.grantType) &&
                Objects.equals(redirectUri, that.redirectUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, grantType, redirectUri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("grantType", grantType)
                .add("redirectUri", redirectUri)
                .toString();
    }
}
