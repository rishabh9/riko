package com.github.rishabh9.riko.upstox.orders;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.orders.models.Order;
import com.github.rishabh9.riko.upstox.orders.models.OrderRequest;
import com.github.rishabh9.riko.upstox.orders.models.Trade;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Order API endpoints declaration.
 */
public interface OrderApi {

    /**
     * Fetches the list of orders placed by the user.
     *
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/orders")
    Call<UpstoxResponse<List<Order>>> getOrderHistory();

    /**
     * Fetches the details of the particular order the user has placed.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/orders/{order_id}")
    Call<UpstoxResponse<List<Order>>> getOrderDetails(@Path("order_id") String orderId);

    /**
     * Fetches the trades for the current day.
     *
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/trade-book")
    Call<UpstoxResponse<List<Trade>>> getTradeBook();

    /**
     * Fetches the trades for the given order.
     *
     * @param orderId The id of the order whose details need to be fetched.
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/orders/{order_id}/trades")
    Call<UpstoxResponse<List<Trade>>> getTradeHistory(@Path("order_id") String orderId);

    /**
     * Place an order to the exchange via Upstox.
     *
     * @param request The order request
     * @return A Call to execute the request (a)synchronously.
     */
    @Headers("Content-Type: application/json")
    @POST("/live/orders")
    Call<UpstoxResponse<Order>> placeOrder(@Body OrderRequest request);

    /**
     * Modify the order.
     *
     * @param orderId The id of the order to be modified
     * @param request The order request
     * @return A Call to execute the request (a)synchronously.
     */
    @Headers("Content-Type: application/json")
    @PUT("/live/orders/{order_id}")
    Call<UpstoxResponse<Order>> modifyOrder(@Path("order_id") String orderId, @Body OrderRequest request);

    /**
     * Cancel a single or multiple orders.
     *
     * @param orderIdCsv The comma separated string of order ids that need to cancelled.
     * @return A Call to execute the request (a)synchronously.
     */
    @DELETE("/live/orders/{order_id_csv}")
    Call<UpstoxResponse<String>> cancelOrders(@Path("order_ids_csv") String orderIdCsv);

    /**
     * Cancel all open orders.
     *
     * @return A Call to execute the request (a)synchronously.
     */
    @DELETE("/live/orders")
    Call<UpstoxResponse<String>> cancelAllOrders();
}
