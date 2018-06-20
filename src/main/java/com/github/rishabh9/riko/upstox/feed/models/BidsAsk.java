package com.github.rishabh9.riko.upstox.feed.models;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.Objects;

public class BidsAsk {

    private long quantity;
    private BigDecimal price;
    private long orders;

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getOrders() {
        return orders;
    }

    public void setOrders(long orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidsAsk bidsAsk = (BidsAsk) o;
        return quantity == bidsAsk.quantity &&
                orders == bidsAsk.orders &&
                Objects.equals(price, bidsAsk.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price, orders);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("quantity", quantity)
                .add("price", price)
                .add("orders", orders)
                .toString();
    }
}
