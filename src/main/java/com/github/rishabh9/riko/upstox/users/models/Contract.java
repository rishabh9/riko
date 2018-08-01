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

import java.math.BigDecimal;
import java.util.Objects;

public class Contract {

    @SerializedName("lower_circuit")
    private BigDecimal lowerCircuit;

    @SerializedName("upper_circuit")
    private BigDecimal upperCircuit;

    @SerializedName("instrument_name")
    private String instrumentName;

    @SerializedName("gn")
    private Long gn;

    @SerializedName("gd")
    private Long gd;

    @SerializedName("pn")
    private Long pn;

    @SerializedName("pd")
    private Long pd;

    @SerializedName("circuit_limit")
    private String circuitLimit;

    private String status;

    @SerializedName("yearly_low")
    private BigDecimal yearlyLow;

    @SerializedName("yearly_high")
    private BigDecimal yearlyHigh;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("lot_size")
    private Long lotSize;

    private String exchange;

    @SerializedName("closing_price")
    private BigDecimal closingPrice;

    @SerializedName("open_interest")
    private String openInterest;

    @SerializedName("tick_size")
    private Long tickSize;

    @SerializedName("name")
    private String name;

    @SerializedName("token")
    private Long token;

    @SerializedName("isin")
    private String isin;

    public BigDecimal getLowerCircuit() {
        return lowerCircuit;
    }

    public void setLowerCircuit(BigDecimal lowerCircuit) {
        this.lowerCircuit = lowerCircuit;
    }

    public BigDecimal getUpperCircuit() {
        return upperCircuit;
    }

    public void setUpperCircuit(BigDecimal upperCircuit) {
        this.upperCircuit = upperCircuit;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public Long getGn() {
        return gn;
    }

    public void setGn(Long gn) {
        this.gn = gn;
    }

    public Long getGd() {
        return gd;
    }

    public void setGd(Long gd) {
        this.gd = gd;
    }

    public Long getPn() {
        return pn;
    }

    public void setPn(Long pn) {
        this.pn = pn;
    }

    public Long getPd() {
        return pd;
    }

    public void setPd(Long pd) {
        this.pd = pd;
    }

    public String getCircuitLimit() {
        return circuitLimit;
    }

    public void setCircuitLimit(String circuitLimit) {
        this.circuitLimit = circuitLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getYearlyLow() {
        return yearlyLow;
    }

    public void setYearlyLow(BigDecimal yearlyLow) {
        this.yearlyLow = yearlyLow;
    }

    public BigDecimal getYearlyHigh() {
        return yearlyHigh;
    }

    public void setYearlyHigh(BigDecimal yearlyHigh) {
        this.yearlyHigh = yearlyHigh;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getLotSize() {
        return lotSize;
    }

    public void setLotSize(Long lotSize) {
        this.lotSize = lotSize;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public String getOpenInterest() {
        return openInterest;
    }

    public void setOpenInterest(String openInterest) {
        this.openInterest = openInterest;
    }

    public Long getTickSize() {
        return tickSize;
    }

    public void setTickSize(Long tickSize) {
        this.tickSize = tickSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(lowerCircuit, contract.lowerCircuit) &&
                Objects.equals(upperCircuit, contract.upperCircuit) &&
                Objects.equals(instrumentName, contract.instrumentName) &&
                Objects.equals(gn, contract.gn) &&
                Objects.equals(gd, contract.gd) &&
                Objects.equals(pn, contract.pn) &&
                Objects.equals(pd, contract.pd) &&
                Objects.equals(circuitLimit, contract.circuitLimit) &&
                Objects.equals(status, contract.status) &&
                Objects.equals(yearlyLow, contract.yearlyLow) &&
                Objects.equals(yearlyHigh, contract.yearlyHigh) &&
                Objects.equals(symbol, contract.symbol) &&
                Objects.equals(lotSize, contract.lotSize) &&
                Objects.equals(exchange, contract.exchange) &&
                Objects.equals(closingPrice, contract.closingPrice) &&
                Objects.equals(openInterest, contract.openInterest) &&
                Objects.equals(tickSize, contract.tickSize) &&
                Objects.equals(name, contract.name) &&
                Objects.equals(token, contract.token) &&
                Objects.equals(isin, contract.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerCircuit, upperCircuit, instrumentName, gn, gd, pn, pd,
                circuitLimit, status, yearlyLow, yearlyHigh, symbol, lotSize, exchange,
                closingPrice, openInterest, tickSize, name, token, isin);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lowerCircuit", lowerCircuit)
                .add("upperCircuit", upperCircuit)
                .add("instrumentName", instrumentName)
                .add("gn", gn)
                .add("gd", gd)
                .add("pn", pn)
                .add("pd", pd)
                .add("circuitLimit", circuitLimit)
                .add("status", status)
                .add("yearlyLow", yearlyLow)
                .add("yearlyHigh", yearlyHigh)
                .add("symbol", symbol)
                .add("lotSize", lotSize)
                .add("exchange", exchange)
                .add("closingPrice", closingPrice)
                .add("openInterest", openInterest)
                .add("tickSize", tickSize)
                .add("name", name)
                .add("token", token)
                .add("isin", isin)
                .toString();
    }
}
