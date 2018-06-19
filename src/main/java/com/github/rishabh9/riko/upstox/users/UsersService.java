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
     * Retrieves the user's profile synchronously
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return User's profile details. It will be empty if request 'returns' an error.
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Profile> getProfile(@Nonnull final AccessToken accessToken,
                                        @Nonnull final ApiCredentials credentials)
            throws IOException {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        Response<UpstoxResponse<Profile>> response = api.getProfile().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Retrieves the user's profile asynchronously
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getProfileAsync(@Nonnull final AccessToken accessToken,
                                @Nonnull final ApiCredentials credentials,
                                @Nonnull final CallMe<Profile> callMe) {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        api.getProfile().enqueue(prepareCallback(callMe));
    }

    /**
     * Retrieve the profile balance.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param accountType The account type - 'security' or 'commodity'
     * @return the user's account balance.
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<ProfileBalance> getProfileBalance(@Nonnull final AccessToken accessToken,
                                                      @Nonnull final ApiCredentials credentials,
                                                      @Nullable final String accountType)
            throws IOException {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        Response<UpstoxResponse<ProfileBalance>> response = api.getProfileBalance(accountType).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Retrieve's profile balance asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param accountType The account type - 'security' or 'commodity'
     * @param callMe      The call back interface
     */
    public void getProfileBalanceAsync(@Nonnull final AccessToken accessToken,
                                       @Nonnull final ApiCredentials credentials,
                                       @Nullable final String accountType,
                                       @Nonnull final CallMe<ProfileBalance> callMe) {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        api.getProfileBalance(accountType).enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the current positions for the user for the current day.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return List of Position
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Position>> getPositions(@Nonnull final AccessToken accessToken,
                                                 @Nonnull final ApiCredentials credentials)
            throws IOException {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<Position>>> response = api.getPositions().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the current positions for the user for the current day, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getPositionsAsync(@Nonnull final AccessToken accessToken,
                                  @Nonnull final ApiCredentials credentials,
                                  @Nonnull final CallMe<List<Position>> callMe) {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        api.getPositions().enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the holdings which the user has bought/sold in previous trading sessions.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return List of Holding
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Holding>> getHoldings(@Nonnull final AccessToken accessToken,
                                               @Nonnull final ApiCredentials credentials)
            throws IOException {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<Holding>>> response = api.getHoldings().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the holdings which the user has bought/sold in previous trading sessions, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getHoldingsAsync(@Nonnull final AccessToken accessToken,
                                 @Nonnull final ApiCredentials credentials,
                                 @Nonnull final CallMe<List<Holding>> callMe) {

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        api.getHoldings().enqueue(prepareCallback(callMe));
    }

    /**
     * Get all available contracts as a CSV.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param exchange    Name of the exchange. <em>Mandatory</em>. Valid values are:<br/>
     *                    <ul>
     *                    <li><code>bse_index</code> - BSE Index</li>
     *                    <li><code>nse_index</code> - NSE Index</li>
     *                    <li><code>bse_eq</code> - BSE Equity</li>
     *                    <li><code>bcd_fo</code> - BSE Currency Futures & Options</li>
     *                    <li><code>nse_eq</code> - NSE Equity</li>
     *                    <li><code>nse_fo</code> - NSE Futures & Options</li>
     *                    <li><code>ncd_fo</code> - NSE Currency Futures & Options</li>
     *                    <li><code>mcx_fo</code> - MCX Futures</li>
     *                    </ul>
     * @throws IllegalArgumentException if exchange is not specified.
     *
     * @implNote
     * The CSV is returned as list of strings.
     * The first item in the list are the headers for the CSV. Rest of the items are the individual records.
     */
    public Optional<List<String>> getMasterContracts(@Nonnull final AccessToken accessToken,
                                                     @Nonnull final ApiCredentials credentials,
                                                     @Nonnull final String exchange)
            throws IOException {

        validateExchange(exchange);

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        Response<UpstoxResponse<List<String>>> response = api.getMasterContracts(exchange).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Get all available contracts as a CSV, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param exchange    Name of the exchange. <em>Mandatory</em>. Valid values are:<br/>
     *                    <ul>
     *                    <li><code>bse_index</code> - BSE Index</li>
     *                    <li><code>nse_index</code> - NSE Index</li>
     *                    <li><code>bse_eq</code> - BSE Equity</li>
     *                    <li><code>bcd_fo</code> - BSE Currency Futures & Options</li>
     *                    <li><code>nse_eq</code> - NSE Equity</li>
     *                    <li><code>nse_fo</code> - NSE Futures & Options</li>
     *                    <li><code>ncd_fo</code> - NSE Currency Futures & Options</li>
     *                    <li><code>mcx_fo</code> - MCX Futures</li>
     *                    </ul>
     * @param callMe      The call back interface
     * @throws IllegalArgumentException if exchange is not specified.
     *
     * @implNote
     * The CSV is returned as list of strings.
     * The first item in the list are the headers for the CSV. Rest of the items are the individual records.
     */
    public void getMasterContractsAsync(@Nonnull final AccessToken accessToken,
                                        @Nonnull final ApiCredentials credentials,
                                        @Nonnull final String exchange,
                                        @Nonnull final CallMe<List<String>> callMe) {

        validateExchange(exchange);

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        api.getMasterContracts(exchange).enqueue(prepareCallback(callMe));
    }

    /**
     * Get available contract for given symbol/token.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param exchange    Name of the exchange. <em>Mandatory</em>. Valid values are:<br/>
     *                    <ul>
     *                    <li><code>bse_index</code> - BSE Index</li>
     *                    <li><code>nse_index</code> - NSE Index</li>
     *                    <li><code>bse_eq</code> - BSE Equity</li>
     *                    <li><code>bcd_fo</code> - BSE Currency Futures & Options</li>
     *                    <li><code>nse_eq</code> - NSE Equity</li>
     *                    <li><code>nse_fo</code> - NSE Futures & Options</li>
     *                    <li><code>ncd_fo</code> - NSE Currency Futures & Options</li>
     *                    <li><code>mcx_fo</code> - MCX Futures</li>
     *                    </ul>
     * @param symbol      Trading symbol which could be a combination of symbol name, instrument, expiry date, etc.
     *                    Optional if token is provided.
     * @param token       Unique indentifier within an exchange. Optional, if symbol is provided.
     * @throws IllegalArgumentException if exchange is not specified. Also, if both symbol and token are null or empty.
     */
    public Optional<Contract> getMasterContract(@Nonnull final AccessToken accessToken,
                                                @Nonnull final ApiCredentials credentials,
                                                @Nonnull final String exchange,
                                                @Nullable final String symbol,
                                                @Nullable final String token)
            throws IOException {

        validateExchange(exchange);
        validateSymbolAndToken(symbol, token);

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        Response<UpstoxResponse<Contract>> response = api.getMasterContract(exchange, symbol, token).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Get available contract for given symbol/token, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param exchange    Name of the exchange. <em>Mandatory</em>. Valid values are:<br/>
     *                    <ul>
     *                    <li><code>bse_index</code> - BSE Index</li>
     *                    <li><code>nse_index</code> - NSE Index</li>
     *                    <li><code>bse_eq</code> - BSE Equity</li>
     *                    <li><code>bcd_fo</code> - BSE Currency Futures & Options</li>
     *                    <li><code>nse_eq</code> - NSE Equity</li>
     *                    <li><code>nse_fo</code> - NSE Futures & Options</li>
     *                    <li><code>ncd_fo</code> - NSE Currency Futures & Options</li>
     *                    <li><code>mcx_fo</code> - MCX Futures</li>
     *                    </ul>
     * @param symbol      Trading symbol which could be a combination of symbol name, instrument, expiry date, etc.
     *                    Optional if token is provided.
     * @param token       Unique indentifier within an exchange. Optional, if symbol is provided.
     * @param callMe      The call back interface
     * @throws IllegalArgumentException if exchange is not specified. Also, if both symbol and token are null or empty.
     */
    public void getMasterContractAsync(@Nonnull final AccessToken accessToken,
                                       @Nonnull final ApiCredentials credentials,
                                       @Nonnull final String exchange,
                                       @Nullable final String symbol,
                                       @Nullable final String token,
                                       @Nonnull final CallMe<Contract> callMe) {

        validateExchange(exchange);
        validateSymbolAndToken(symbol, token);

        UsersApi api = prepareServiceApi(UsersApi.class, accessToken, credentials);

        api.getMasterContract(exchange, symbol, token).enqueue(prepareCallback(callMe));
    }

    private void validateSymbolAndToken(final String symbol, final String token) {
        if (Strings.isNullOrEmpty(symbol) && Strings.isNullOrEmpty(token)) {
            throw new IllegalArgumentException("Provide either the 'symbol' or 'token'. Both cannot be null nor empty.");
        }
    }

    private void validateExchange(@Nonnull String exchange) {
        if (Strings.isNullOrEmpty(exchange)) {
            throw new IllegalArgumentException("Argument 'exchange' is mandatory. It cannot be null nor empty.");
        }
    }

}
