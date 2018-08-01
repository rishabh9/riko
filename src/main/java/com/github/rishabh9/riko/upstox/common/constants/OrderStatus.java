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

package com.github.rishabh9.riko.upstox.common.constants;

/**
 * Order Status Constants
 */
public final class OrderStatus {

    // Order was received by our order management system successfully
    public static final String PUT_ORD_REQ_RCVD = "put order req received";
    // Will get sent to the exchange after validating risk management rules
    public static final String VALIDATION_PENDING = "validation pending";
    // Order was received by exchange and awaiting open confirmation
    public static final String OPEN_PENDING = "open pending";
    // Order is open having order type as limit. Can be modified or cancelled
    public static final String OPEN = "open";
    // Order is open having order type as SL or SL-M. Can be modified or cancelled
    public static final String TRIGGER_PENDING = "trigger pending";
    // Order is fully traded and closed. Cannot be modified or cancelled
    public static final String COMPLETE = "complete";
    // Order was rejected. Please check the message field for rejection reason
    public static final String REJECTED = "rejected";
    // Will get sent to the exchange after validating risk management rules
    public static final String MODIFY_VALIDATION_PENDING = "modify validation pending";
    // Modify request received by exchange and modification pending
    public static final String MODIFY_PENDING = "modify pending";
    // Modification was rejected. Please check the message field for rejection reason
    public static final String NOT_MODIFIED = "not modified";
    // Modification was successful
    public static final String MODIFIED = "modified";
    // Cancel request received by exchange and cancellation pending
    public static final String CANCEL_PENDING = "cancel pending";
    // Cancellation request was rejected. Please check the message field for rejection reason
    public static final String NOT_CANCELLED = "not cancelled";
    // Order was cancelled. Cannot be modified or cancelled
    public static final String CANCELLED = "cancelled";
    // AMO was received by our order management system successfully. Can be modified or cancelled
    public static final String AFT_MKT_ORD_REQ_RCVD = "after market order req received";
    // AMO modification was sucessful
    public static final String MOD_AFT_MKT_ORD_REQ_RCVD = "modify after market order req received";
    // AMO was cancelled
    public static final String CANCLLED_AFT_MKT_ORD = "cancelled after market order";
}
