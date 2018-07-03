package com.github.rishabh9.riko.upstox.historical;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
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

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        HistoricalService service = new HistoricalService(token, credentials);

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

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        HistoricalService service = new HistoricalService(token, credentials);

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

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        HistoricalService service = new HistoricalService(token, credentials);

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
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        HistoricalService service = new HistoricalService(token, credentials);

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

        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        assertThrows(NullPointerException.class,
                () -> new HistoricalService(null, credentials),
                "Null check missing for 'AccessToken' from HistoricalService constructor");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        assertThrows(NullPointerException.class,
                () -> new HistoricalService(token, null),
                "Null check missing for 'ApiCredentials' from HistoricalService constructor");
    }
}