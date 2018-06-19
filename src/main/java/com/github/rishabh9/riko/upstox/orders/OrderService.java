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
     * Fetches the list of orders placed by the user.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return List of Order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Order>> getOrderHistory(@Nonnull final AccessToken accessToken,
                                                 @Nonnull final ApiCredentials credentials)
            throws IOException {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<Order>>> response = api.getOrderHistory().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the list of orders placed by the user, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getOrderHistoryAsync(@Nonnull final AccessToken accessToken,
                                     @Nonnull final ApiCredentials credentials,
                                     @Nonnull final CallMe<List<Order>> callMe) {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.getOrderHistory().enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the details of the particular order the user has placed.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderId     The id of the order whose details need to be fetched.
     * @return List of Order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Order>> getOrderDetails(@Nonnull final AccessToken accessToken,
                                                 @Nonnull final ApiCredentials credentials,
                                                 @Nonnull final String orderId)
            throws IOException {

        validateOrderId(orderId);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<Order>>> response = api.getOrderDetails(orderId).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the details of the particular order the user has placed, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderId     The id of the order whose details need to be fetched.
     * @param callMe      The call back interface
     */
    public void getOrderDetailsAsync(@Nonnull final AccessToken accessToken,
                                     @Nonnull final ApiCredentials credentials,
                                     @Nonnull final String orderId,
                                     @Nonnull final CallMe<List<Order>> callMe) {

        validateOrderId(orderId);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.getOrderDetails(orderId).enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the trades for the current day.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return List of Trade
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Trade>> getTradeBook(@Nonnull final AccessToken accessToken,
                                              @Nonnull final ApiCredentials credentials)
            throws IOException {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<Trade>>> response = api.getTradeBook().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the trades for the current day, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getTradeBookAsync(@Nonnull final AccessToken accessToken,
                                  @Nonnull final ApiCredentials credentials,
                                  @Nonnull final CallMe<List<Trade>> callMe) {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.getTradeBook().enqueue(prepareCallback(callMe));
    }


    /**
     * Fetches the trades for the given order.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderId     The id of the order whose trade history need to be fetched.
     * @return List of Trade
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Trade>> getTradeHistory(@Nonnull final AccessToken accessToken,
                                                 @Nonnull final ApiCredentials credentials,
                                                 @Nonnull final String orderId)
            throws IOException {

        validateOrderId(orderId);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<Trade>>> response = api.getTradeHistory(orderId).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the trades for the given order, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderId     The id of the order whose trade history need to be fetched.
     * @param callMe      The call back interface
     */
    public void getTradeHistoryAsync(@Nonnull final AccessToken accessToken,
                                     @Nonnull final ApiCredentials credentials,
                                     @Nonnull final String orderId,
                                     @Nonnull final CallMe<List<Trade>> callMe) {

        validateOrderId(orderId);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.getTradeHistory(orderId).enqueue(prepareCallback(callMe));
    }

    /**
     * Place an order to the exchange via Upstox.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param request     The order request
     * @return The creates order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Order> placeOrder(@Nonnull final AccessToken accessToken,
                                      @Nonnull final ApiCredentials credentials,
                                      @Nonnull final OrderRequest request)
            throws IOException {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<Order>> response = api.placeOrder(request).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Place an order to the exchange via Upstox, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param request     The order request
     * @param callMe      The call back interface
     */
    public void placeOrderAsync(@Nonnull final AccessToken accessToken,
                                @Nonnull final ApiCredentials credentials,
                                @Nonnull final OrderRequest request,
                                @Nonnull final CallMe<Order> callMe) {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.placeOrder(request).enqueue(prepareCallback(callMe));
    }

    /**
     * Modify the order.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderId     The id of the order to be modified
     * @param request     The order request
     * @return The modified order
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Order> modifyOrder(@Nonnull final AccessToken accessToken,
                                       @Nonnull final ApiCredentials credentials,
                                       @Nonnull final String orderId,
                                       @Nonnull final OrderRequest request)
            throws IOException {

        validateOrderId(orderId);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<Order>> response = api.modifyOrder(orderId, request).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Modify the order, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderId     The id of the order to be modified
     * @param request     The order request
     * @param callMe      The call back interface
     */
    public void modifyOrderAsync(@Nonnull final AccessToken accessToken,
                                 @Nonnull final ApiCredentials credentials,
                                 @Nonnull final String orderId,
                                 @Nonnull final OrderRequest request,
                                 @Nonnull final CallMe<Order> callMe) {

        validateOrderId(orderId);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.modifyOrder(orderId, request).enqueue(prepareCallback(callMe));
    }

    /**
     * Cancel a single or multiple orders.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderIdsCsv The comma separated string of order ids that need to cancelled.
     * @return The comma separate string of affected orders
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<String> cancelOrders(@Nonnull final AccessToken accessToken,
                                         @Nonnull final ApiCredentials credentials,
                                         @Nonnull final String orderIdsCsv)
            throws IOException {

        validateOrderId(orderIdsCsv);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<String>> response = api.cancelOrders(orderIdsCsv).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Cancel a single or multiple orders, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param orderIdsCsv The comma separated string of order ids that need to cancelled.
     * @param callMe      The call back interface
     */
    public void cancelOrdersAsync(@Nonnull final AccessToken accessToken,
                                  @Nonnull final ApiCredentials credentials,
                                  @Nonnull final String orderIdsCsv,
                                  @Nonnull final CallMe<String> callMe) {

        validateOrderId(orderIdsCsv);

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.cancelOrders(orderIdsCsv).enqueue(prepareCallback(callMe));
    }

    /**
     * Cancel all open orders.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return The comma separate string of affected orders
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<String> cancelAllOrders(@Nonnull final AccessToken accessToken,
                                            @Nonnull final ApiCredentials credentials)
            throws IOException {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        Response<UpstoxResponse<String>> response = api.cancelAllOrders().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Cancel all open orders, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void cancelAllOrdersAsync(@Nonnull final AccessToken accessToken,
                                     @Nonnull final ApiCredentials credentials,
                                     @Nonnull final CallMe<String> callMe) {

        OrderApi api = prepareServiceApi(OrderApi.class, accessToken, credentials);

        api.cancelAllOrders().enqueue(prepareCallback(callMe));
    }

    private void validateOrderId(@Nonnull String orderId) {
        if (Strings.isNullOrEmpty(orderId)) {
            throw new IllegalArgumentException("Argument 'orderId' is mandatory. It cannot be null nor empty.");
        }
    }
}
