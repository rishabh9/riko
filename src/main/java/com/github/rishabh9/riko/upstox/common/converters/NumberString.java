package com.github.rishabh9.riko.upstox.common.converters;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class is used to support the anomaly at Upstox end,
 * where their API can return either an empty string or a number.
 * <p>
 * Example for retrieving value for calculations:<br>
 *     <code>if (numberString.isNumber()) {</code><br>
 *     <code>&nbsp;&nbsp;BigDecimal value = numberString.value();</code><br>
 *     <code>}</code>
 * </p>
 * <p>
 * For all other purposes, just call the {@link NumberString#toString() toString()} method.
 * </p>
 */
public class NumberString {

    private BigDecimal value;

    public NumberString(BigDecimal value) {
        this.value = value;
    }

    /**
     * @return The actual value. Run {@link NumberString#isNumber isNumber()} to validate and prevent NPE.
     */
    public BigDecimal value() {
        return value;
    }

    /**
     * @return {@code TRUE} if the value it holds is a number.
     */
    public boolean isNumber() {
        return null != value;
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
            return "";
        }
        return value.toPlainString();
    }
}
