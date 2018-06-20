package com.github.rishabh9.riko.upstox.feed.models;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class SubscriptionResponse {

    private boolean success;
    private String exchange;
    private List<String> symbol;
    private String type;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public List<String> getSymbol() {
        return symbol;
    }

    public void setSymbol(List<String> symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionResponse that = (SubscriptionResponse) o;
        return success == that.success &&
                Objects.equals(exchange, that.exchange) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, exchange, symbol, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("success", success)
                .add("exchange", exchange)
                .add("symbol", symbol)
                .add("type", type)
                .toString();
    }
}
