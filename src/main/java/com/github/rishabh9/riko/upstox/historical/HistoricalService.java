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

import com.github.rishabh9.riko.upstox.common.RetryPolicyFactory;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.historical.models.Candle;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import net.jodah.failsafe.Failsafe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.github.rishabh9.riko.upstox.common.constants.RateLimits.HISTORICAL_RATE_LIMIT;

@SuppressWarnings("UnstableApiUsage")
public class HistoricalService extends Service {

    private static final Logger log = LogManager.getLogger(HistoricalService.class);

    private static final RateLimiter historicalRateLimiter = RateLimiter.create(HISTORICAL_RATE_LIMIT);

    /**
     * @param upstoxAuthService The service to retrieve authentication details
     */
    public HistoricalService(@Nonnull final UpstoxAuthService upstoxAuthService,
                             @Nonnull final RetryPolicyFactory retryPolicyFactory) {

        super(upstoxAuthService, retryPolicyFactory);
    }

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
     * @param startDate Date in the format: <code>DD-MM-YYYY</code>.
     *                  Default value is 7 days before today.
     * @param endDate   Date in the format: <code>DD-MM-YYYY</code>.
     *                  Default value is today.
     * @return List of Candle
     */
    public CompletableFuture<UpstoxResponse<List<Candle>>> getOhlc(@Nonnull final String exchange,
                                                                   @Nonnull final String symbol,
                                                                   @Nonnull final String interval,
                                                                   final String startDate,
                                                                   final String endDate) {

        log.debug("Validate parameters - GET OHLC");
        validatePathParameters(exchange, symbol, interval);

        log.debug("Preparing service - GET OHLC");
        final HistoricalApi api = prepareServiceApi(HistoricalApi.class);

        log.debug("Making request - GET OHLC");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET OHLC.", failure))
                .onSuccess(response -> log.debug("GET OHLC successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET OHLC, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    historicalRateLimiter.acquire(1);
                    return api.getOhlc(exchange, symbol, interval, startDate, endDate, "json");
                });
    }

    private void validatePathParameters(String... values) {
        for (String value : values) {
            if (Strings.isNullOrEmpty(value)) {
                log.error("Argument validation failed. Arguments 'exchange', 'symbol' and 'interval' are mandatory.");
                throw new IllegalArgumentException(
                        "Arguments 'exchange', 'symbol' and 'interval' are mandatory. They cannot be null nor empty.");
            }
        }
    }
}
