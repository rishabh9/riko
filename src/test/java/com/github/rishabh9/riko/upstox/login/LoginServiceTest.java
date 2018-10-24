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

package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.UpstoxAuthService;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
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

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        TokenRequest request = new TokenRequest("authorization_code_123456789",
                "authorization_code", "http://localhost:4567/hello");

        UpstoxAuthService upstoxAuthService = new UpstoxAuthService() {
            @Override
            public ApiCredentials getApiCredentials() {
                return new ApiCredentials("secretApiKey", "secret-secret");
            }

            @Override
            public AccessToken getAccessToken() {
                return null;
            }
        };

        LoginService service = new LoginService(upstoxAuthService);
        try {
            AccessToken accessToken = service.getAccessToken(request).get();

            assertNotNull(accessToken);
            assertEquals(Long.valueOf(86400L), accessToken.getExpiresIn());
            assertEquals("Bearer", accessToken.getType());
            assertEquals("access_token_123456789", accessToken.getToken());
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            // Shut down the server. Instances cannot be reused.
            server.shutdown();
        }
    }

    @Test
    void getAccessToken_failure_whenUpstoxReturnsError() throws IOException {
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

        TokenRequest request = new TokenRequest("authorization_code_123456789",
                "authorization_code", "http://localhost:4567/hello");

        UpstoxAuthService upstoxAuthService = new UpstoxAuthService() {
            @Override
            public ApiCredentials getApiCredentials() {
                return new ApiCredentials("secretApiKey", "secret-secret");
            }

            @Override
            public AccessToken getAccessToken() {
                return null;
            }
        };

        LoginService service = new LoginService(upstoxAuthService);

        assertThrows(ExecutionException.class, () -> service.getAccessToken(request).get());

        // Shut down the server. Instances cannot be reused.
        server.shutdown();
    }

    @Test
    void getAccessToken_failure_onNetworkError() throws IOException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setSocketPolicy(DISCONNECT_AFTER_REQUEST));

        server.start();

        ServiceGenerator.getInstance().rebuildWithUrl(server.url("/"));

        TokenRequest request = new TokenRequest("authorization_code_123456789",
                "authorization_code", "http://localhost:4567/hello");

        UpstoxAuthService upstoxAuthService = new UpstoxAuthService() {
            @Override
            public ApiCredentials getApiCredentials() {
                return new ApiCredentials("secretApiKey", "secret-secret");
            }

            @Override
            public AccessToken getAccessToken() {
                return null;
            }
        };

        LoginService service = new LoginService(upstoxAuthService);

        try {
            service.getAccessToken(request).get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            // Shut down the server. Instances cannot be reused.
            server.shutdown();
        }
    }

    @Test
    void getAccessToken_throwNPE_whenMissingTokenRequest() {
        UpstoxAuthService upstoxAuthService = new UpstoxAuthService() {
            @Override
            public ApiCredentials getApiCredentials() {
                return new ApiCredentials("secretApiKey", "secret-secret");
            }

            @Override
            public AccessToken getAccessToken() {
                return null;
            }
        };

        LoginService service = new LoginService(upstoxAuthService);
        assertThrows(NullPointerException.class,
                () -> service.getAccessToken(null),
                "Null check missing for 'TokenRequest' from LoginService constructor");
    }

    @Test
    void getAccessToken_throwNPE_whenMissingGrantType() {
        assertThrows(NullPointerException.class,
                () -> new TokenRequest("authorization_code_123456789", null, "http://localhost:4567/hello"),
                "Null check missing for 'grantType' from TokenRequest constructor");
    }

    @Test
    void getAccessToken_throwNPE_whenMissingCode() {
        assertThrows(NullPointerException.class,
                () -> new TokenRequest(null, "authorization_code", "http://localhost:4567/hello"),
                "Null check missing for 'code' from TokenRequest constructor");
    }

    @Test
    void getAccessToken_throwNPE_whenMissingRedirectUri() {
        assertThrows(NullPointerException.class,
                () -> new TokenRequest("authorization_code_123456789", "authorization_code", null),
                "Null check missing for 'redirectUri' from TokenRequest constructor");
    }

    @Test
    void getAccessToken_throwNPE_whenMissingApiKey() {
        assertThrows(NullPointerException.class,
                () -> new ApiCredentials(null, "secret-secret"),
                "Null check missing for 'apiKey' from ApiCredentials constructor");
    }

    @Test
    void getAccessToken_throwNPE_whenMissingApiSecret() {
        assertThrows(NullPointerException.class,
                () -> new ApiCredentials("secretApiKey", null),
                "Null check missing for 'apiSecret' from ApiCredentials constructor");
    }
}