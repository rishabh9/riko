package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.users.models.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static okhttp3.mockwebserver.SocketPolicy.DISCONNECT_AFTER_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private static final Logger log = LogManager.getLogger(UserServiceTest.class);

    @Test
    void getProfile_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Profile profile = new Profile();
        profile.setName("John Doe");
        UpstoxResponse<Profile> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(profile);

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
        UserService service = new UserService(token, credentials);

        try {
            UpstoxResponse<Profile> serverResponse = service.getProfile().get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getProfile_failure_whenUpstoxReturnsError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        assertThrows(ExecutionException.class, service.getProfile()::get);

        server.shutdown();
    }

    @Test
    void getProfile_failure_onNetworkError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        try {
            service.getProfile().get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getProfileBalance_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Balance balance = new Balance();
        balance.setAvailableMargin(BigDecimal.TEN);
        ProfileBalance profileBalance = new ProfileBalance();
        profileBalance.setCommodity(balance);
        UpstoxResponse<ProfileBalance> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(profileBalance);

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
        UserService service = new UserService(token, credentials);

        try {
            UpstoxResponse<ProfileBalance> serverResponse =
                    service.getProfileBalance("commodity").get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getProfileBalance_failure_whenUpstoxReturnsError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        assertThrows(ExecutionException.class, service.getProfileBalance(null)::get);

        server.shutdown();
    }

    @Test
    void getProfileBalance_failure_onNetworkError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        try {
            service.getProfileBalance(null).get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getPositions_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Position position = new Position();
        position.setExchange("NSE");
        List<Position> holdings = new ArrayList<>(1);
        holdings.add(position);
        UpstoxResponse<List<Position>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(holdings);

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
        UserService service = new UserService(token, credentials);

        try {
            UpstoxResponse<List<Position>> serverResponse = service.getPositions().get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getPositions_failure_whenUpstoxReturnsError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        assertThrows(ExecutionException.class, service.getPositions()::get);

        server.shutdown();
    }

    @Test
    void getPositions_failure_onNetworkError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        try {
            service.getPositions().get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getHoldings_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Holding holding = new Holding();
        holding.setQuantity(1000L);
        List<Holding> holdings = new ArrayList<>(1);
        holdings.add(holding);
        UpstoxResponse<List<Holding>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(holdings);

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
        UserService service = new UserService(token, credentials);

        try {
            UpstoxResponse<List<Holding>> serverResponse = service.getHoldings().get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getHoldings_failure_whenUpstoxReturnsError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        assertThrows(ExecutionException.class, service.getHoldings()::get);

        server.shutdown();
    }

    @Test
    void getHoldings_failure_onNetworkError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        try {
            service.getHoldings().get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getAllMasterContracts_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        List<String> csv = new ArrayList<>(2);
        csv.add("A,B,C");
        csv.add("1,2,3");
        UpstoxResponse<List<String>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(csv);

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
        UserService service = new UserService(token, credentials);

        try {
            InputStream inputStream =
                    service.getAllMasterContracts("NSE")
                            .get();

            String stagedResponse = convert(inputStream, Charset.forName("UTF-8"));
            Type fooType = new TypeToken<UpstoxResponse<List<String>>>() {}.getType();
            UpstoxResponse<List<String>> serverResponse =
                    new Gson().fromJson(stagedResponse, fooType);
            System.err.println(stagedResponse);

            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    public String convert(InputStream inputStream, Charset charset) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream, charset))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    @Test
    void getAllMasterContracts_failure_whenUpstoxReturnsError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        assertThrows(ExecutionException.class,
                service.getAllMasterContracts("NSE")::get);

        server.shutdown();
    }

    @Test
    void getAllMasterContracts_failure_onNetworkError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        try {
            service.getAllMasterContracts("NSE").get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getAllMasterContracts_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        UserService service = new UserService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.getAllMasterContracts(null),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getAllMasterContracts(""),
                "Exchange cannot be empty. Mandatory validation missing.");
    }

    @Test
    void getMasterContract_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Contract contract = new Contract();
        contract.setExchange("NSE");
        UpstoxResponse<Contract> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(contract);

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
        UserService service = new UserService(token, credentials);

        try {
            UpstoxResponse<Contract> serverResponse =
                    service.getMasterContract("NSE", "RELIANCE", "12345")
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
    void getMasterContract_failure_whenUpstoxReturnsError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        assertThrows(ExecutionException.class,
                service.getMasterContract("NSE", "RELIANCE", null)::get);

        server.shutdown();
    }

    @Test
    void getMasterContract_failure_onNetworkError() throws IOException {
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
        UserService service = new UserService(token, credentials);

        try {
            service.getMasterContract("NSE", "RELIANCE", null).get();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getMasterContract_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        UserService service = new UserService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.getMasterContract(null, null, null),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getMasterContract("", null, null),
                "Exchange cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getMasterContract("NSE", null, null),
                "Both symbol and token cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getMasterContract("NSE", "", ""),
                "Both symbol and token cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getMasterContract("NSE", null, ""),
                "Both symbol and token cannot be empty. Mandatory validation missing.");

        try {
            service.getMasterContract("NSE", null, "");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Provide either the 'symbol' or 'token'. Both cannot be null nor empty.",
                    e.getMessage());
        }
    }

    @Test
    void getMasterContract_throwNPE_whenServiceParametersAreMissing() {

        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        assertThrows(NullPointerException.class,
                () -> new UserService(null, credentials),
                "Null check missing for 'AccessToken' from UserService constructor");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        assertThrows(NullPointerException.class,
                () -> new UserService(token, null),
                "Null check missing for 'ApiCredentials' from UserService constructor");
    }
}