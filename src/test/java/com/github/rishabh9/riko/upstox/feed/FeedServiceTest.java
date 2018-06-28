package com.github.rishabh9.riko.upstox.feed;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.feed.models.Feed;
import com.github.rishabh9.riko.upstox.feed.models.SubscriptionResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.google.gson.Gson;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class FeedServiceTest {

    private static final Logger log = LogManager.getLogger(FeedServiceTest.class);

    @Test
    void liveFeed_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Feed feed = new Feed();
        feed.setExchange("NSE");
        UpstoxResponse<Feed> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(feed);

        server.enqueue(
                new MockResponse().setBody(
                        new Gson().toJson(response)));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        try {
            UpstoxResponse<Feed> serverResponse =
                    service.liveFeed("NSE", "RELIANCE", "TYPE")
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
    void liveFeed_failure_whenUpstoxReturnsError() throws IOException {
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
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        assertThrows(ExecutionException.class,
                service.liveFeed("NSE", "RELIANCE", "TYPE")::get);

        server.shutdown();
    }

    @Test
    void liveFeed_failure_onNetworkError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        try {
            service.liveFeed("NSE", "RELIANCE", "TYPE").get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void liveFeed_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        FeedService service = new FeedService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed(null, null, null),
                "Arguments cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed("NSE", "ACC", null),
                "Type cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed("NSE", null, "TYPE"),
                "Symbol cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed(null, "ACC", "TYPE"),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed("", "", ""),
                "Arguments cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed("", "ACC", "TYPE"),
                "Exchange cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed("NSE", "", "TYPE"),
                "Symbol cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.liveFeed("NSE", "ACC", ""),
                "Type cannot be empty. Mandatory validation missing.");
    }

    @Test
    void liveFeed_throwNPE_whenServiceParametersAreMissing() {

        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        assertThrows(NullPointerException.class,
                () -> new FeedService(null, credentials),
                "Null check missing for 'AccessToken' from FeedService constructor");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        assertThrows(NullPointerException.class,
                () -> new FeedService(token, null),
                "Null check missing for 'ApiCredentials' from FeedService constructor");
    }

    @Test
    void subscribe_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        SubscriptionResponse sr = new SubscriptionResponse();
        sr.setExchange("NSE");
        UpstoxResponse<SubscriptionResponse> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(sr);

        server.enqueue(
                new MockResponse().setBody(
                        new Gson().toJson(response)));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        try {
            UpstoxResponse<SubscriptionResponse> subResponse =
                    service.subscribe("TYPE", "NSE", "RELIANCE,ACC")
                            .get();
            assertEquals(response, subResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void subscribe_failure_whenUpstoxReturnsError() throws IOException {
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
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        assertThrows(ExecutionException.class,
                service.subscribe("TYPE", "NSE", "ACC,RELIANCE")::get);

        server.shutdown();
    }

    @Test
    void subscribe_failure_onNetworkError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        try {
            service.subscribe("TYPE", "NSE", "RELIANCE,ACC").get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void subscribe_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        FeedService service = new FeedService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe(null, null, null),
                "Arguments cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe(null, "NSE", "ACC,SBI"),
                "Type cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe("TYPE", null, "TYPE"),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe("TYPE", "ACC", null),
                "Symbols cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe("", "", ""),
                "Arguments cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe("", "NSE", "ACC,SBI"),
                "Type cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe("TYPE", "", "ACC,SBI"),
                "Exchange cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.subscribe("TYPE", "NSE", ""),
                "Symbols cannot be empty. Mandatory validation missing.");
    }

    @Test
    void unsubscribe_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        SubscriptionResponse sr = new SubscriptionResponse();
        sr.setExchange("NSE");
        UpstoxResponse<SubscriptionResponse> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(sr);

        server.enqueue(
                new MockResponse().setBody(
                        new Gson().toJson(response)));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        try {
            UpstoxResponse<SubscriptionResponse> unsubResponse =
                    service.unsubscribe("TYPE", "NSE", "RELIANCE,ACC")
                            .get();
            assertEquals(response, unsubResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void unsubscribe_failure_whenUpstoxReturnsError() throws IOException {
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
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        assertThrows(ExecutionException.class,
                service.unsubscribe("TYPE", "NSE", "ACC,RELIANCE")::get);

        server.shutdown();
    }

    @Test
    void unsubscribe_failure_onNetworkError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");
        FeedService service = new FeedService(token, credentials);

        try {
            service.unsubscribe("TYPE", "NSE", "RELIANCE,ACC").get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void unsubscribe_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        FeedService service = new FeedService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe(null, null, null),
                "Arguments cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe(null, "NSE", "ACC,SBI"),
                "Type cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe("TYPE", null, "TYPE"),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe("TYPE", "ACC", null),
                "Symbols cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe("", "", ""),
                "Arguments cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe("", "NSE", "ACC,SBI"),
                "Type cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe("TYPE", "", "ACC,SBI"),
                "Exchange cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.unsubscribe("TYPE", "NSE", ""),
                "Symbols cannot be empty. Mandatory validation missing.");
    }
}