package com.github.rishabh9.riko.upstox.login.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class AccessToken {

    @SerializedName("access_token")
    private String token;

    @SerializedName("expires_in")
    private Long expiresIn;

    @SerializedName("token_type")
    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(type.charAt(0))) {
            type = Character
                    .toString(type.charAt(0))
                    .toUpperCase() + type.substring(1);
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessToken that = (AccessToken) o;
        return Objects.equals(token, that.token) &&
                Objects.equals(expiresIn, that.expiresIn) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expiresIn, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", token)
                .add("expiresIn", expiresIn)
                .add("type", type)
                .toString();
    }
}
