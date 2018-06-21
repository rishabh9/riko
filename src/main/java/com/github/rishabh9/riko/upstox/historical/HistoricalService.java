package com.github.rishabh9.riko.upstox.historical;

import com.github.rishabh9.riko.upstox.common.CallMe;
import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.historical.models.Candle;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HistoricalService extends Service {

    private static final Logger log = LogManager.getLogger(HistoricalService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public HistoricalService(@Nonnull AccessToken accessToken, @Nonnull ApiCredentials credentials) {
        super(accessToken, credentials);
    }

    /**
     * Get OHLC data.
     *
     * @param exchange  Name of the exchange. <em>Mandatory.</em>
     * @param symbol    Trading symbol. <em>Mandatory.</em>
     * @param interval  Allowed Values:
     *                  <ul>
     *                  <li><code>1MINUTE</code></li>
     *                  <li><code>5MINUTE</code></li>
     *                  <li><code>10MINUTE</code></li>
     *                  <li><code>30MINUTE</code></li>
     *                  <li><code>60MINUTE</code></li>
     *                  <li><code>1DAY</code> <em>(default)</em></li>
     *                  <li><code>1WEEK</code></li>
     *                  <li><code>1MONTH</code></li>
     *                  </ul>
     * @param startDate Date in the format: <code>DD-MM-YYYY</code>.
     *                  Default value is 15 days before today.
     * @param endDate   Date in the format: <code>DD-MM-YYYY</code>.
     *                  Default value is today.
     * @return List of Candle
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Candle>> getOhlc(@Nonnull final String exchange,
                                          @Nonnull final String symbol,
                                          final String interval,
                                          final String startDate,
                                          final String endDate)
            throws IOException {

        log.debug("Validate parameters - GET OHLC");
        validatePathParameters(exchange, symbol);

        log.debug("Preparing service - GET OHLC");
        final HistoricalApi api = prepareServiceApi(HistoricalApi.class);

        log.debug("Making request - GET OHLC");
        final Response<UpstoxResponse<List<Candle>>> response =
                api.getOhlc(exchange, symbol, interval, startDate, endDate, "json").execute();

        log.debug("Finishing request - GET OHLC");
        return completeSynchronousRequest(response);
    }

    /**
     * Get OHLC data, asynchronously.
     *
     * @param exchange  Name of the exchange. <em>Mandatory.</em>
     * @param symbol    Trading symbol. <em>Mandatory.</em>
     * @param interval  Allowed Values:
     *                  <ul>
     *                  <li><code>1MINUTE</code></li>
     *                  <li><code>5MINUTE</code></li>
     *                  <li><code>10MINUTE</code></li>
     *                  <li><code>30MINUTE</code></li>
     *                  <li><code>60MINUTE</code></li>
     *                  <li><code>1DAY</code> (default)</li>
     *                  <li><code>1WEEK</code></li>
     *                  <li><code>1MONTH</code></li>
     *                  </ul>
     * @param startDate Date in the format: <code>DD-MM-YYYY</code>.
     *                  Default value is 15 days before today.
     * @param endDate   Date in the format: <code>DD-MM-YYYY</code>.
     *                  Default value is today.
     * @param callMe    The call back interface
     */
    public void getOhlcAsync(@Nonnull final String exchange,
                             @Nonnull final String symbol,
                             final String interval,
                             final String startDate,
                             final String endDate,
                             final CallMe<List<Candle>> callMe) {

        log.debug("Validate parameters - GET OHLC");
        validatePathParameters(exchange, symbol);

        log.debug("Preparing async service - GET OHLC");
        final HistoricalApi api = prepareServiceApi(HistoricalApi.class);

        log.debug("Setting up callback interface - GET OHLC");
        api.getOhlc(exchange, symbol, interval, startDate, endDate, "json").enqueue(prepareCallback(callMe));
    }

    private void validatePathParameters(String... values) {
        for (String value : values) {
            if (Strings.isNullOrEmpty(value)) {
                log.error("Argument validation failed. Arguments 'exchange' and 'symbol' are mandatory.");
                throw new IllegalArgumentException(
                        "Arguments 'exchange' and 'symbol' are mandatory. They cannot be null nor empty.");
            }
        }
    }
}
