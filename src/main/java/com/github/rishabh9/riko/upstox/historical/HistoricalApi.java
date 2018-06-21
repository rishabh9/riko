package com.github.rishabh9.riko.upstox.historical;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.historical.models.Candle;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Historical API endpoints declaration.
 */
public interface HistoricalApi {

    /**
     * Get OHLC data.
     *
     * @param exchange  Name of the exchange. <em>Mandatory.</em>
     * @param symbol    Trading symbol. <em>Mandatory.</em>
     * @param interval  Allowed Values:
     *                  <ul>
     *                  <li><code>1MINUTE</code></li>
     *                  <li><code>5MINUTE</code></li>
     *                  <li><code>10MINUTE</code></li>
     *                  <li><code>30MINUTE</code></li>
     *                  <li><code>60MINUTE</code></li>
     *                  <li><code>1DAY</code> <em>(default)</em></li>
     *                  <li><code>1WEEK</code></li>
     *                  <li><code>1MONTH</code></li>
     *                  </ul>
     * @param startDate Date in the format: <code>DD-MM-YYYY</code>. Default value is 15 days before today.
     * @param endDate   Date in the format: <code>DD-MM-YYYY</code>. Default value is today.
     * @param format    Response format - 'csv' or 'json'.
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/historical/ohlc/{exchange}/{symbol}")
    CompletableFuture<UpstoxResponse<List<Candle>>> getOhlc(@Path("exchange") String exchange,
                                                            @Path("symbol") String symbol,
                                                            @Query("interval") String interval,
                                                            @Query("start_date") String startDate,
                                                            @Query("end_date") String endDate,
                                                            @Query("format") String format);
}
