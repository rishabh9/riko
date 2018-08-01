/*
 * MIT License
 *
 * Copyright (c) 2018 Rishabh Joshi
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

package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Set;

public class Profile {

    @SerializedName("client_id")
    private String clientId;

    private String name;

    private String email;

    private String phone;

    @SerializedName("exchanges_enabled")
    private Set<String> exchangesEnabled;

    @SerializedName("products_enabled")
    private Set<String> productsEnabled;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<String> getExchangesEnabled() {
        return exchangesEnabled;
    }

    public void setExchangesEnabled(Set<String> exchangesEnabled) {
        this.exchangesEnabled = exchangesEnabled;
    }

    public Set<String> getProductsEnabled() {
        return productsEnabled;
    }

    public void setProductsEnabled(Set<String> productsEnabled) {
        this.productsEnabled = productsEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(clientId, profile.clientId) &&
                Objects.equals(name, profile.name) &&
                Objects.equals(email, profile.email) &&
                Objects.equals(phone, profile.phone) &&
                Objects.equals(exchangesEnabled, profile.exchangesEnabled) &&
                Objects.equals(productsEnabled, profile.productsEnabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, name, email, phone, exchangesEnabled, productsEnabled);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clientId", clientId)
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("exchangesEnabled", exchangesEnabled)
                .add("productsEnabled", productsEnabled)
                .toString();
    }
}
