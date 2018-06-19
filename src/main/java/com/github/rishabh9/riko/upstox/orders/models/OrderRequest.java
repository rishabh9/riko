package com.github.rishabh9.riko.upstox.orders.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderRequest {

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("transaction_type")
    private String transactionType;

    private String exchange;

    private String symbol;

    private long quantity;

    @SerializedName("order_type")
    private String orderType;

    private String product;

    private BigDecimal price;

    @SerializedName("trigger_price")
    private BigDecimal triggerPrice;

    @SerializedName("disclosed_quantity")
    private long disclosedQuantity;

    private String duration;

    @SerializedName("is_amo")
    private boolean is_amo;

    private BigDecimal stoploss;

    private BigDecimal squareoff;

    @SerializedName("trailing_ticks")
    private double tralingTicks;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(BigDecimal triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public long getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public void setDisclosedQuantity(long disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isIs_amo() {
        return is_amo;
    }

    public void setIs_amo(boolean is_amo) {
        this.is_amo = is_amo;
    }

    public BigDecimal getStoploss() {
        return stoploss;
    }

    public void setStoploss(BigDecimal stoploss) {
        this.stoploss = stoploss;
    }

    public BigDecimal getSquareoff() {
        return squareoff;
    }

    public void setSquareoff(BigDecimal squareoff) {
        this.squareoff = squareoff;
    }

    public double getTralingTicks() {
        return tralingTicks;
    }

    public void setTralingTicks(double tralingTicks) {
        this.tralingTicks = tralingTicks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequest that = (OrderRequest) o;
        return quantity == that.quantity &&
                disclosedQuantity == that.disclosedQuantity &&
                is_amo == that.is_amo &&
                Double.compare(that.tralingTicks, tralingTicks) == 0 &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(exchange, that.exchange) &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(orderType, that.orderType) &&
                Objects.equals(product, that.product) &&
                Objects.equals(price, that.price) &&
                Objects.equals(triggerPrice, that.triggerPrice) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(stoploss, that.stoploss) &&
                Objects.equals(squareoff, that.squareoff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, transactionType, exchange, symbol, quantity, orderType, product,
                price, triggerPrice, disclosedQuantity, duration, is_amo, stoploss, squareoff, tralingTicks);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderId", orderId)
                .add("transactionType", transactionType)
                .add("exchange", exchange)
                .add("symbol", symbol)
                .add("quantity", quantity)
                .add("orderType", orderType)
                .add("product", product)
                .add("price", price)
                .add("triggerPrice", triggerPrice)
                .add("disclosedQuantity", disclosedQuantity)
                .add("duration", duration)
                .add("is_amo", is_amo)
                .add("stoploss", stoploss)
                .add("squareoff", squareoff)
                .add("tralingTicks", tralingTicks)
                .toString();
    }
}
