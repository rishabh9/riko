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
    private long gn;

    @SerializedName("gd")
    private long gd;

    @SerializedName("pn")
    private long pn;

    @SerializedName("pd")
    private long pd;

    @SerializedName("circuit_limit")
    private String circuitLimit;

    private String status;

    @SerializedName("yearly_low")
    private long yearlyLow;

    @SerializedName("yearly_high")
    private long yearlyHigh;

    @SerializedName("symbol")
    private String symbol;

    @SerializedName("lot_size")
    private long lotSize;

    private String exchange;
    @SerializedName("closing_price")
    private BigDecimal closingPrice;

    @SerializedName("open_interest")
    private String openInterest;

    @SerializedName("tick_size")
    private double tickSize;

    @SerializedName("name")
    private String name;

    @SerializedName("token")
    private long token;

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

    public long getGn() {
        return gn;
    }

    public void setGn(long gn) {
        this.gn = gn;
    }

    public long getGd() {
        return gd;
    }

    public void setGd(long gd) {
        this.gd = gd;
    }

    public long getPn() {
        return pn;
    }

    public void setPn(long pn) {
        this.pn = pn;
    }

    public long getPd() {
        return pd;
    }

    public void setPd(long pd) {
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

    public long getYearlyLow() {
        return yearlyLow;
    }

    public void setYearlyLow(long yearlyLow) {
        this.yearlyLow = yearlyLow;
    }

    public long getYearlyHigh() {
        return yearlyHigh;
    }

    public void setYearlyHigh(long yearlyHigh) {
        this.yearlyHigh = yearlyHigh;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getLotSize() {
        return lotSize;
    }

    public void setLotSize(long lotSize) {
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

    public double getTickSize() {
        return tickSize;
    }

    public void setTickSize(double tickSize) {
        this.tickSize = tickSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
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
        return gn == contract.gn &&
                gd == contract.gd &&
                pn == contract.pn &&
                pd == contract.pd &&
                yearlyLow == contract.yearlyLow &&
                yearlyHigh == contract.yearlyHigh &&
                lotSize == contract.lotSize &&
                tickSize == contract.tickSize &&
                token == contract.token &&
                Objects.equals(lowerCircuit, contract.lowerCircuit) &&
                Objects.equals(upperCircuit, contract.upperCircuit) &&
                Objects.equals(instrumentName, contract.instrumentName) &&
                Objects.equals(circuitLimit, contract.circuitLimit) &&
                Objects.equals(status, contract.status) &&
                Objects.equals(symbol, contract.symbol) &&
                Objects.equals(exchange, contract.exchange) &&
                Objects.equals(closingPrice, contract.closingPrice) &&
                Objects.equals(openInterest, contract.openInterest) &&
                Objects.equals(name, contract.name) &&
                Objects.equals(isin, contract.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerCircuit, upperCircuit, instrumentName, gn, gd, pn, pd, circuitLimit, status,
                yearlyLow, yearlyHigh, symbol, lotSize, exchange, closingPrice, openInterest, tickSize, name,
                token, isin);
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
