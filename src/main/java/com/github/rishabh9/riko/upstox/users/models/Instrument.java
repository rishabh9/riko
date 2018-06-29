package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Instrument {

    private String exchange;
    private String symbol;
    private Long token;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return Objects.equals(exchange, that.exchange) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, symbol, token);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("exchange", exchange)
                .add("symbol", symbol)
                .add("token", token)
                .toString();
    }
}
