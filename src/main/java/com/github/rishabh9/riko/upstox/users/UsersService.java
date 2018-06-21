package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.users.models.*;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UsersService extends Service {

    private static final Logger log = LogManager.getLogger(UsersService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public UsersService(@Nonnull final AccessToken accessToken, @Nonnull final ApiCredentials credentials) {
        super(accessToken, credentials);
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
        return api.getProfile();
    }

    /**
     * Retrieve the profile balance.
     *
     * @param accountType The account type - 'security' or 'commodity'
     * @return the user's account balance.
     */
    public CompletableFuture<UpstoxResponse<ProfileBalance>> getProfileBalance(@Nullable final String accountType) {

        log.debug("Preparing service - GET Profile Balance");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Profile Balance");
        return api.getProfileBalance(accountType);
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
        return api.getPositions();
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
        return api.getHoldings();
    }

    /**
     * Get all available contracts as a CSV.
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
     * @throws IllegalArgumentException if exchange is not specified.
     * @implNote The CSV is returned as list of strings.
     * The first item in the list are the headers for the CSV.
     * Rest of the items are the individual records.
     */
    public CompletableFuture<UpstoxResponse<List<String>>> getAllMasterContracts(@Nonnull final String exchange) {

        log.debug("Validate parameters - GET All Contracts");
        validateExchange(exchange);

        log.debug("Preparing service - GET All Contracts");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET All Contracts");
        return api.getAllMasterContracts(exchange);
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
                                                                         @Nullable final String symbol,
                                                                         @Nullable final String token) {

        log.debug("Validate parameters - GET Contract");
        validateExchange(exchange);
        validateSymbolAndToken(symbol, token);

        log.debug("Preparing service - GET Contract");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Contract");
        return api.getMasterContract(exchange, symbol, token);
    }

    private void validateSymbolAndToken(final String symbol, final String token) {
        if (Strings.isNullOrEmpty(symbol) && Strings.isNullOrEmpty(token)) {
            log.error("Argument validation failed. Either 'symbol' or 'token' must be specified.");
            throw new IllegalArgumentException("Provide either the 'symbol' or 'token'. Both cannot be null nor empty.");
        }
    }

    private void validateExchange(@Nonnull String exchange) {
        if (Strings.isNullOrEmpty(exchange)) {
            log.error("Argument validation failed. Argument 'exchange' is mandatory.");
            throw new IllegalArgumentException("Argument 'exchange' is mandatory. It cannot be null nor empty.");
        }
    }

}
