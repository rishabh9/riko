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

package com.github.rishabh9.riko.upstox.orders;

import com.github.rishabh9.riko.upstox.common.RetryPolicyFactory;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.orders.models.Order;
import com.github.rishabh9.riko.upstox.orders.models.OrderRequest;
import com.github.rishabh9.riko.upstox.orders.models.Trade;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import net.jodah.failsafe.Failsafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.github.rishabh9.riko.upstox.common.constants.RateLimits.*;

@SuppressWarnings("UnstableApiUsage")
public class OrderService extends Service {

    private static final Logger log = LogManager.getLogger(OrderService.class);

    private static final RateLimiter orderHistoryRateLimiter = RateLimiter.create(ORDER_HISTORY_RATE_LIMIT);
    private static final RateLimiter orderDetailsRateLimiter = RateLimiter.create(ORDER_DETAILS_RATE_LIMIT);
    private static final RateLimiter tradeBookRateLimiter = RateLimiter.create(TRADE_BOOK_RATE_LIMIT);
    private static final RateLimiter tradeHistoryRateLimiter = RateLimiter.create(TRADE_HISTORY_RATE_LIMIT);
    private static final RateLimiter placeOrderRateLimiter = RateLimiter.create(PLACE_ORDER_RATE_LIMIT);
    private static final RateLimiter modifyOrderRateLimiter = RateLimiter.create(MODIFY_ORDER_RATE_LIMIT);
    private static final RateLimiter cancelOrderRateLimiter = RateLimiter.create(CANCEL_ORDER_RATE_LIMIT);

    /**
     * @param upstoxAuthService The service to retrieve authentication details
     */
    public OrderService(@Nonnull final UpstoxAuthService upstoxAuthService,
                        @Nonnull final RetryPolicyFactory retryPolicyFactory) {

        super(upstoxAuthService, retryPolicyFactory);
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Order History.", failure))
                .onSuccess(response -> log.debug("GET Order History successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Order History, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    orderHistoryRateLimiter.acquire(1);
                    return api.getOrderHistory();
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Order Details. ", failure))
                .onSuccess(response -> log.debug("GET Order Details successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Order Details, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    orderDetailsRateLimiter.acquire(1);
                    return api.getOrderDetails(orderId);
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Trade Book. ", failure))
                .onSuccess(response -> log.debug("GET Trade Book successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Trade Book, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    tradeBookRateLimiter.acquire(1);
                    return api.getTradeBook();
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Trade History. ", failure))
                .onSuccess(response -> log.debug("GET Trade History successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Trade History, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    tradeHistoryRateLimiter.acquire(1);
                    return api.getTradeHistory(orderId);
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to POST Place Order.", failure))
                .onSuccess(response -> log.debug("POST Place Order successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to POST Place Order, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    placeOrderRateLimiter.acquire(1);
                    return api.placeOrder(request);
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to PUT Modify Order.", failure))
                .onSuccess(response -> log.debug("PUT Modify Order successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to PUT Modify Order, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    modifyOrderRateLimiter.acquire(1);
                    return api.modifyOrder(orderId, request);
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to DELETE Orders.", failure))
                .onSuccess(response -> log.debug("DELETE Orders successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to DELETE Orders, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    cancelOrderRateLimiter.acquire(1);
                    return api.cancelOrders(orderIdsCsv);
                });
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
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to DELETE All Orders.", failure))
                .onSuccess(response -> log.debug("DELETE All Orders successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to DELETE All Orders, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    cancelOrderRateLimiter.acquire(1);
                    return api.cancelAllOrders();
                });
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
