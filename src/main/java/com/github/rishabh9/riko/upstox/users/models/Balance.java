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

public class Balance {

    @SerializedName("used_margin")
    private Double usedMargin;

    @SerializedName("payin_amount")
    private Double payinAmount;

    @SerializedName("span_margin")
    private Double spanMargin;

    @SerializedName("adhoc_margin")
    private Double adhocMargin;

    @SerializedName("notional_cash")
    private Double notionalCash;

    @SerializedName("available_margin")
    private Double availableMargin;

    @SerializedName("exposure_margin")
    private Double exposureMargin;

    public Double getUsedMargin() {
        return usedMargin;
    }

    public void setUsedMargin(Double usedMargin) {
        this.usedMargin = usedMargin;
    }

    public Double getPayinAmount() {
        return payinAmount;
    }

    public void setPayinAmount(Double payinAmount) {
        this.payinAmount = payinAmount;
    }

    public Double getSpanMargin() {
        return spanMargin;
    }

    public void setSpanMargin(Double spanMargin) {
        this.spanMargin = spanMargin;
    }

    public Double getAdhocMargin() {
        return adhocMargin;
    }

    public void setAdhocMargin(Double adhocMargin) {
        this.adhocMargin = adhocMargin;
    }

    public Double getNotionalCash() {
        return notionalCash;
    }

    public void setNotionalCash(Double notionalCash) {
        this.notionalCash = notionalCash;
    }

    public Double getAvailableMargin() {
        return availableMargin;
    }

    public void setAvailableMargin(Double availableMargin) {
        this.availableMargin = availableMargin;
    }

    public Double getExposureMargin() {
        return exposureMargin;
    }

    public void setExposureMargin(Double exposureMargin) {
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
