package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.common.TestHelper;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private static final Logger log = LogManager.getLogger(LoginServiceTest.class);

    @Test
    void getAccessToken_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse()
                .setBody("{\"access_token\":\"access_token_123456789\", " +
                        "\"expires_in\": 86400, " +
                        "\"token_type\": \"bearer\"}"));

        server.start();

        HttpUrl baseUrl = server.url("/unit/tests/");
        TestHelper.getInstance().setBaseUrl(baseUrl);

        TokenRequest request = new TokenRequest("authorization_code_123456789",
                "authorization_code", "http://localhost:4567/hello");

        ApiCredentials credentials = new ApiCredentials("secretApiKey", "secret-secret");

        LoginService service = new LoginService(request, credentials);
        try {
            AccessToken accessToken = service.getAccessToken();

            assertNotNull(accessToken);
            assertEquals(86400, accessToken.getExpiresIn());
            assertEquals("Bearer", accessToken.getType());
            assertEquals("access_token_123456789", accessToken.getToken());
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
        }

        // Shut down the server. Instances cannot be reused.
        server.shutdown();
    }

    @Test
    void getAccessToken_errorOut_whenUpstoxReturnsError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody("{\"code\":400," +
                        "\"status\":\"Bad Request\"," +
                        "\"timestamp\":\"2018-06-19T20:11:57+05:30\"," +
                        "\"message\":\"Random error\"," +
                        "\"error\":{\"name\":\"Error\",\"reason\":\"Random error\"}}"));

        server.start();

        HttpUrl baseUrl = server.url("/unit/tests/");
        TestHelper.getInstance().setBaseUrl(baseUrl);

        TokenRequest request = new TokenRequest("authorization_code_123456789",
                "authorization_code", "http://localhost:4567/hello");

        ApiCredentials credentials = new ApiCredentials("secretApiKey", "secret-secret");

        LoginService service = new LoginService(request, credentials);

        assertThrows(ExecutionException.class, service::getAccessToken);

        // Shut down the server. Instances cannot be reused.
        server.shutdown();
    }

    @Test
    void getAccessToken_errorOut_onNetworkError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

        server.start();

        HttpUrl baseUrl = server.url("/unit/tests/");
        TestHelper.getInstance().setBaseUrl(baseUrl);

        TokenRequest request = new TokenRequest("authorization_code_123456789",
                "authorization_code", "http://localhost:4567/hello");

        ApiCredentials credentials = new ApiCredentials("secretApiKey", "secret-secret");

        LoginService service = new LoginService(request, credentials);

        try {
            service.getAccessToken();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        }
        //assertThrows(ExecutionException.class, service::getAccessToken);

        // Shut down the server. Instances cannot be reused.
        server.shutdown();
    }

    @Test
    void getAccessToken_throwNPE_whenParametersAreNotProper() {

        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        assertThrows(NullPointerException.class,
                () -> new LoginService(null, credentials),
                "Null check missing for 'TokenRequest' from LoginService constructor");

        assertThrows(NullPointerException.class,
                () -> new LoginService(
                        new TokenRequest(
                                "authorization_code_123456789",
                                null,
                                "http://localhost:4567/hello"),
                        credentials),
                "Null check missing for 'grantType' from TokenRequest constructor");

        assertThrows(NullPointerException.class,
                () -> new LoginService(
                        new TokenRequest(
                                null,
                                "authorization_code",
                                "http://localhost:4567/hello"),
                        credentials),
                "Null check missing for 'code' from TokenRequest constructor");

        assertThrows(NullPointerException.class,
                () -> new LoginService(
                        new TokenRequest(
                                "authorization_code_123456789",
                                "authorization_code",
                                null),
                        credentials),
                "Null check missing for 'redirectUri' from TokenRequest constructor");

        TokenRequest request = new TokenRequest(
                "authorization_code_123456789",
                "authorization_code",
                "http://localhost:4567/hello");

        assertThrows(NullPointerException.class,
                () -> new LoginService(
                        request,
                        null),
                "Null check missing for 'ApiCredentials' from LoginService constructor");

        assertThrows(NullPointerException.class,
                () -> new LoginService(
                        request,
                        new ApiCredentials(
                                null,
                                "secret-secret")),
                "Null check missing for 'apiKey' from ApiCredentials constructor");

        assertThrows(NullPointerException.class,
                () -> new LoginService(
                        request,
                        new ApiCredentials(
                                "secretApiKey",
                                null)),
                "Null check missing for 'apiSecret' from ApiCredentials constructor");
    }
}