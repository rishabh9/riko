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

package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Position {

    private String exchange;

    private String product;

    private String symbol;

    private Long token;

    @SerializedName("buy_amount")
    private Double buyAmount;

    @SerializedName("sell_amount")
    private Double sellAmount;

    @SerializedName("buy_quantity")
    private Long buyQuantity;

    @SerializedName("sell_quantity")
    private Long sellQuantity;

    @SerializedName("cf_buy_amount")
    private Double cfBuyAmount;

    @SerializedName("cf_sell_amount")
    private Double cfSellAmount;

    @SerializedName("cf_buy_quantity")
    private Long cfBuyQuantity;

    @SerializedName("cf_sell_quantity")
    private Long cfSellQuantity;

    @SerializedName("avg_buy_price")
    private Double averageBuyPrice;

    @SerializedName("avg_sell_price")
    private Double averageSellPrice;

    @SerializedName("net_quantity")
    private Long netQuantity;

    @SerializedName("close_price")
    private Double closePrice;

    @SerializedName("last_traded_price")
    private Double lastTradedPrice;

    @SerializedName("realized_profit")
    private Double realizedProfit;

    @SerializedName("unrealized_profit")
    private Double unrealizedProfit;

    @SerializedName("cf_avg_price")
    private Double cfAveragePrice;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public Double getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Double buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Double getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(Double sellAmount) {
        this.sellAmount = sellAmount;
    }

    public Long getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Long buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Long getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Long sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getCfBuyAmount() {
        return cfBuyAmount;
    }

    public void setCfBuyAmount(Double cfBuyAmount) {
        this.cfBuyAmount = cfBuyAmount;
    }

    public Double getCfSellAmount() {
        return cfSellAmount;
    }

    public void setCfSellAmount(Double cfSellAmount) {
        this.cfSellAmount = cfSellAmount;
    }

    public Long getCfBuyQuantity() {
        return cfBuyQuantity;
    }

    public void setCfBuyQuantity(Long cfBuyQuantity) {
        this.cfBuyQuantity = cfBuyQuantity;
    }

    public Long getCfSellQuantity() {
        return cfSellQuantity;
    }

    public void setCfSellQuantity(Long cfSellQuantity) {
        this.cfSellQuantity = cfSellQuantity;
    }

    public Double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(Double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public Double getAverageSellPrice() {
        return averageSellPrice;
    }

    public void setAverageSellPrice(Double averageSellPrice) {
        this.averageSellPrice = averageSellPrice;
    }

    public Long getNetQuantity() {
        return netQuantity;
    }

    public void setNetQuantity(Long netQuantity) {
        this.netQuantity = netQuantity;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public void setLastTradedPrice(Double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public Double getRealizedProfit() {
        return realizedProfit;
    }

    public void setRealizedProfit(Double realizedProfit) {
        this.realizedProfit = realizedProfit;
    }

    public Double getUnrealizedProfit() {
        return unrealizedProfit;
    }

    public void setUnrealizedProfit(Double unrealizedProfit) {
        this.unrealizedProfit = unrealizedProfit;
    }

    public Double getCfAveragePrice() {
        return cfAveragePrice;
    }

    public void setCfAveragePrice(Double cfAveragePrice) {
        this.cfAveragePrice = cfAveragePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(exchange, position.exchange) &&
                Objects.equals(product, position.product) &&
                Objects.equals(symbol, position.symbol) &&
                Objects.equals(token, position.token) &&
                Objects.equals(buyAmount, position.buyAmount) &&
                Objects.equals(sellAmount, position.sellAmount) &&
                Objects.equals(buyQuantity, position.buyQuantity) &&
                Objects.equals(sellQuantity, position.sellQuantity) &&
                Objects.equals(cfBuyAmount, position.cfBuyAmount) &&
                Objects.equals(cfSellAmount, position.cfSellAmount) &&
                Objects.equals(cfBuyQuantity, position.cfBuyQuantity) &&
                Objects.equals(cfSellQuantity, position.cfSellQuantity) &&
                Objects.equals(averageBuyPrice, position.averageBuyPrice) &&
                Objects.equals(averageSellPrice, position.averageSellPrice) &&
                Objects.equals(netQuantity, position.netQuantity) &&
                Objects.equals(closePrice, position.closePrice) &&
                Objects.equals(lastTradedPrice, position.lastTradedPrice) &&
                Objects.equals(realizedProfit, position.realizedProfit) &&
                Objects.equals(unrealizedProfit, position.unrealizedProfit) &&
                Objects.equals(cfAveragePrice, position.cfAveragePrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(exchange, product, symbol, token, buyAmount, sellAmount, buyQuantity,
                sellQuantity, cfBuyAmount, cfSellAmount, cfBuyQuantity, cfSellQuantity, averageBuyPrice,
                averageSellPrice, netQuantity, closePrice, lastTradedPrice, realizedProfit, unrealizedProfit,
                cfAveragePrice);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("exchange", exchange)
                .add("product", product)
                .add("symbol", symbol)
                .add("token", token)
                .add("buyAmount", buyAmount)
                .add("sellAmount", sellAmount)
                .add("buyQuantity", buyQuantity)
                .add("sellQuantity", sellQuantity)
                .add("cfBuyAmount", cfBuyAmount)
                .add("cfSellAmount", cfSellAmount)
                .add("cfBuyQuantity", cfBuyQuantity)
                .add("cfSellQuantity", cfSellQuantity)
                .add("averageBuyPrice", averageBuyPrice)
                .add("averageSellPrice", averageSellPrice)
                .add("netQuantity", netQuantity)
                .add("closePrice", closePrice)
                .add("lastTradedPrice", lastTradedPrice)
                .add("realizedProfit", realizedProfit)
                .add("unrealizedProfit", unrealizedProfit)
                .add("cfAveragePrice", cfAveragePrice)
                .toString();
    }
}
