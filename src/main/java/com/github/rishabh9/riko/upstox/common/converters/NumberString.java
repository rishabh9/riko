package com.github.rishabh9.riko.upstox.common.converters;

import java.math.BigDecimal;
import java.util.Objects;

public class NumberString {

    private BigDecimal value;

    public NumberString(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberString that = (NumberString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        if (null == value) {
            return  "";
        }
        return value.toPlainString();
    }
}
