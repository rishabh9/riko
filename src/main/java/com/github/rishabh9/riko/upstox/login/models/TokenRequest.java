/*
 * MIT License
 *
 * Copyright (c) 2019 Rishabh Joshi
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

    public TokenRequest(@Nonnull final String code,
                        @Nonnull final String grantType,
                        @Nonnull final String redirectUri) {

        this.code = Objects.requireNonNull(code);
        this.grantType = Objects.requireNonNull(grantType);
        this.redirectUri = Objects.requireNonNull(redirectUri);
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
