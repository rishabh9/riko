package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.CallMe;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.users.models.*;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UsersService extends Service {

    private static final Logger log = LogManager.getLogger(UsersService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public UsersService(@Nonnull AccessToken accessToken, @Nonnull ApiCredentials credentials) {
        super(accessToken, credentials);
    }

    /**
     * Retrieves the user's profile
     *
     * @return User's profile details. It will be empty if request 'returns' an error.
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Profile> getProfile()
            throws IOException {

        log.debug("Preparing service - GET Profile");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Profile");
        final Response<UpstoxResponse<Profile>> response = api.getProfile().execute();

        log.debug("Finishing request - GET Profile");
        return completeSynchronousRequest(response);
    }

    /**
     * Retrieves the user's profile asynchronously
     *
     * @param callMe The call back interface
     */
    public void getProfileAsync(@Nonnull final CallMe<Profile> callMe) {

        log.debug("Preparing async service - GET Profile");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Setting up callback interface - GET Profile");
        api.getProfile().enqueue(prepareCallback(callMe));
    }

    /**
     * Retrieve the profile balance.
     *
     * @param accountType The account type - 'security' or 'commodity'
     * @return the user's account balance.
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<ProfileBalance> getProfileBalance(@Nullable final String accountType)
            throws IOException {

        log.debug("Preparing service - GET Profile Balance");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Profile Balance");
        final Response<UpstoxResponse<ProfileBalance>> response =
                api.getProfileBalance(accountType).execute();

        log.debug("Finishing request - GET Profile Balance");
        return completeSynchronousRequest(response);
    }

    /**
     * Retrieve's profile balance asynchronously.
     *
     * @param accountType The account type - 'security' or 'commodity'
     * @param callMe      The call back interface
     */
    public void getProfileBalanceAsync(@Nullable final String accountType,
                                       @Nonnull final CallMe<ProfileBalance> callMe) {

        log.debug("Preparing async service - GET Profile Balance");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Setting up callback interface - GET Profile Balance");
        api.getProfileBalance(accountType).enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the current positions for the user for the current day.
     *
     * @return List of Position
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Position>> getPositions()
            throws IOException {

        log.debug("Preparing service - GET Positions");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Positions");
        final Response<UpstoxResponse<List<Position>>> response = api.getPositions().execute();

        log.debug("Finishing request - GET Positions");
        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the current positions for the user for the current day, asynchronously.
     *
     * @param callMe The call back interface
     */
    public void getPositionsAsync(@Nonnull final CallMe<List<Position>> callMe) {

        log.debug("Preparing async service - GET Positions");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Setting up callback interface - GET Positions");
        api.getPositions().enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the holdings which the user has bought/sold in previous trading sessions.
     *
     * @return List of Holding
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Holding>> getHoldings() throws IOException {

        log.debug("Preparing service - GET Holdings");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Holdings");
        final Response<UpstoxResponse<List<Holding>>> response = api.getHoldings().execute();

        log.debug("Finishing request - GET Holdings");
        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the holdings which the user has bought/sold in previous trading sessions, asynchronously.
     *
     * @param callMe The call back interface
     */
    public void getHoldingsAsync(@Nonnull final CallMe<List<Holding>> callMe) {

        log.debug("Preparing async service - GET Holdings");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Setting up callback interface - GET Holdings");
        api.getHoldings().enqueue(prepareCallback(callMe));
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
    public Optional<List<String>> getAllMasterContracts(@Nonnull final String exchange)
            throws IOException {

        log.debug("Validate parameters - GET All Contracts");
        validateExchange(exchange);

        log.debug("Preparing service - GET All Contracts");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET All Contracts");
        final Response<UpstoxResponse<List<String>>> response =
                api.getAllMasterContracts(exchange).execute();

        log.debug("Finishing request - GET All Contracts");
        return completeSynchronousRequest(response);
    }

    /**
     * Get all available contracts as a CSV, asynchronously.
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
     * @param callMe   The call back interface
     * @throws IllegalArgumentException if exchange is not specified.
     * @implNote The CSV is returned as list of strings.
     * The first item in the list are the headers for the CSV.
     * Rest of the items are the individual records.
     */
    public void getAllMasterContractsAsync(@Nonnull final String exchange,
                                           @Nonnull final CallMe<List<String>> callMe) {

        log.debug("Validate parameters - GET All Contracts");
        validateExchange(exchange);

        log.debug("Preparing async service - GET All Contracts");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Setting up callback interface - GET All Contracts");
        api.getAllMasterContracts(exchange).enqueue(prepareCallback(callMe));
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
    public Optional<Contract> getMasterContract(@Nonnull final String exchange,
                                                @Nullable final String symbol,
                                                @Nullable final String token)
            throws IOException {

        log.debug("Validate parameters - GET Contract");
        validateExchange(exchange);
        validateSymbolAndToken(symbol, token);

        log.debug("Preparing service - GET Contract");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Making request - GET Contract");
        final Response<UpstoxResponse<Contract>> response =
                api.getMasterContract(exchange, symbol, token).execute();

        log.debug("Finishing request - GET Master Contract");
        return completeSynchronousRequest(response);
    }

    /**
     * Get available contract for given symbol/token, asynchronously.
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
     * @param symbol   Trading symbol which could be a combination of symbol name, instrument,
     *                 expiry date, etc. Optional if token is provided.
     * @param token    Unique identifier within an exchange. Optional, if symbol is provided.
     * @param callMe   The call back interface
     * @throws IllegalArgumentException if exchange is not specified.
     *                                  Also, if both symbol and token are null or empty.
     */
    public void getMasterContractAsync(@Nonnull final String exchange,
                                       @Nullable final String symbol,
                                       @Nullable final String token,
                                       @Nonnull final CallMe<Contract> callMe) {

        log.debug("Validate parameters - GET Contract");
        validateExchange(exchange);
        validateSymbolAndToken(symbol, token);

        log.debug("Preparing async service - GET Contract");
        final UsersApi api = prepareServiceApi(UsersApi.class);

        log.debug("Setting up callback interface - GET Contract");
        api.getMasterContract(exchange, symbol, token).enqueue(prepareCallback(callMe));
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
