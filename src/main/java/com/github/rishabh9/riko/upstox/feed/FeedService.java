/*
 * MIT License
 *
 * Copyright (c) 2018 Rishabh Joshi
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

import com.github.rishabh9.riko.upstox.common.RetryPolicyFactory;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.feed.models.Feed;
import com.github.rishabh9.riko.upstox.feed.models.Subscription;
import com.github.rishabh9.riko.upstox.feed.models.SubscriptionResponse;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import net.jodah.failsafe.Failsafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

import static com.github.rishabh9.riko.upstox.common.constants.RateLimits.*;

@SuppressWarnings("UnstableApiUsage")
public class FeedService extends Service {

    private static final Logger log = LogManager.getLogger(FeedService.class);

    private static final RateLimiter liveFeedRateLimiter = RateLimiter.create(LIVE_FEED_RATE_LIMIT);
    private static final RateLimiter subscribeRateLimiter = RateLimiter.create(SUBSCRIBE_RATE_LIMIT);
    private static final RateLimiter unsubscribeRateLimiter = RateLimiter.create(UNSUBSCRIBE_RATE_LIMIT);
    private static final RateLimiter symbolsSubscribedRateLimiter = RateLimiter.create(SYMBOLS_SUBSCRIBED_RATE_LIMIT);

    /**
     * @param upstoxAuthService The service to retrieve authentication details
     */
    public FeedService(@Nonnull final UpstoxAuthService upstoxAuthService,
                       @Nonnull final RetryPolicyFactory retryPolicyFactory) {

        super(upstoxAuthService, retryPolicyFactory);
    }

    /**
     * Get live feed information about a single instrument.
     *
     * @param exchange Name of the exchange. <em>Mandatory.</em>
     * @param symbol   Trading symbol. <em>Mandatory.</em>
     * @param type     'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @return Instant of a live Feed
     */
    public CompletableFuture<UpstoxResponse<Feed>> liveFeed(@Nonnull final String exchange,
                                                            @Nonnull final String symbol,
                                                            @Nonnull final String type) {

        log.debug("Validate parameters - GET Live Feed");
        validatePathParameters(exchange, symbol, type);

        log.debug("Preparing service - GET Live Feed");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Live Feed");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Live Feed.", failure))
                .onSuccess(response -> log.debug("GET Live Feed successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Live Feed, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    liveFeedRateLimiter.acquire(1);
                    return api.liveFeed(exchange, symbol, type);
                });
    }

    /**
     * Subscribe to the feed.
     *
     * @param type       'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange   Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv Comma separated list of trading symbols
     * @return Confirmation response
     */
    public CompletableFuture<UpstoxResponse<SubscriptionResponse>> subscribe(@Nonnull final String type,
                                                                             @Nonnull final String exchange,
                                                                             @Nonnull final String symbolsCsv) {

        log.debug("Validate parameters - GET Subscribe");
        validatePathParameters(exchange, type, symbolsCsv);

        log.debug("Preparing service - GET Subscribe");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Subscribe");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Subscribe.", failure))
                .onSuccess(response -> log.debug("GET Subscribe successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Subscribe, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    subscribeRateLimiter.acquire(1);
                    return api.subscribe(type, exchange, symbolsCsv);
                });
    }

    /**
     * Unsubscribe to the feed.
     *
     * @param type       'ltp' or 'full'. <em>Either. Mandatory.</em>
     * @param exchange   Name of the exchange. <em>Mandatory.</em>
     * @param symbolsCsv Comma separated list of trading symbols
     * @return Confirmation response
     */
    public CompletableFuture<UpstoxResponse<SubscriptionResponse>> unsubscribe(@Nonnull final String type,
                                                                               @Nonnull final String exchange,
                                                                               @Nonnull final String symbolsCsv) {

        log.debug("Validate parameters - GET Unsubscribe");
        validatePathParameters(exchange, type, symbolsCsv);

        log.debug("Preparing service - GET Unsubscribe");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Unsubscribe");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Unsubscribe.", failure))
                .onSuccess(response -> log.debug("GET Unsubscribe successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Unsubscribe, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    unsubscribeRateLimiter.acquire(1);
                    return api.unsubscribe(type, exchange, symbolsCsv);
                });
    }


    /**
     * Get list of symbols subscribed.
     *
     * @param type 'all' or 'ltp' or 'full'. <em>Any. Mandatory.</em>
     * @return List of symbols subscribed
     */
    public CompletableFuture<UpstoxResponse<Subscription>> symbolsSubscribed(@Nonnull final String type) {

        log.debug("Validate parameters - GET Symbols Subscribed");
        validatePathParameter(type);

        log.debug("Preparing service - GET Symbols Subscribed");
        final FeedApi api = prepareServiceApi(FeedApi.class);

        log.debug("Making request - GET Symbols Subscribed");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Symbols Subscribed.", failure))
                .onSuccess(response -> log.debug("GET Symbols Subscribed successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Symbols Subscribed, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    symbolsSubscribedRateLimiter.acquire(1);
                    return api.symbolsSubscribed(type);
                });
    }

    private void validatePathParameters(String... values) {
        for (String value : values) {
            if (Strings.isNullOrEmpty(value)) {
                log.error("Argument validation failed. " +
                        "Arguments 'exchange', 'symbol(s)' and 'type' are mandatory.");
                throw new IllegalArgumentException(
                        "Arguments 'exchange', 'symbol(s)' and 'type' are mandatory. " +
                                "They cannot be null nor empty.");
            }
        }
    }

    private void validatePathParameter(String value) {
        if (Strings.isNullOrEmpty(value)) {
            log.error("Argument validation failed. Arguments 'type' is mandatory.");
            throw new IllegalArgumentException("Argument validation failed. Arguments 'type' is mandatory.");
        }
    }
}
