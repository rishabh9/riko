package com.github.rishabh9.riko.upstox.feed.models;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.Objects;

public class BidsAsk {

    private Long quantity;
    private BigDecimal price;
    private Long orders;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BidsAsk bidsAsk = (BidsAsk) o;
        return Objects.equals(quantity, bidsAsk.quantity) &&
                Objects.equals(price, bidsAsk.price) &&
                Objects.equals(orders, bidsAsk.orders);
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
