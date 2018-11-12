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

public class Order {

    private String exchange;

    private Long token;

    private String symbol;

    private String product;

    @SerializedName("order_type")
    private String orderType;

    private String duration;

    private Double price;

    @SerializedName("trigger_price")
    private Double triggerPrice;

    private Long quantity;

    @SerializedName("disclosed_quantity")
    private Long disclosedQuantity;

    @SerializedName("transaction_type")
    private String transactionType;

    @SerializedName("average_price")
    private Double averagePrice;

    @SerializedName("traded_quantity")
    private Long tradedQuantity;

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

    @SerializedName("fill_leg")
    private String fillLeg;

    private String report;

    private String text;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public void setDisclosedQuantity(Long disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Long getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(Long tradedQuantity) {
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

    public String getFillLeg() {
        return fillLeg;
    }

    public void setFillLeg(String fillLeg) {
        this.fillLeg = fillLeg;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return is_amo == order.is_amo &&
                Objects.equals(exchange, order.exchange) &&
                Objects.equals(token, order.token) &&
                Objects.equals(symbol, order.symbol) &&
                Objects.equals(product, order.product) &&
                Objects.equals(orderType, order.orderType) &&
                Objects.equals(duration, order.duration) &&
                Objects.equals(price, order.price) &&
                Objects.equals(triggerPrice, order.triggerPrice) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(disclosedQuantity, order.disclosedQuantity) &&
                Objects.equals(transactionType, order.transactionType) &&
                Objects.equals(averagePrice, order.averagePrice) &&
                Objects.equals(tradedQuantity, order.tradedQuantity) &&
                Objects.equals(message, order.message) &&
                Objects.equals(exchangeOrderId, order.exchangeOrderId) &&
                Objects.equals(parentOrderId, order.parentOrderId) &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(exchangeTime, order.exchangeTime) &&
                Objects.equals(timeInMicro, order.timeInMicro) &&
                Objects.equals(status, order.status) &&
                Objects.equals(validDate, order.validDate) &&
                Objects.equals(orderRequestId, order.orderRequestId) &&
                Objects.equals(fillLeg, order.fillLeg) &&
                Objects.equals(report, order.report) &&
                Objects.equals(text, order.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, token, symbol, product, orderType,
                duration, price, triggerPrice, quantity, disclosedQuantity,
                transactionType, averagePrice, tradedQuantity, message,
                exchangeOrderId, parentOrderId, orderId, exchangeTime,
                timeInMicro, status, is_amo, validDate, orderRequestId,
                fillLeg, report, text);
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
                .add("fillLeg", fillLeg)
                .add("report", report)
                .add("text", text)
                .toString();
    }
}
