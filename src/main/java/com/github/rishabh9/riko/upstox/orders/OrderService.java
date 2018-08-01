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

import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.orders.models.Order;
import com.github.rishabh9.riko.upstox.orders.models.OrderRequest;
import com.github.rishabh9.riko.upstox.orders.models.Trade;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class OrderService extends Service {

    private static final Logger log = LogManager.getLogger(OrderService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public OrderService(@Nonnull final AccessToken accessToken,
                        @Nonnull final ApiCredentials credentials) {

        super(Objects.requireNonNull(accessToken), Objects.requireNonNull(credentials));
    }

    /**
     * Fetches the list of orders placed by the user.
     *
     * @return List of Order
     */
    public CompletableFuture<UpstoxResponse<List<Order>>> getOrderHistory() {

        log.debug("Preparing service - GET Order History");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Order History");
        return api.getOrderHistory();
    }

    /**
     * Fetches the details of the particular order the user has placed.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @return List of Order
     */
    public CompletableFuture<UpstoxResponse<List<Order>>> getOrderDetails(@Nonnull final String orderId) {

        log.debug("Validate parameters - GET Order Details");
        validateOrderId(orderId);

        log.debug("Preparing service - GET Order Details");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Order Details");
        return api.getOrderDetails(orderId);
    }

    /**
     * Fetches the trades for the current day.
     *
     * @return List of Trade
     */
    public CompletableFuture<UpstoxResponse<List<Trade>>> getTradeBook() {

        log.debug("Preparing service - GET Trade Book");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Trade Book");
        return api.getTradeBook();
    }

    /**
     * Fetches the trades for the given order.
     *
     * @param orderId The id of the order whose trade history need to be fetched.
     * @return List of Trade
     */
    public CompletableFuture<UpstoxResponse<List<Trade>>> getTradeHistory(@Nonnull final String orderId) {

        log.debug("Validate parameters - GET Trade History");
        validateOrderId(orderId);

        log.debug("Preparing service - GET Trade History");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Trade History");
        return api.getTradeHistory(orderId);
    }

    /**
     * Place an order to the exchange via Upstox.
     *
     * @param request The order request
     * @return The creates order
     */
    public CompletableFuture<UpstoxResponse<Order>> placeOrder(@Nonnull final OrderRequest request) {

        log.debug("Validate parameters - POST Place Order");
        validateOrderRequest(request);

        log.debug("Preparing service - POST Place Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - POST Place Order");
        return api.placeOrder(request);
    }

    /**
     * Modify the order.
     *
     * @param orderId The id of the order to be modified
     * @param request The order request
     * @return The modified order
     */
    public CompletableFuture<UpstoxResponse<Order>> modifyOrder(@Nonnull final String orderId,
                                                                @Nonnull final OrderRequest request) {

        log.debug("Validate parameters - PUT Modify Order");
        validateOrderId(orderId);
        validateOrderRequest(request);

        log.debug("Preparing service - PUT Modify Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - PUT Modify Order");
        return api.modifyOrder(orderId, request);
    }

    /**
     * Cancel a single or multiple orders.
     *
     * @param orderIdsCsv The comma separated string of order ids that need to cancelled.
     * @return The comma separate string of affected orders
     */
    public CompletableFuture<UpstoxResponse<String>> cancelOrders(@Nonnull final String orderIdsCsv) {

        log.debug("Validate parameters - DELETE Orders");
        validateOrderId(orderIdsCsv);

        log.debug("Preparing service - DELETE Orders");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - DELETE Orders");
        return api.cancelOrders(orderIdsCsv);
    }

    /**
     * Cancel all open orders.
     *
     * @return The comma separate string of affected orders
     */
    public CompletableFuture<UpstoxResponse<String>> cancelAllOrders() {

        log.debug("Preparing service - DELETE All Orders");
        OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - DELETE All Orders");
        return api.cancelAllOrders();
    }

    private void validateOrderRequest(OrderRequest request) {
        if (null == request) {
            log.error("Order placement request parameters are missing.");
            throw new IllegalArgumentException("Order placement request parameters are missing.");
        }
    }

    private void validateOrderId(String orderId) {
        if (Strings.isNullOrEmpty(orderId)) {
            log.error("Argument validation failed. Argument 'orderId' is mandatory.");
            throw new IllegalArgumentException("Argument 'orderId' is mandatory. It cannot be null nor empty.");
        }
    }
}
