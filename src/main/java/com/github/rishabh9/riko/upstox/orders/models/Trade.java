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

package com.github.rishabh9.riko.upstox.orders.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class Trade {

    private String exchange;

    private Long token;

    private String symbol;

    private String product;

    @SerializedName("order_type")
    private String orderType;

    @SerializedName("transaction_type")
    private String transactionType;

    @SerializedName("traded_quantity")
    private Long tradedQuantity;

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
    private Long tradeId;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
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

    public Long getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(Long tradedQuantity) {
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

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(exchange, trade.exchange) &&
                Objects.equals(token, trade.token) &&
                Objects.equals(symbol, trade.symbol) &&
                Objects.equals(product, trade.product) &&
                Objects.equals(orderType, trade.orderType) &&
                Objects.equals(transactionType, trade.transactionType) &&
                Objects.equals(tradedQuantity, trade.tradedQuantity) &&
                Objects.equals(exchangeOrderId, trade.exchangeOrderId) &&
                Objects.equals(orderId, trade.orderId) &&
                Objects.equals(exchangeTime, trade.exchangeTime) &&
                Objects.equals(timeInMicro, trade.timeInMicro) &&
                Objects.equals(tradedPrice, trade.tradedPrice) &&
                Objects.equals(tradeId, trade.tradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, token, symbol, product, orderType,
                transactionType, tradedQuantity, exchangeOrderId, orderId,
                exchangeTime, timeInMicro, tradedPrice, tradeId);
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
