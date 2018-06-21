package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class Balance {

    @SerializedName("used_margin")
    private BigDecimal usedMargin;

    @SerializedName("payin_amount")
    private BigDecimal payinAmount;

    @SerializedName("span_margin")
    private BigDecimal spanMargin;

    @SerializedName("adhoc_margin")
    private BigDecimal adhocMargin;

    @SerializedName("notional_cash")
    private BigDecimal notionalCash;

    @SerializedName("available_margin")
    private BigDecimal availableMargin;

    @SerializedName("exposure_margin")
    private BigDecimal exposureMargin;

    public BigDecimal getUsedMargin() {
        return usedMargin;
    }

    public void setUsedMargin(BigDecimal usedMargin) {
        this.usedMargin = usedMargin;
    }

    public BigDecimal getPayinAmount() {
        return payinAmount;
    }

    public void setPayinAmount(BigDecimal payinAmount) {
        this.payinAmount = payinAmount;
    }

    public BigDecimal getSpanMargin() {
        return spanMargin;
    }

    public void setSpanMargin(BigDecimal spanMargin) {
        this.spanMargin = spanMargin;
    }

    public BigDecimal getAdhocMargin() {
        return adhocMargin;
    }

    public void setAdhocMargin(BigDecimal adhocMargin) {
        this.adhocMargin = adhocMargin;
    }

    public BigDecimal getNotionalCash() {
        return notionalCash;
    }

    public void setNotionalCash(BigDecimal notionalCash) {
        this.notionalCash = notionalCash;
    }

    public BigDecimal getAvailableMargin() {
        return availableMargin;
    }

    public void setAvailableMargin(BigDecimal availableMargin) {
        this.availableMargin = availableMargin;
    }

    public BigDecimal getExposureMargin() {
        return exposureMargin;
    }

    public void setExposureMargin(BigDecimal exposureMargin) {
        this.exposureMargin = exposureMargin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(usedMargin, balance.usedMargin) &&
                Objects.equals(payinAmount, balance.payinAmount) &&
                Objects.equals(spanMargin, balance.spanMargin) &&
                Objects.equals(adhocMargin, balance.adhocMargin) &&
                Objects.equals(notionalCash, balance.notionalCash) &&
                Objects.equals(availableMargin, balance.availableMargin) &&
                Objects.equals(exposureMargin, balance.exposureMargin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usedMargin, payinAmount, spanMargin, adhocMargin,
                notionalCash, availableMargin, exposureMargin);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("usedMargin", usedMargin)
                .add("payinAmount", payinAmount)
                .add("spanMargin", spanMargin)
                .add("adhocMargin", adhocMargin)
                .add("notionalCash", notionalCash)
                .add("availableMargin", availableMargin)
                .add("exposureMargin", exposureMargin)
                .toString();
    }
}
