package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Balance {

    @SerializedName("used_margin")
    private double usedMargin;

    @SerializedName("payin_amount")
    private double payinAmount;

    @SerializedName("span_margin")
    private double spanMargin;

    @SerializedName("adhoc_margin")
    private double adhocMargin;

    @SerializedName("notional_cash")
    private double notionalCash;

    @SerializedName("available_margin")
    private double availableMargin;

    @SerializedName("exposure_margin")
    private double exposureMargin;

    public double getUsedMargin() {
        return usedMargin;
    }

    public void setUsedMargin(double usedMargin) {
        this.usedMargin = usedMargin;
    }

    public double getPayinAmount() {
        return payinAmount;
    }

    public void setPayinAmount(double payinAmount) {
        this.payinAmount = payinAmount;
    }

    public double getSpanMargin() {
        return spanMargin;
    }

    public void setSpanMargin(double spanMargin) {
        this.spanMargin = spanMargin;
    }

    public double getAdhocMargin() {
        return adhocMargin;
    }

    public void setAdhocMargin(double adhocMargin) {
        this.adhocMargin = adhocMargin;
    }

    public double getNotionalCash() {
        return notionalCash;
    }

    public void setNotionalCash(double notionalCash) {
        this.notionalCash = notionalCash;
    }

    public double getAvailableMargin() {
        return availableMargin;
    }

    public void setAvailableMargin(double availableMargin) {
        this.availableMargin = availableMargin;
    }

    public double getExposureMargin() {
        return exposureMargin;
    }

    public void setExposureMargin(double exposureMargin) {
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
