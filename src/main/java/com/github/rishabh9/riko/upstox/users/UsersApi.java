package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.users.models.*;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Order API endpoints declaration.
 */
public interface UsersApi {

    /**
     * Retrieves the user's profile
     *
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/index/profile")
    CompletableFuture<UpstoxResponse<Profile>> getProfile();

    /**
     * Retrieve the profile balance.
     *
     * @param type The account type - 'security' or 'commodity'
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/profile/balance")
    CompletableFuture<UpstoxResponse<ProfileBalance>> getProfileBalance(@Query("type") String type);

    /**
     * Fetches the current positions for the user for the current day.
     *
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/profile/positions")
    CompletableFuture<UpstoxResponse<List<Position>>> getPositions();

    /**
     * Fetches the holdings which the user has bought/sold in previous trading sessions.
     *
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/profile/holdings")
    CompletableFuture<UpstoxResponse<List<Holding>>> getHoldings();

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
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @Streaming
    @GET("/index/master-contract/{exchange}")
    CompletableFuture<ResponseBody> getAllMasterContracts(@Path("exchange") String exchange);

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
     * @param symbol   Trading symbol which could be a combination of symbol name, instrument, expiry date, etc.
     *                 Optional if token is provided.
     * @param token    Unique identifier within an exchange. Optional, if symbol is provided.
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/index/master-contract/{exchange}")
    CompletableFuture<UpstoxResponse<Contract>> getMasterContract(@Path("exchange") String exchange,
                                                                  @Query("symbol") String symbol,
                                                                  @Query("token") String token);
}
