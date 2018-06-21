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
import java.util.concurrent.CompletableFuture;

public class OrderService extends Service {

    private static final Logger log = LogManager.getLogger(OrderService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public OrderService(@Nonnull final AccessToken accessToken, @Nonnull final ApiCredentials credentials) {
        super(accessToken, credentials);
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

        log.debug("Validate parameters - PUT Place Order");
        validateOrderId(orderId);

        log.debug("Preparing service - PUT Place Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - PUT Place Order");
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

    private void validateOrderId(@Nonnull String orderId) {
        if (Strings.isNullOrEmpty(orderId)) {
            log.error("Argument validation failed. Argument 'orderId' is mandatory.");
            throw new IllegalArgumentException("Argument 'orderId' is mandatory. It cannot be null nor empty.");
        }
    }
}
