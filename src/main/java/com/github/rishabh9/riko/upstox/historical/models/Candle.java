package com.github.rishabh9.riko.upstox.historical.models;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * OHLC candle.
 */
public class Candle {

    private Long timestamp;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private Long volume;

    private BigDecimal cp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public BigDecimal getCp() {
        return cp;
    }

    public void setCp(BigDecimal cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candle candle = (Candle) o;
        return Objects.equals(timestamp, candle.timestamp) &&
                Objects.equals(open, candle.open) &&
                Objects.equals(high, candle.high) &&
                Objects.equals(low, candle.low) &&
                Objects.equals(close, candle.close) &&
                Objects.equals(volume, candle.volume) &&
                Objects.equals(cp, candle.cp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, open, high, low, close, volume, cp);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("timestamp", timestamp)
                .add("open", open)
                .add("high", high)
                .add("low", low)
                .add("close", close)
                .add("volume", volume)
                .add("cp", cp)
                .toString();
    }
}
