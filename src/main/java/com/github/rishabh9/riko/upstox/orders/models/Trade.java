package com.github.rishabh9.riko.upstox.orders.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Trade {

    private String exchange;

    private long token;

    private String symbol;

    private String product;

    @SerializedName("order_type")
    private String orderType;

    @SerializedName("transaction_type")
    private String transactionType;

    @SerializedName("traded_quantity")
    private long tradedQuantity;

    @SerializedName("exchange_order_id")
    private String exchangeOrderId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("exchange_time")
    private String exchangeTime;

    @SerializedName("time_in_micro")
    private String timeInMicro;

    @SerializedName("traded_price")
    private BigDecimal tradedPrice;

    @SerializedName("trade_id")
    private long tradeId;
}
