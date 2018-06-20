package com.github.rishabh9.riko.upstox.feed;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.feed.models.Feed;
import com.github.rishabh9.riko.upstox.feed.models.SubscriptionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FeedApi {

    @GET("/live/feed/now/{exchange}/{symbol}/{type}")
    Call<UpstoxResponse<Feed>> liveFeed(@Path("exchange") String exchange,
                                        @Path("symbol") String symbol,
                                        @Path("type") String type);

    @GET("/live/feed/sub/{type}/{exchange}")
    Call<UpstoxResponse<SubscriptionResponse>> subscribe(@Path("type") String type,
                                                         @Path("exchange") String exchange,
                                                         @Query("symbol") String symbolsCsv);

    @GET("/live/feed/unsub/{type}/{exchange}")
    Call<UpstoxResponse<SubscriptionResponse>> unsubscribe(@Path("type") String type,
                                                           @Path("exchange") String exchange,
                                                           @Query("symbol") String symbolsCsv);
}
