package com.github.rishabh9.riko.upstox.feed.models;

import com.github.rishabh9.riko.upstox.common.converters.NumberString;
import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Feed {

    private Long timestamp;

    private String exchange;

    private String symbol;

    private BigDecimal ltp;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private Long vtt;

    private BigDecimal atp;

    private NumberString oi;

    @SerializedName("spot_price")
    private BigDecimal spotPrice;

    @SerializedName("total_buy_qty")
    private Long totalBuyQuantity;

    @SerializedName("total_sell_qty")
    private Long totalSellQuantity;

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

    private Long ltt;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
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

    public BigDecimal getLtp() {
        return ltp;
    }

    public void setLtp(BigDecimal ltp) {
        this.ltp = ltp;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public Long getVtt() {
        return vtt;
    }

    public void setVtt(Long vtt) {
        this.vtt = vtt;
    }

    public BigDecimal getAtp() {
        return atp;
    }

    public void setAtp(BigDecimal atp) {
        this.atp = atp;
    }

    public NumberString getOi() {
        return oi;
    }

    public void setOi(NumberString oi) {
        this.oi = oi;
    }

    public BigDecimal getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(BigDecimal spotPrice) {
        this.spotPrice = spotPrice;
    }

    public Long getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    public void setTotalBuyQuantity(Long totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public Long getTotalSellQuantity() {
        return totalSellQuantity;
    }

    public void setTotalSellQuantity(Long totalSellQuantity) {
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

    public Long getLtt() {
        return ltt;
    }

    public void setLtt(Long ltt) {
        this.ltt = ltt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return Objects.equals(timestamp, feed.timestamp) &&
                Objects.equals(exchange, feed.exchange) &&
                Objects.equals(symbol, feed.symbol) &&
                Objects.equals(ltp, feed.ltp) &&
                Objects.equals(open, feed.open) &&
                Objects.equals(high, feed.high) &&
                Objects.equals(low, feed.low) &&
                Objects.equals(close, feed.close) &&
                Objects.equals(vtt, feed.vtt) &&
                Objects.equals(atp, feed.atp) &&
                Objects.equals(oi, feed.oi) &&
                Objects.equals(spotPrice, feed.spotPrice) &&
                Objects.equals(totalBuyQuantity, feed.totalBuyQuantity) &&
                Objects.equals(totalSellQuantity, feed.totalSellQuantity) &&
                Objects.equals(lowerCircuit, feed.lowerCircuit) &&
                Objects.equals(upperCircuit, feed.upperCircuit) &&
                Objects.equals(yearlyLow, feed.yearlyLow) &&
                Objects.equals(yearlyHigh, feed.yearlyHigh) &&
                Objects.equals(bids, feed.bids) &&
                Objects.equals(ask, feed.ask) &&
                Objects.equals(ltt, feed.ltt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, exchange, symbol, ltp, open, high, low, close,
                vtt, atp, oi, spotPrice, totalBuyQuantity, totalSellQuantity,
                lowerCircuit, upperCircuit, yearlyLow, yearlyHigh, bids, ask, ltt);
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
