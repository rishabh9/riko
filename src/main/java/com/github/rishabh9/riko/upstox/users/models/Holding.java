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

import java.util.List;
import java.util.Objects;

public class Holding {

    private List<Instrument> instruments;

    private String product;

    @SerializedName("collateral_type")
    private String collateralType;

    @SerializedName("cnc_used_quantity")
    private Long cncUsedQuantity;

    private Long quantity;

    @SerializedName("collateral_qty")
    private Long collateralQuantity;

    @SerializedName("haircut")
    private Double haircutPercentage;

    @SerializedName("avg_price")
    private Double averagePrice;

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCollateralType() {
        return collateralType;
    }

    public void setCollateralType(String collateralType) {
        this.collateralType = collateralType;
    }

    public Long getCncUsedQuantity() {
        return cncUsedQuantity;
    }

    public void setCncUsedQuantity(Long cncUsedQuantity) {
        this.cncUsedQuantity = cncUsedQuantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getCollateralQuantity() {
        return collateralQuantity;
    }

    public void setCollateralQuantity(Long collateralQuantity) {
        this.collateralQuantity = collateralQuantity;
    }

    public Double getHaircutPercentage() {
        return haircutPercentage;
    }

    public void setHaircutPercentage(Double haircutPercentage) {
        this.haircutPercentage = haircutPercentage;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holding holding = (Holding) o;
        return Objects.equals(instruments, holding.instruments) &&
                Objects.equals(product, holding.product) &&
                Objects.equals(collateralType, holding.collateralType) &&
                Objects.equals(cncUsedQuantity, holding.cncUsedQuantity) &&
                Objects.equals(quantity, holding.quantity) &&
                Objects.equals(collateralQuantity, holding.collateralQuantity) &&
                Objects.equals(haircutPercentage, holding.haircutPercentage) &&
                Objects.equals(averagePrice, holding.averagePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instruments, product, collateralType, cncUsedQuantity,
                quantity, collateralQuantity, haircutPercentage, averagePrice);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("instruments", instruments)
                .add("product", product)
                .add("collateralType", collateralType)
                .add("cncUsedQuantity", cncUsedQuantity)
                .add("quantity", quantity)
                .add("collateralQuantity", collateralQuantity)
                .add("haircutPercentage", haircutPercentage)
                .add("averagePrice", averagePrice)
                .toString();
    }
}
