/*
 * MIT License
 *
 * Copyright (c) 2019 Rishabh Joshi
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
