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

package com.github.rishabh9.riko.upstox.feed;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.feed.models.Feed;
import com.github.rishabh9.riko.upstox.feed.models.Subscription;
import com.github.rishabh9.riko.upstox.feed.models.SubscriptionResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

/**
 * Feed API endpoints declaration.
 */
public interface FeedApi {

    /**
     * Get live feed information about a single instrument.
     *
     * @param exchange Name of the exchange.
     * @param symbol   Trading symbol.
     * @param type     'ltp' or 'full'.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/feed/now/{exchange}/{symbol}/{type}")
    CompletableFuture<UpstoxResponse<Feed>> liveFeed(@Path("exchange") String exchange,
                                                     @Path("symbol") String symbol,
                                                     @Path("type") String type);

    /**
     * Subscribe to get the live feed of a particular instrument in real-time.
     *
     * @param type       'ltp' or 'full'.
     * @param exchange   Name of the exchange.
     * @param symbolsCsv Comma separated trading symbols.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/feed/sub/{type}/{exchange}")
    CompletableFuture<UpstoxResponse<SubscriptionResponse>> subscribe(@Path("type") String type,
                                                                      @Path("exchange") String exchange,
                                                                      @Query("symbol") String symbolsCsv);

    /**
     * Unsubscribe for the live data feed for the instruments chosen.
     *
     * @param type       'ltp' or 'full'.
     * @param exchange   Name of the exchange.
     * @param symbolsCsv Comma separated trading symbols.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/feed/unsub/{type}/{exchange}")
    CompletableFuture<UpstoxResponse<SubscriptionResponse>> unsubscribe(@Path("type") String type,
                                                                        @Path("exchange") String exchange,
                                                                        @Query("symbol") String symbolsCsv);

    /**
     * Get list of symbols subscribed.
     *
     * @param type 'all' or 'ltp' or 'full'.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/feed/{type}")
    CompletableFuture<UpstoxResponse<Subscription>> symbolsSubscribed(@Path("type") String type);
}
