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

package com.github.rishabh9.riko.upstox.historical;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.historical.models.Candle;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.google.gson.Gson;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class HistoricalServiceTest {

    private static final Logger log = LogManager.getLogger(HistoricalServiceTest.class);

    private UpstoxAuthService upstoxAuthService = new UpstoxAuthService() {
        @Override
        public ApiCredentials getApiCredentials() {
            return new ApiCredentials("secretApiKey", "secret-secret");
        }

        @Override
        public AccessToken getAccessToken() {
            AccessToken token = new AccessToken();
            token.setExpiresIn(86400L);
            token.setType("Bearer");
            token.setToken("access_token_123456789");
            return token;
        }
    };

    @Test
    void getOhlc_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Candle candle = new Candle();
        candle.setVolume(2345L);
        List<Candle> candles = new ArrayList<>(1);
        candles.add(candle);
        UpstoxResponse<List<Candle>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(candles);

        server.enqueue(
                new MockResponse().setBody(
                        new Gson().toJson(response)));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        HistoricalService service = new HistoricalService(upstoxAuthService);

        try {
            UpstoxResponse<List<Candle>> serverResponse =
                    service.getOhlc("NSE", "RELIANCE", "", "", "")
                            .get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getOhlc_failure_whenUpstoxReturnsError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("{\"code\":400," +
                        "\"status\":\"Bad Request\"," +
                        "\"timestamp\":\"2018-06-19T20:11:57+05:30\"," +
                        "\"message\":\"Random error\"," +
                        "\"error\":{\"name\":\"Error\",\"reason\":\"Random error\"}}"));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        HistoricalService service = new HistoricalService(upstoxAuthService);

        assertThrows(ExecutionException.class,
                service.getOhlc("NSE", "RELIANCE", null, null, null)::get);

        server.shutdown();
    }

    @Test
    void getOhlc_failure_onNetworkError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        HistoricalService service = new HistoricalService(upstoxAuthService);

        try {
            service.getOhlc("NSE", "RELIANCE", null, null, null).get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getOhlc_throwIAE_whenRequiredParametersAreMissing() {
        HistoricalService service = new HistoricalService(upstoxAuthService);

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOhlc(null, null, null, null, null),
                "Arguments cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOhlc(null, "ACC", null, null, null),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOhlc("NSE", null, null, null, null),
                "Symbol cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOhlc("", "ACC", null, null, null),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOhlc("NSE", "", null, null, null),
                "Symbol cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOhlc("", "", null, null, null),
                "Arguments cannot be empty. Mandatory validation missing.");
    }

    @Test
    void getOhlc_throwNPE_whenServiceParametersAreMissing() {

        assertThrows(NullPointerException.class,
                () -> new HistoricalService(null),
                "Null check missing for 'UpstoxAuthService' from HistoricalService constructor");
    }
}