package com.github.rishabh9.riko.upstox.orders.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class Trade {

    private String exchange;

    private long token;

    private String symbol;

    private String product;

    @SerializedName("order_type")
    private String orderType;

    @SerializedName("transaction_type")
    private String transactionType;

    @SerializedName("traded_quantity")
    private long tradedQuantity;

    @SerializedName("exchange_order_id")
    private String exchangeOrderId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("exchange_time")
    private String exchangeTime;

    @SerializedName("time_in_micro")
    private String timeInMicro;

    @SerializedName("traded_price")
    private BigDecimal tradedPrice;

    @SerializedName("trade_id")
    private long tradeId;

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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(long tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }

    public String getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(String exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
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

    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }

    public void setTradedPrice(BigDecimal tradedPrice) {
        this.tradedPrice = tradedPrice;
    }

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return token == trade.token &&
                tradedQuantity == trade.tradedQuantity &&
                tradeId == trade.tradeId &&
                Objects.equals(exchange, trade.exchange) &&
                Objects.equals(symbol, trade.symbol) &&
                Objects.equals(product, trade.product) &&
                Objects.equals(orderType, trade.orderType) &&
                Objects.equals(transactionType, trade.transactionType) &&
                Objects.equals(exchangeOrderId, trade.exchangeOrderId) &&
                Objects.equals(orderId, trade.orderId) &&
                Objects.equals(exchangeTime, trade.exchangeTime) &&
                Objects.equals(timeInMicro, trade.timeInMicro) &&
                Objects.equals(tradedPrice, trade.tradedPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, token, symbol, product, orderType, transactionType, tradedQuantity,
                exchangeOrderId, orderId, exchangeTime, timeInMicro, tradedPrice, tradeId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("exchange", exchange)
                .add("token", token)
                .add("symbol", symbol)
                .add("product", product)
                .add("orderType", orderType)
                .add("transactionType", transactionType)
                .add("tradedQuantity", tradedQuantity)
                .add("exchangeOrderId", exchangeOrderId)
                .add("orderId", orderId)
                .add("exchangeTime", exchangeTime)
                .add("timeInMicro", timeInMicro)
                .add("tradedPrice", tradedPrice)
                .add("tradeId", tradeId)
                .toString();
    }
}
