package com.github.rishabh9.riko.upstox.feed;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.feed.models.Feed;
import com.github.rishabh9.riko.upstox.feed.models.SubscriptionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/feed/now/{exchange}/{symbol}/{type}")
    Call<UpstoxResponse<Feed>> liveFeed(@Path("exchange") String exchange,
                                        @Path("symbol") String symbol,
                                        @Path("type") String type);

    /**
     * Subscribe to get the live feed of a particular instrument in real-time.
     *
     * @param type       'ltp' or 'full'.
     * @param exchange   Name of the exchange.
     * @param symbolsCsv Comma separated trading symbols.
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/feed/sub/{type}/{exchange}")
    Call<UpstoxResponse<SubscriptionResponse>> subscribe(@Path("type") String type,
                                                         @Path("exchange") String exchange,
                                                         @Query("symbol") String symbolsCsv);

    /**
     * Unsubscribe for the live data feed for the instruments chosen.
     *
     * @param type       'ltp' or 'full'.
     * @param exchange   Name of the exchange.
     * @param symbolsCsv Comma separated trading symbols.
     * @return A Call to execute the request (a)synchronously.
     */
    @GET("/live/feed/unsub/{type}/{exchange}")
    Call<UpstoxResponse<SubscriptionResponse>> unsubscribe(@Path("type") String type,
                                                           @Path("exchange") String exchange,
                                                           @Query("symbol") String symbolsCsv);
}
