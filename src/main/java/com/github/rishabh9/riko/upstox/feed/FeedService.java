package com.github.rishabh9.riko.upstox.feed;

import com.github.rishabh9.riko.upstox.common.CallMe;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.feed.models.Feed;
import com.github.rishabh9.riko.upstox.feed.models.SubscriptionResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Optional;

public class FeedService extends Service {

    private static final Logger log = LogManager.getLogger(FeedService.class);

    /**
     * Get live feed information about a single instrument.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param exchange    Name of the exchange. <em>Mandatory.</em>
     * @param symbol      Trading symbol. <em>Mandatory.</em>
     * @param type        'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @return Instant of a live Feed
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Feed> liveFeed(@Nonnull final AccessToken accessToken,
                                   @Nonnull final ApiCredentials credentials,
                                   @Nonnull final String exchange,
                                   @Nonnull final String symbol,
                                   @Nonnull final String type)
            throws IOException {

        validatePathParameters(exchange, symbol, type);

        FeedApi api = prepareServiceApi(FeedApi.class, accessToken, credentials);

        Response<UpstoxResponse<Feed>> response = api.liveFeed(exchange, symbol, type).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Get live feed information about a single instrument, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param exchange    Name of the exchange. <em>Mandatory.</em>
     * @param symbol      Trading symbol. <em>Mandatory.</em>
     * @param type        'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param callMe      The call back interface
     */
    public void liveFeedAsync(@Nonnull final AccessToken accessToken,
                              @Nonnull final ApiCredentials credentials,
                              @Nonnull final String exchange,
                              @Nonnull final String symbol,
                              @Nonnull final String type,
                              @Nonnull final CallMe<Feed> callMe) {

        validatePathParameters(exchange, symbol, type);

        FeedApi api = prepareServiceApi(FeedApi.class, accessToken, credentials);

        api.liveFeed(exchange, symbol, type).enqueue(prepareCallback(callMe));
    }

    /**
     * Subscribe to the feed.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param type        'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange    Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv  Comma separated list of trading symbols
     * @return Confirmation response
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<SubscriptionResponse> subscribe(@Nonnull final AccessToken accessToken,
                                                    @Nonnull final ApiCredentials credentials,
                                                    @Nonnull final String type,
                                                    @Nonnull final String exchange,
                                                    @Nonnull final String symbolsCsv)
            throws IOException {

        validatePathParameters(exchange, type);

        FeedApi api = prepareServiceApi(FeedApi.class, accessToken, credentials);

        Response<UpstoxResponse<SubscriptionResponse>> response = api.subscribe(type, exchange, symbolsCsv).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Subscribe to the feed, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param type        'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange    Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv  Comma separated list of trading symbols
     * @param callMe      The call back interface
     */
    public void subscribeAsync(@Nonnull final AccessToken accessToken,
                               @Nonnull final ApiCredentials credentials,
                               @Nonnull final String type,
                               @Nonnull final String exchange,
                               @Nonnull final String symbolsCsv,
                               @Nonnull final CallMe<SubscriptionResponse> callMe) {

        validatePathParameters(exchange, type);

        FeedApi api = prepareServiceApi(FeedApi.class, accessToken, credentials);

        api.subscribe(type, exchange, symbolsCsv).enqueue(prepareCallback(callMe));
    }

    /**
     * Unsubscribe to the feed.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param type        'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange    Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv  Comma separated list of trading symbols
     * @return Confirmation response
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<SubscriptionResponse> unsubscribe(@Nonnull final AccessToken accessToken,
                                                      @Nonnull final ApiCredentials credentials,
                                                      @Nonnull final String type,
                                                      @Nonnull final String exchange,
                                                      @Nonnull final String symbolsCsv)
            throws IOException {

        validatePathParameters(exchange, type);

        FeedApi api = prepareServiceApi(FeedApi.class, accessToken, credentials);

        Response<UpstoxResponse<SubscriptionResponse>> response = api.unsubscribe(type, exchange, symbolsCsv).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Unsubscribe to the feed, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param type        'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange    Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv  Comma separated list of trading symbols
     * @param callMe      The call back interface
     */
    public void unsubscribeAsync(@Nonnull final AccessToken accessToken,
                                 @Nonnull final ApiCredentials credentials,
                                 @Nonnull final String type,
                                 @Nonnull final String exchange,
                                 @Nonnull final String symbolsCsv,
                                 @Nonnull final CallMe<SubscriptionResponse> callMe) {

        validatePathParameters(exchange, type);

        FeedApi api = prepareServiceApi(FeedApi.class, accessToken, credentials);

        api.unsubscribe(type, exchange, symbolsCsv).enqueue(prepareCallback(callMe));
    }

    private void validatePathParameters(String... values) {
        for (String value : values) {
            if (Strings.isNullOrEmpty(value)) {
                log.error("Argument validation failed. Arguments 'exchange', 'symbol' and 'type' are mandatory.");
                throw new IllegalArgumentException(
                        "Arguments 'exchange', 'symbol' and 'type' are mandatory. They cannot be null nor empty.");
            }
        }
    }
}
