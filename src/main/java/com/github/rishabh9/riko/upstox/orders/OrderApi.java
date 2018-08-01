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

package com.github.rishabh9.riko.upstox.orders;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.orders.models.Order;
import com.github.rishabh9.riko.upstox.orders.models.OrderRequest;
import com.github.rishabh9.riko.upstox.orders.models.Trade;
import retrofit2.http.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Order API endpoints declaration.
 */
public interface OrderApi {

    /**
     * Fetches the list of orders placed by the user.
     *
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/orders")
    CompletableFuture<UpstoxResponse<List<Order>>> getOrderHistory();

    /**
     * Fetches the details of the particular order the user has placed.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/orders/{order_id}")
    CompletableFuture<UpstoxResponse<List<Order>>> getOrderDetails(@Path("order_id") String orderId);

    /**
     * Fetches the trades for the current day.
     *
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/trade-book")
    CompletableFuture<UpstoxResponse<List<Trade>>> getTradeBook();

    /**
     * Fetches the trades for the given order.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/orders/{order_id}/trades")
    CompletableFuture<UpstoxResponse<List<Trade>>> getTradeHistory(@Path("order_id") String orderId);

    /**
     * Place an order to the exchange via Upstox.
     *
     * @param request The order request
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @Headers("Content-Type: application/json")
    @POST("/live/orders")
    CompletableFuture<UpstoxResponse<Order>> placeOrder(@Body OrderRequest request);

    /**
     * Modify the order.
     *
     * @param orderId The id of the order to be modified
     * @param request The order request
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @Headers("Content-Type: application/json")
    @PUT("/live/orders/{order_id}")
    CompletableFuture<UpstoxResponse<Order>> modifyOrder(@Path("order_id") String orderId, @Body OrderRequest request);

    /**
     * Cancel a single or multiple orders.
     *
     * @param orderIdCsv The comma separated string of order ids that need to cancelled.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @DELETE("/live/orders/{order_ids_csv}")
    CompletableFuture<UpstoxResponse<String>> cancelOrders(@Path("order_ids_csv") String orderIdCsv);

    /**
     * Cancel all open orders.
     *
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @DELETE("/live/orders")
    CompletableFuture<UpstoxResponse<String>> cancelAllOrders();
}
