package com.github.rishabh9.riko.upstox.orders;

import com.github.rishabh9.riko.upstox.common.CallMe;
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
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class OrderService extends Service {

    private static final Logger log = LogManager.getLogger(OrderService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public OrderService(@Nonnull AccessToken accessToken, @Nonnull ApiCredentials credentials) {
        super(accessToken, credentials);
    }

    /**
     * Fetches the list of orders placed by the user.
     *
     * @return List of Order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Order>> getOrderHistory()
            throws IOException {

        log.debug("Preparing service - GET Order History");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Order History");
        final Response<UpstoxResponse<List<Order>>> response = api.getOrderHistory().execute();

        log.debug("Finishing request - GET Order History");
        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the list of orders placed by the user, asynchronously.
     *
     * @param callMe The call back interface
     */
    public void getOrderHistoryAsync(@Nonnull final CallMe<List<Order>> callMe) {

        log.debug("Preparing async service - GET Order History");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - GET Order History");
        api.getOrderHistory().enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the details of the particular order the user has placed.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @return List of Order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Order>> getOrderDetails(@Nonnull final String orderId)
            throws IOException {

        log.debug("Validate parameters - GET Order Details");
        validateOrderId(orderId);

        log.debug("Preparing service - GET Order Details");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Order Details");
        final Response<UpstoxResponse<List<Order>>> response = api.getOrderDetails(orderId).execute();

        log.debug("Finishing request - GET Order Details");
        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the details of the particular order the user has placed, asynchronously.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @param callMe  The call back interface
     */
    public void getOrderDetailsAsync(@Nonnull final String orderId,
                                     @Nonnull final CallMe<List<Order>> callMe) {

        log.debug("Validate parameters - GET Order Details");
        validateOrderId(orderId);

        log.debug("Preparing async service - GET Order Details");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - GET Order Details");
        api.getOrderDetails(orderId).enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the trades for the current day.
     *
     * @return List of Trade
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Trade>> getTradeBook()
            throws IOException {

        log.debug("Preparing service - GET Trade Book");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Trade Book");
        final Response<UpstoxResponse<List<Trade>>> response = api.getTradeBook().execute();

        log.debug("Finishing request - GET Trade Book");
        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the trades for the current day, asynchronously.
     *
     * @param callMe The call back interface
     */
    public void getTradeBookAsync(@Nonnull final CallMe<List<Trade>> callMe) {

        log.debug("Preparing async service - GET Trade Book");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - GET Trade Book");
        api.getTradeBook().enqueue(prepareCallback(callMe));
    }


    /**
     * Fetches the trades for the given order.
     *
     * @param orderId The id of the order whose trade history need to be fetched.
     * @return List of Trade
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Trade>> getTradeHistory(@Nonnull final String orderId)
            throws IOException {

        log.debug("Validate parameters - GET Trade History");
        validateOrderId(orderId);

        log.debug("Preparing service - GET Trade History");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - GET Trade History");
        final Response<UpstoxResponse<List<Trade>>> response = api.getTradeHistory(orderId).execute();

        log.debug("Finishing request - GET Trade History");
        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the trades for the given order, asynchronously.
     *
     * @param orderId The id of the order whose trade history need to be fetched.
     * @param callMe  The call back interface
     */
    public void getTradeHistoryAsync(@Nonnull final String orderId,
                                     @Nonnull final CallMe<List<Trade>> callMe) {

        log.debug("Validate parameters - GET Trade History");
        validateOrderId(orderId);

        log.debug("Preparing async service - GET Trade History");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - GET Trade History");
        api.getTradeHistory(orderId).enqueue(prepareCallback(callMe));
    }

    /**
     * Place an order to the exchange via Upstox.
     *
     * @param request The order request
     * @return The creates order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Order> placeOrder(@Nonnull final OrderRequest request)
            throws IOException {

        log.debug("Preparing service - POST Place Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - POST Place Order");
        final Response<UpstoxResponse<Order>> response = api.placeOrder(request).execute();

        log.debug("Finishing request - POST Place Order");
        return completeSynchronousRequest(response);
    }

    /**
     * Place an order to the exchange via Upstox, asynchronously.
     *
     * @param request The order request
     * @param callMe  The call back interface
     */
    public void placeOrderAsync(@Nonnull final OrderRequest request,
                                @Nonnull final CallMe<Order> callMe) {

        log.debug("Preparing async service - POST Place Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - POST Place Order");
        api.placeOrder(request).enqueue(prepareCallback(callMe));
    }

    /**
     * Modify the order.
     *
     * @param orderId The id of the order to be modified
     * @param request The order request
     * @return The modified order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Order> modifyOrder(@Nonnull final String orderId,
                                       @Nonnull final OrderRequest request)
            throws IOException {

        log.debug("Validate parameters - PUT Place Order");
        validateOrderId(orderId);

        log.debug("Preparing service - PUT Place Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - PUT Place Order");
        final Response<UpstoxResponse<Order>> response = api.modifyOrder(orderId, request).execute();

        log.debug("Finishing request - PUT Place Order");
        return completeSynchronousRequest(response);
    }

    /**
     * Modify the order, asynchronously.
     *
     * @param orderId The id of the order to be modified
     * @param request The order request
     * @param callMe  The call back interface
     */
    public void modifyOrderAsync(@Nonnull final String orderId,
                                 @Nonnull final OrderRequest request,
                                 @Nonnull final CallMe<Order> callMe) {

        log.debug("Validate parameters - PUT Place Order");
        validateOrderId(orderId);

        log.debug("Preparing async service - PUT Place Order");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - PUT Place Order");
        api.modifyOrder(orderId, request).enqueue(prepareCallback(callMe));
    }

    /**
     * Cancel a single or multiple orders.
     *
     * @param orderIdsCsv The comma separated string of order ids that need to cancelled.
     * @return The comma separate string of affected orders
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<String> cancelOrders(@Nonnull final String orderIdsCsv)
            throws IOException {

        log.debug("Validate parameters - DELETE Orders");
        validateOrderId(orderIdsCsv);

        log.debug("Preparing service - DELETE Orders");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - DELETE Orders");
        final Response<UpstoxResponse<String>> response = api.cancelOrders(orderIdsCsv).execute();

        log.debug("Finishing request - DELETE Orders");
        return completeSynchronousRequest(response);
    }

    /**
     * Cancel a single or multiple orders, asynchronously.
     *
     * @param orderIdsCsv The comma separated string of order ids that need to cancelled.
     * @param callMe      The call back interface
     */
    public void cancelOrdersAsync(@Nonnull final String orderIdsCsv,
                                  @Nonnull final CallMe<String> callMe) {

        log.debug("Validate parameters - DELETE Orders");
        validateOrderId(orderIdsCsv);

        log.debug("Preparing async service - DELETE Orders");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - DELETE Orders");
        api.cancelOrders(orderIdsCsv).enqueue(prepareCallback(callMe));
    }

    /**
     * Cancel all open orders.
     *
     * @return The comma separate string of affected orders
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<String> cancelAllOrders()
            throws IOException {

        log.debug("Preparing service - DELETE All Orders");
        OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Making request - DELETE All Orders");
        final Response<UpstoxResponse<String>> response = api.cancelAllOrders().execute();

        log.debug("Finishing request - DELETE All Orders");
        return completeSynchronousRequest(response);
    }

    /**
     * Cancel all open orders, asynchronously.
     *
     * @param callMe The call back interface
     */
    public void cancelAllOrdersAsync(@Nonnull final CallMe<String> callMe) {

        log.debug("Preparing async service - DELETE All Orders");
        final OrderApi api = prepareServiceApi(OrderApi.class);

        log.debug("Setting up callback interface - DELETE All Orders");
        api.cancelAllOrders().enqueue(prepareCallback(callMe));
    }

    private void validateOrderId(@Nonnull String orderId) {
        if (Strings.isNullOrEmpty(orderId)) {
            log.error("Argument validation failed. Argument 'orderId' is mandatory.");
            throw new IllegalArgumentException("Argument 'orderId' is mandatory. It cannot be null nor empty.");
        }
    }
}
