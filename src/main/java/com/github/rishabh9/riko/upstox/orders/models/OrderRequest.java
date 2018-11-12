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

import java.util.Objects;

public class OrderRequest {

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("transaction_type")
    private String transactionType;

    private String exchange;

    private String symbol;

    private Long quantity;

    @SerializedName("order_type")
    private String orderType;

    private String product;

    private Double price;

    @SerializedName("trigger_price")
    private Double triggerPrice;

    @SerializedName("disclosed_quantity")
    private Long disclosedQuantity;

    private String duration;

    @SerializedName("is_amo")
    private boolean is_amo;

    private Double stoploss;

    private Double squareoff;

    @SerializedName("trailing_ticks")
    private Double tralingTicks;

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(Double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public Long getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public void setDisclosedQuantity(Long disclosedQuantity) {
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

    public Double getStoploss() {
        return stoploss;
    }

    public void setStoploss(Double stoploss) {
        this.stoploss = stoploss;
    }

    public Double getSquareoff() {
        return squareoff;
    }

    public void setSquareoff(Double squareoff) {
        this.squareoff = squareoff;
    }

    public Double getTralingTicks() {
        return tralingTicks;
    }

    public void setTralingTicks(Double tralingTicks) {
        this.tralingTicks = tralingTicks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequest request = (OrderRequest) o;
        return is_amo == request.is_amo &&
                Objects.equals(orderId, request.orderId) &&
                Objects.equals(transactionType, request.transactionType) &&
                Objects.equals(exchange, request.exchange) &&
                Objects.equals(symbol, request.symbol) &&
                Objects.equals(quantity, request.quantity) &&
                Objects.equals(orderType, request.orderType) &&
                Objects.equals(product, request.product) &&
                Objects.equals(price, request.price) &&
                Objects.equals(triggerPrice, request.triggerPrice) &&
                Objects.equals(disclosedQuantity, request.disclosedQuantity) &&
                Objects.equals(duration, request.duration) &&
                Objects.equals(stoploss, request.stoploss) &&
                Objects.equals(squareoff, request.squareoff) &&
                Objects.equals(tralingTicks, request.tralingTicks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, transactionType, exchange, symbol, quantity,
                orderType, product, price, triggerPrice, disclosedQuantity, duration,
                is_amo, stoploss, squareoff, tralingTicks);
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
