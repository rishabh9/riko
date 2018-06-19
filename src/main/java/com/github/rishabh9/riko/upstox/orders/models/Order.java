package com.github.rishabh9.riko.upstox.orders.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class Order {

    private String exchange;

    private long token;

    private String symbol;

    private String product;

    @SerializedName("order_type")
    private String orderType;

    private String duration;

    private BigDecimal price;

    @SerializedName("trigger_price")
    private BigDecimal triggerPrice;

    private long quantity;

    @SerializedName("disclosed_quantity")
    private long disclosedQuantity;

    @SerializedName("transaction_type")
    private String transactionType;

    @SerializedName("average_price")
    private BigDecimal averagePrice;

    @SerializedName("traded_quantity")
    private long tradedQuantity;

    private String message;

    @SerializedName("exchange_order_id")
    private String exchangeOrderId;

    @SerializedName("parent_order_id")
    private String parentOrderId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("exchange_time")
    private String exchangeTime;

    @SerializedName("time_in_micro")
    private String timeInMicro;

    private String status;

    @SerializedName("is_amo")
    private boolean is_amo;

    @SerializedName("valid_date")
    private String validDate;

    @SerializedName("order_request_id")
    private String orderRequestId;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public void setDisclosedQuantity(long disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public long getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(long tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(String exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getTimeInMicro() {
        return timeInMicro;
    }

    public void setTimeInMicro(String timeInMicro) {
        this.timeInMicro = timeInMicro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIs_amo() {
        return is_amo;
    }

    public void setIs_amo(boolean is_amo) {
        this.is_amo = is_amo;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getOrderRequestId() {
        return orderRequestId;
    }

    public void setOrderRequestId(String orderRequestId) {
        this.orderRequestId = orderRequestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return token == order.token &&
                quantity == order.quantity &&
                disclosedQuantity == order.disclosedQuantity &&
                tradedQuantity == order.tradedQuantity &&
                is_amo == order.is_amo &&
                Objects.equals(exchange, order.exchange) &&
                Objects.equals(symbol, order.symbol) &&
                Objects.equals(product, order.product) &&
                Objects.equals(orderType, order.orderType) &&
                Objects.equals(duration, order.duration) &&
                Objects.equals(price, order.price) &&
                Objects.equals(triggerPrice, order.triggerPrice) &&
                Objects.equals(transactionType, order.transactionType) &&
                Objects.equals(averagePrice, order.averagePrice) &&
                Objects.equals(message, order.message) &&
                Objects.equals(exchangeOrderId, order.exchangeOrderId) &&
                Objects.equals(parentOrderId, order.parentOrderId) &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(exchangeTime, order.exchangeTime) &&
                Objects.equals(timeInMicro, order.timeInMicro) &&
                Objects.equals(status, order.status) &&
                Objects.equals(validDate, order.validDate) &&
                Objects.equals(orderRequestId, order.orderRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, token, symbol, product, orderType, duration, price, triggerPrice,
                quantity, disclosedQuantity, transactionType, averagePrice, tradedQuantity, message,
                exchangeOrderId, parentOrderId, orderId, exchangeTime, timeInMicro, status, is_amo, validDate,
                orderRequestId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("exchange", exchange)
                .add("token", token)
                .add("symbol", symbol)
                .add("product", product)
                .add("orderType", orderType)
                .add("duration", duration)
                .add("price", price)
                .add("triggerPrice", triggerPrice)
                .add("quantity", quantity)
                .add("disclosedQuantity", disclosedQuantity)
                .add("transactionType", transactionType)
                .add("averagePrice", averagePrice)
                .add("tradedQuantity", tradedQuantity)
                .add("message", message)
                .add("exchangeOrderId", exchangeOrderId)
                .add("parentOrderId", parentOrderId)
                .add("orderId", orderId)
                .add("exchangeTime", exchangeTime)
                .add("timeInMicro", timeInMicro)
                .add("status", status)
                .add("is_amo", is_amo)
                .add("validDate", validDate)
                .add("orderRequestId", orderRequestId)
                .toString();
    }
}
