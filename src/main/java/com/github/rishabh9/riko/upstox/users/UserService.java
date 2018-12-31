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

package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.RetryPolicyFactory;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.users.models.*;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.RateLimiter;
import net.jodah.failsafe.Failsafe;
import okhttp3.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.github.rishabh9.riko.upstox.common.constants.RateLimits.*;

@SuppressWarnings("UnstableApiUsage")
public class UserService extends Service {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private static final RateLimiter profileRateLimiter = RateLimiter.create(PROFILE_RATE_LIMIT);
    private static final RateLimiter balanceRateLimiter = RateLimiter.create(BALANCE_RATE_LIMIT);
    private static final RateLimiter positionsRateLimiter = RateLimiter.create(POSITIONS_RATE_LIMIT);
    private static final RateLimiter holdingsRateLimiter = RateLimiter.create(HOLDINGS_RATE_LIMIT);
    private static final RateLimiter masterContractRateLimiter = RateLimiter.create(MASTER_CONTRACT_RATE_LIMIT);

    /**
     * @param upstoxAuthService The service to retrieve authentication details
     */
    public UserService(@Nonnull final UpstoxAuthService upstoxAuthService,
                       @Nonnull final RetryPolicyFactory retryPolicyFactory) {

        super(upstoxAuthService, retryPolicyFactory);
    }

    /**
     * Retrieves the user's profile
     *
     * @return User's profile details. It will be empty if request 'returns' an error.
     */
    public CompletableFuture<UpstoxResponse<Profile>> getProfile() {

        log.debug("Preparing service - GET Profile");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Profile");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Profile.", failure))
                .onSuccess(response -> log.debug("GET Profile successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Profile, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    profileRateLimiter.acquire(1);
                    return api.getProfile();
                });
    }

    /**
     * Retrieve the profile balance.
     *
     * @param accountType The account type - 'security' or 'commodity'
     * @return the user's account balance.
     */
    public CompletableFuture<UpstoxResponse<ProfileBalance>> getProfileBalance(
            @Nullable final String accountType) {

        log.debug("Preparing service - GET Profile Balance");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Profile Balance");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Profile Balance.", failure))
                .onSuccess(response -> log.debug("GET Profile Balance successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Profile Balance, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    balanceRateLimiter.acquire(1);
                    return api.getProfileBalance(accountType);
                });
    }

    /**
     * Fetches the current positions for the user for the current day.
     *
     * @return List of Position
     */
    public CompletableFuture<UpstoxResponse<List<Position>>> getPositions() {

        log.debug("Preparing service - GET Positions");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Positions");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Positions.", failure))
                .onSuccess(response -> log.debug("GET Positions successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Positions, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    positionsRateLimiter.acquire(1);
                    return api.getPositions();
                });
    }

    /**
     * Fetches the holdings which the user has bought/sold in previous trading sessions.
     *
     * @return List of Holding
     */
    public CompletableFuture<UpstoxResponse<List<Holding>>> getHoldings() {

        log.debug("Preparing service - GET Holdings");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Holdings");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Holdings.", failure))
                .onSuccess(response -> log.debug("GET Holdings successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Holdings, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    holdingsRateLimiter.acquire(1);
                    return api.getHoldings();
                });
    }

    /**
     * Get all available contracts as a Stream.
     *
     * @param exchange Name of the exchange. <em>Mandatory</em>. Valid values are:<br/>
     *                 <ul>
     *                 <li><code>bse_index</code> - BSE Index</li>
     *                 <li><code>nse_index</code> - NSE Index</li>
     *                 <li><code>bse_eq</code> - BSE Equity</li>
     *                 <li><code>bcd_fo</code> - BSE Currency Futures & Options</li>
     *                 <li><code>nse_eq</code> - NSE Equity</li>
     *                 <li><code>nse_fo</code> - NSE Futures & Options</li>
     *                 <li><code>ncd_fo</code> - NSE Currency Futures & Options</li>
     *                 <li><code>mcx_fo</code> - MCX Futures</li>
     *                 </ul>
     * @return The Json response from Upstox as a Stream, because the Json could be very large.
     * @throws IllegalArgumentException if exchange is not specified.
     * @implNote You'll need to extract the <em>data</em> element from the stream.
     * The <em>data</em> element is a list of strings.
     * The first item in the list contains the headers for the CSV.
     * Rest of the items in the list are the individual CSV records.
     */
    public CompletableFuture<InputStream> getAllMasterContracts(@Nonnull final String exchange) {

        log.debug("Validate parameters - GET All Contracts");
        validateExchange(exchange);

        log.debug("Preparing service - GET All Contracts");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET All Contracts");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET All Contracts.", failure))
                .onSuccess(response -> log.debug("GET All Contracts successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET All Contracts, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    masterContractRateLimiter.acquire(1);
                    return api.getAllMasterContracts(exchange);
                })
                .thenApply(ResponseBody::byteStream);
    }

    /**
     * Get available contract for given symbol/token.
     *
     * @param exchange Name of the exchange. <em>Mandatory</em>. Valid values are:<br/>
     *                 <ul>
     *                 <li><code>bse_index</code> - BSE Index</li>
     *                 <li><code>nse_index</code> - NSE Index</li>
     *                 <li><code>bse_eq</code> - BSE Equity</li>
     *                 <li><code>bcd_fo</code> - BSE Currency Futures & Options</li>
     *                 <li><code>nse_eq</code> - NSE Equity</li>
     *                 <li><code>nse_fo</code> - NSE Futures & Options</li>
     *                 <li><code>ncd_fo</code> - NSE Currency Futures & Options</li>
     *                 <li><code>mcx_fo</code> - MCX Futures</li>
     *                 </ul>
     * @param symbol   Trading symbol which could be a combination of symbol name,
     *                 instrument, expiry date, etc. Optional if token is provided.
     * @param token    Unique indentifier within an exchange. Optional, if symbol is provided.
     * @throws IllegalArgumentException if exchange is not specified.
     *                                  Also, if both symbol and token are null or empty.
     */
    public CompletableFuture<UpstoxResponse<Contract>> getMasterContract(@Nonnull final String exchange,
                                                                         final String symbol,
                                                                         final String token) {

        log.debug("Validate parameters - GET Contract");
        validateExchange(exchange);
        validateSymbolAndToken(symbol, token);

        log.debug("Preparing service - GET Contract");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Contract");
        return Failsafe.with(retryPolicy)
                .with(retryExecutor)
                .onFailure(failure -> log.fatal("Failed completely to GET Contract.", failure))
                .onSuccess(response -> log.debug("GET Contract successful!", response))
                .onRetry((c, f, ctx) ->
                        log.warn("Failure #" + ctx.getExecutions()
                                + ". Unable to GET Contract, retrying. REASON: {}", f.getCause().getMessage()))
                .future(() -> {
                    masterContractRateLimiter.acquire(1);
                    return api.getMasterContract(exchange, symbol, token);
                });
    }

    private void validateSymbolAndToken(final String symbol, final String token) {
        if (Strings.isNullOrEmpty(symbol) && Strings.isNullOrEmpty(token)) {
            log.error("Argument validation failed. Either 'symbol' or 'token' must be specified.");
            throw new IllegalArgumentException("Provide either the 'symbol' or 'token'. Both cannot be null nor empty.");
        }
    }

    private void validateExchange(String exchange) {
        if (Strings.isNullOrEmpty(exchange)) {
            log.error("Argument validation failed. Argument 'exchange' is mandatory.");
            throw new IllegalArgumentException("Argument 'exchange' is mandatory. It cannot be null nor empty.");
        }
    }

}
