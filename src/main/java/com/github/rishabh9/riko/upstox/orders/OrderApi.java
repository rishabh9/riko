package com.github.rishabh9.riko.upstox.orders;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.orders.models.Order;
import com.github.rishabh9.riko.upstox.orders.models.OrderRequest;
import com.github.rishabh9.riko.upstox.orders.models.Trade;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface OrderApi {

    @GET("/live/orders")
    Call<UpstoxResponse<List<Order>>> getOrderHistory();

    @GET("/live/orders/{order_id}")
    Call<UpstoxResponse<List<Order>>> getOrderDetails(@Path("order_id") String orderId);

    @GET("/live/trade-book")
    Call<UpstoxResponse<List<Trade>>> getTradeBook();

    @GET("/live/orders/{order_id}/trades")
    Call<UpstoxResponse<List<Trade>>> getTradeHistory(@Path("order_id") String orderId);

    @Headers("Content-Type: application/json")
    @POST("/live/orders")
    Call<UpstoxResponse<Order>> placeOrder(@Body OrderRequest request);

    @Headers("Content-Type: application/json")
    @PUT("/live/orders/{order_id}")
    Call<UpstoxResponse<Order>> modifyOrder(@Path("order_id") String orderId, @Body OrderRequest request);

    @DELETE("/live/orders/{order_id_csv}")
    Call<UpstoxResponse<String>> cancelOrders(@Path("order_ids_csv") String orderIdCsv);

    @DELETE("/live/orders")
    Call<UpstoxResponse<String>> cancelAllOrders();
}
