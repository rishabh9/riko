package com.github.rishabh9.riko.upstox.feed.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Feed {

    private long timestamp;

    private String exchange;

    private String symbol;

    private double ltp;

    private double open;

    private double high;

    private double low;

    private double close;

    private double vtt;

    private double atp;

    private String oi;

    @SerializedName("spot_price")
    private BigDecimal spotPrice;

    @SerializedName("total_buy_qty")
    private long totalBuyQuantity;

    @SerializedName("total_sell_qty")
    private long totalSellQuantity;

    @SerializedName("lower_circuit")
    private BigDecimal lowerCircuit;

    @SerializedName("upper_circuit")
    private BigDecimal upperCircuit;

    @SerializedName("yearly_low")
    private BigDecimal yearlyLow;

    @SerializedName("yearly_high")
    private BigDecimal yearlyHigh;

    private List<BidsAsk> bids;

    private List<BidsAsk> ask;

    private long ltt;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public double getLtp() {
        return ltp;
    }

    public void setLtp(double ltp) {
        this.ltp = ltp;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVtt() {
        return vtt;
    }

    public void setVtt(double vtt) {
        this.vtt = vtt;
    }

    public double getAtp() {
        return atp;
    }

    public void setAtp(double atp) {
        this.atp = atp;
    }

    public String getOi() {
        return oi;
    }

    public void setOi(String oi) {
        this.oi = oi;
    }

    public BigDecimal getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(BigDecimal spotPrice) {
        this.spotPrice = spotPrice;
    }

    public long getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    public void setTotalBuyQuantity(long totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public long getTotalSellQuantity() {
        return totalSellQuantity;
    }

    public void setTotalSellQuantity(long totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
    }

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

    public List<BidsAsk> getBids() {
        return bids;
    }

    public void setBids(List<BidsAsk> bids) {
        this.bids = bids;
    }

    public List<BidsAsk> getAsk() {
        return ask;
    }

    public void setAsk(List<BidsAsk> ask) {
        this.ask = ask;
    }

    public long getLtt() {
        return ltt;
    }

    public void setLtt(long ltt) {
        this.ltt = ltt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return timestamp == feed.timestamp &&
                Double.compare(feed.ltp, ltp) == 0 &&
                Double.compare(feed.open, open) == 0 &&
                Double.compare(feed.high, high) == 0 &&
                Double.compare(feed.low, low) == 0 &&
                Double.compare(feed.close, close) == 0 &&
                Double.compare(feed.vtt, vtt) == 0 &&
                Double.compare(feed.atp, atp) == 0 &&
                totalBuyQuantity == feed.totalBuyQuantity &&
                totalSellQuantity == feed.totalSellQuantity &&
                ltt == feed.ltt &&
                Objects.equals(exchange, feed.exchange) &&
                Objects.equals(symbol, feed.symbol) &&
                Objects.equals(oi, feed.oi) &&
                Objects.equals(spotPrice, feed.spotPrice) &&
                Objects.equals(lowerCircuit, feed.lowerCircuit) &&
                Objects.equals(upperCircuit, feed.upperCircuit) &&
                Objects.equals(yearlyLow, feed.yearlyLow) &&
                Objects.equals(yearlyHigh, feed.yearlyHigh) &&
                Objects.equals(bids, feed.bids) &&
                Objects.equals(ask, feed.ask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, exchange, symbol, ltp, open, high, low, close, vtt, atp, oi,
                spotPrice, totalBuyQuantity, totalSellQuantity, lowerCircuit, upperCircuit, yearlyLow,
                yearlyHigh, bids, ask, ltt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("timestamp", timestamp)
                .add("exchange", exchange)
                .add("symbol", symbol)
                .add("ltp", ltp)
                .add("open", open)
                .add("high", high)
                .add("low", low)
                .add("close", close)
                .add("vtt", vtt)
                .add("atp", atp)
                .add("oi", oi)
                .add("spotPrice", spotPrice)
                .add("totalBuyQuantity", totalBuyQuantity)
                .add("totalSellQuantity", totalSellQuantity)
                .add("lowerCircuit", lowerCircuit)
                .add("upperCircuit", upperCircuit)
                .add("yearlyLow", yearlyLow)
                .add("yearlyHigh", yearlyHigh)
                .add("bids", bids)
                .add("ask", ask)
                .add("ltt", ltt)
                .toString();
    }
}
