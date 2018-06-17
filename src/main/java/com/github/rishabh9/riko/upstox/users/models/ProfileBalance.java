package com.github.rishabh9.riko.upstox.users.models;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class ProfileBalance {

    private Balance equity;
    private Balance commodity;

    public Balance getEquity() {
        return equity;
    }

    public void setEquity(Balance equity) {
        this.equity = equity;
    }

    public Balance getCommodity() {
        return commodity;
    }

    public void setCommodity(Balance commodity) {
        this.commodity = commodity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileBalance that = (ProfileBalance) o;
        return Objects.equals(equity, that.equity) &&
                Objects.equals(commodity, that.commodity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equity, commodity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("equity", equity)
                .add("commodity", commodity)
                .toString();
    }
}
