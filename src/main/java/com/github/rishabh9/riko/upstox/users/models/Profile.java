package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Profile {

    @SerializedName("client_id")
    private String clientId;
    private String name;
    private String email;
    private String phone;
    @SerializedName("exchanges_enabled")
    private List<String> exchangesEnabled;
    @SerializedName("products_enabled")
    private List<String> productsEnabled;

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

    public List<String> getExchangesEnabled() {
        return exchangesEnabled;
    }

    public void setExchangesEnabled(List<String> exchangesEnabled) {
        this.exchangesEnabled = exchangesEnabled;
    }

    public List<String> getProductsEnabled() {
        return productsEnabled;
    }

    public void setProductsEnabled(List<String> productsEnabled) {
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
