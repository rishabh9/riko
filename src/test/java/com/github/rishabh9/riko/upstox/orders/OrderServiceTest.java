package com.github.rishabh9.riko.upstox.orders;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.orders.models.Order;
import com.github.rishabh9.riko.upstox.orders.models.OrderRequest;
import com.github.rishabh9.riko.upstox.orders.models.Trade;
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

class OrderServiceTest {

    private static final Logger log = LogManager.getLogger(OrderServiceTest.class);

    @Test
    void getOrderHistory_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Order order = new Order();
        order.setOrderId("ORD_ID_1");
        List<Order> orders = new ArrayList<>(1);
        orders.add(order);
        UpstoxResponse<List<Order>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(orders);

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
        OrderService service = new OrderService(token, credentials);

        try {
            UpstoxResponse<List<Order>> serverResponse = service.getOrderHistory().get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getOrderHistory_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.getOrderHistory()::get);

        server.shutdown();
    }

    @Test
    void getOrderHistory_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.getOrderHistory().get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getOrderDetails_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Order order = new Order();
        order.setOrderId("ORD_ID_1");
        List<Order> orders = new ArrayList<>(1);
        orders.add(order);
        UpstoxResponse<List<Order>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(orders);

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
        OrderService service = new OrderService(token, credentials);

        try {
            UpstoxResponse<List<Order>> serverResponse =
                    service.getOrderDetails("ORD_ID_1").get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getOrderDetails_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.getOrderDetails("ORD_ID_1")::get);

        server.shutdown();
    }

    @Test
    void getOrderDetails_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.getOrderDetails("ORD_ID_1").get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getOrderDetails_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        OrderService service = new OrderService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOrderDetails(null),
                "OrderId cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getOrderDetails(""),
                "OrderId cannot be empty. Mandatory validation missing.");
    }

    @Test
    void getTradeBook_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Trade trade = new Trade();
        trade.setOrderId("ORD_ID_1");
        List<Trade> trades = new ArrayList<>(1);
        trades.add(trade);
        UpstoxResponse<List<Trade>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(trades);

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
        OrderService service = new OrderService(token, credentials);

        try {
            UpstoxResponse<List<Trade>> serverResponse = service.getTradeBook().get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getTradeBook_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.getTradeBook()::get);

        server.shutdown();
    }

    @Test
    void getTradeBook_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.getTradeBook().get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getTradeHistory_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Trade trade = new Trade();
        trade.setOrderId("ORD_ID_1");
        List<Trade> trades = new ArrayList<>(1);
        trades.add(trade);
        UpstoxResponse<List<Trade>> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(trades);

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
        OrderService service = new OrderService(token, credentials);

        try {
            UpstoxResponse<List<Trade>> serverResponse =
                    service.getTradeHistory("ORD_ID_1").get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getTradeHistory_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.getTradeHistory("ORD_ID_1")::get);

        server.shutdown();
    }

    @Test
    void getTradeHistory_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.getTradeHistory("ORD_ID_1").get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void getTradeHistory_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        OrderService service = new OrderService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.getTradeHistory(null),
                "OrderId cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.getTradeHistory(""),
                "OrderId cannot be null. Mandatory validation missing.");
    }

    @Test
    void placeOrder_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Order order = new Order();
        order.setOrderId("ORD_ID_1");
        UpstoxResponse<Order> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(order);

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
        OrderService service = new OrderService(token, credentials);

        OrderRequest request = new OrderRequest();
        request.setOrderId("ORD_ID_1");

        try {
            UpstoxResponse<Order> serverResponse = service.placeOrder(request).get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void placeOrder_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.placeOrder(new OrderRequest())::get);

        server.shutdown();
    }

    @Test
    void placeOrder_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.placeOrder(new OrderRequest()).get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void placeOrder_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        OrderService service = new OrderService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.placeOrder(null),
                "OrderRequest cannot be null. Mandatory validation missing.");
    }

    @Test
    void modifyOrder_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        Order order = new Order();
        order.setOrderId("ORD_ID_1");
        UpstoxResponse<Order> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData(order);

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
        OrderService service = new OrderService(token, credentials);

        OrderRequest request = new OrderRequest();
        request.setOrderId("ORD_ID_1");

        try {
            UpstoxResponse<Order> serverResponse =
                    service.modifyOrder("ORD_ID_1", request).get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void modifyOrder_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class,
                service.modifyOrder("ORD_ID_1", new OrderRequest())::get);

        server.shutdown();
    }

    @Test
    void modifyOrder_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.modifyOrder("ORD_ID_1", new OrderRequest()).get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void modifyOrder_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        OrderService service = new OrderService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.modifyOrder(null, new OrderRequest()),
                "OrderId cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.modifyOrder("", new OrderRequest()),
                "OrderId cannot be empty. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.modifyOrder("ORD_ID_1", null),
                "OrderRequest cannot be null. Mandatory validation missing.");
    }

    @Test
    void cancelOrders_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        UpstoxResponse<String> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData("ORD_ID_1,ORD_ID_2");

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
        OrderService service = new OrderService(token, credentials);

        try {
            UpstoxResponse<String> serverResponse =
                    service.cancelOrders("ORD_ID_1,ORD_ID_2").get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void cancelOrders_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.cancelOrders("ORDER_ID_1")::get);

        server.shutdown();
    }

    @Test
    void cancelOrders_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.cancelOrders("ORD_ID_1,ORD_ID_2").get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void cancelOrders_throwIAE_whenRequiredParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        OrderService service = new OrderService(token, credentials);

        assertThrows(IllegalArgumentException.class, () ->
                        service.cancelOrders(null),
                "OrderIds cannot be null. Mandatory validation missing.");

        assertThrows(IllegalArgumentException.class, () ->
                        service.cancelOrders(""),
                "OrderIds cannot be empty. Mandatory validation missing.");
    }

    @Test
    void cancelAllOrders_success_whenAllParametersAreCorrect() throws IOException {
        MockWebServer server = new MockWebServer();

        UpstoxResponse<String> response = new UpstoxResponse<>();
        response.setCode(200);
        response.setData("ORD_ID_1,ORD_ID_2");

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
        OrderService service = new OrderService(token, credentials);

        try {
            UpstoxResponse<String> serverResponse = service.cancelAllOrders().get();
            assertEquals(response, serverResponse);
        } catch (ExecutionException | InterruptedException e) {
            log.fatal(e);
            fail();
        } finally {
            server.shutdown();
        }
    }

    @Test
    void cancelAllOrders_failure_whenUpstoxReturnsError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        assertThrows(ExecutionException.class, service.cancelAllOrders()::get);

        server.shutdown();
    }

    @Test
    void cancelAllOrders_failure_onNetworkError() throws IOException {
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
        OrderService service = new OrderService(token, credentials);

        try {
            service.cancelAllOrders().get();
            fail();
        } catch (ExecutionException | InterruptedException e) {
            assertTrue(e.getCause() instanceof IOException);
        } finally {
            server.shutdown();
        }
    }

    @Test
    void cancelAllOrders_throwNPE_whenServiceParametersAreMissing() {
        ApiCredentials credentials =
                new ApiCredentials("secretApiKey", "secret-secret");

        assertThrows(NullPointerException.class,
                () -> new OrderService(null, credentials),
                "Null check missing for 'AccessToken' from OrderService constructor");

        AccessToken token = new AccessToken();
        token.setExpiresIn(86400L);
        token.setType("Bearer");
        token.setToken("access_token_123456789");

        assertThrows(NullPointerException.class,
                () -> new OrderService(token, null),
                "Null check missing for 'ApiCredentials' from OrderService constructor");
    }
}