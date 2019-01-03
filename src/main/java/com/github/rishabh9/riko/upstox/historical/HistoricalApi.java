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
     *                  <li><code>1</code></li>
     *                  <li><code>3</code></li>
     *                  <li><code>5</code></li>
     *                  <li><code>10</code></li>
     *                  <li><code>15</code></li>
     *                  <li><code>30</code></li>
     *                  <li><code>60</code></li>
     *                  <li><code>day</code></li>
     *                  <li><code>week</code></li>
     *                  <li><code>month</code></li>
     *                  </ul>
     * @param startDate Date in the format: <code>DD-MM-YYYY</code>. Default value is 7 days before today.
     * @param endDate   Date in the format: <code>DD-MM-YYYY</code>. Default value is today.
     * @param format    Response format - 'csv' or 'json'.
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/historical/{exchange}/{symbol}/{interval}")
    CompletableFuture<UpstoxResponse<List<Candle>>> getOhlc(@Path("exchange") String exchange,
                                                            @Path("symbol") String symbol,
                                                            @Path("interval") String interval,
                                                            @Query("start_date") String startDate,
                                                            @Query("end_date") String endDate,
                                                            @Query("format") String format);
}
