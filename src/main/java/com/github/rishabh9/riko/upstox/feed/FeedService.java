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
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public FeedService(@Nonnull AccessToken accessToken, @Nonnull ApiCredentials credentials) {
        super(accessToken, credentials);
    }

    /**
     * Get live feed information about a single instrument.
     *
     * @param exchange Name of the exchange. <em>Mandatory.</em>
     * @param symbol   Trading symbol. <em>Mandatory.</em>
     * @param type     'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @return Instant of a live Feed
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Feed> liveFeed(@Nonnull final String exchange,
                                   @Nonnull final String symbol,
                                   @Nonnull final String type)
            throws IOException {

        log.debug("Validate parameters - GET Live Feed");
        validatePathParameters(exchange, symbol, type);

        log.debug("Preparing service - GET Live Feed");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Live Feed");
        final Response<UpstoxResponse<Feed>> response =
                api.liveFeed(exchange, symbol, type).execute();

        log.debug("Finishing request - GET Live Feed");
        return completeSynchronousRequest(response);
    }

    /**
     * Get live feed information about a single instrument, asynchronously.
     *
     * @param exchange Name of the exchange. <em>Mandatory.</em>
     * @param symbol   Trading symbol. <em>Mandatory.</em>
     * @param type     'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param callMe   The call back interface
     */
    public void liveFeedAsync(@Nonnull final String exchange,
                              @Nonnull final String symbol,
                              @Nonnull final String type,
                              @Nonnull final CallMe<Feed> callMe) {

        log.debug("Validate parameters - GET Live Feed");
        validatePathParameters(exchange, symbol, type);

        log.debug("Preparing async service - GET Live Feed");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Setting up callback interface - GET Live Feed");
        api.liveFeed(exchange, symbol, type).enqueue(prepareCallback(callMe));
    }

    /**
     * Subscribe to the feed.
     *
     * @param type       'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange   Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv Comma separated list of trading symbols
     * @return Confirmation response
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<SubscriptionResponse> subscribe(@Nonnull final String type,
                                                    @Nonnull final String exchange,
                                                    @Nonnull final String symbolsCsv)
            throws IOException {

        log.debug("Validate parameters - GET Subscribe");
        validatePathParameters(exchange, type);

        log.debug("Preparing service - GET Subscribe");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Subscribe");
        final Response<UpstoxResponse<SubscriptionResponse>> response =
                api.subscribe(type, exchange, symbolsCsv).execute();

        log.debug("Finishing request - GET Subscribe");
        return completeSynchronousRequest(response);
    }

    /**
     * Subscribe to the feed, asynchronously.
     *
     * @param type       'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange   Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv Comma separated list of trading symbols
     * @param callMe     The call back interface
     */
    public void subscribeAsync(@Nonnull final String type,
                               @Nonnull final String exchange,
                               @Nonnull final String symbolsCsv,
                               @Nonnull final CallMe<SubscriptionResponse> callMe) {

        log.debug("Validate parameters - GET Subscribe");
        validatePathParameters(exchange, type);

        log.debug("Preparing async service - GET Subscribe");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Setting up callback interface - GET Subscribe");
        api.subscribe(type, exchange, symbolsCsv).enqueue(prepareCallback(callMe));
    }

    /**
     * Unsubscribe to the feed.
     *
     * @param type       'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange   Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv Comma separated list of trading symbols
     * @return Confirmation response
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<SubscriptionResponse> unsubscribe(@Nonnull final String type,
                                                      @Nonnull final String exchange,
                                                      @Nonnull final String symbolsCsv)
            throws IOException {

        log.debug("Validate parameters - GET Unsubscribe");
        validatePathParameters(exchange, type);

        log.debug("Preparing service - GET Unsubscribe");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Unsubscribe");
        final Response<UpstoxResponse<SubscriptionResponse>> response =
                api.unsubscribe(type, exchange, symbolsCsv).execute();

        log.debug("Finishing request - GET Unsubscribe");
        return completeSynchronousRequest(response);
    }

    /**
     * Unsubscribe to the feed, asynchronously.
     *
     * @param type       'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange   Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv Comma separated list of trading symbols
     * @param callMe     The call back interface
     */
    public void unsubscribeAsync(@Nonnull final String type,
                                 @Nonnull final String exchange,
                                 @Nonnull final String symbolsCsv,
                                 @Nonnull final CallMe<SubscriptionResponse> callMe) {

        log.debug("Validate parameters - GET Unsubscribe");
        validatePathParameters(exchange, type);

        log.debug("Preparing async service - GET Unsubscribe");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Setting up callback interface - GET Unsubscribe");
        api.unsubscribe(type, exchange, symbolsCsv).enqueue(prepareCallback(callMe));
    }

    private void validatePathParameters(String... values) {
        for (String value : values) {
            if (Strings.isNullOrEmpty(value)) {
                log.error("Argument validation failed. " +
                        "Arguments 'exchange', 'symbol' and 'type' are mandatory.");
                throw new IllegalArgumentException(
                        "Arguments 'exchange', 'symbol' and 'type' are mandatory. " +
                                "They cannot be null nor empty.");
            }
        }
    }
}
