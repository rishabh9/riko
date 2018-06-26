package com.github.rishabh9.riko.upstox.websockets;

import com.github.rishabh9.riko.upstox.common.Service;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.websockets.exceptions.WebRequestException;
import com.github.rishabh9.riko.upstox.websockets.models.WebsocketParameters;
import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WebSocketService extends Service {

    private static final Logger log = LogManager.getLogger(WebSocketService.class);

    /**
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     */
    public WebSocketService(final AccessToken accessToken, final ApiCredentials credentials) {
        super(accessToken, credentials);
    }

    private CompletableFuture<UpstoxResponse<WebsocketParameters>> getWebsocketParameters() {

        log.debug("Preparing service - GET Websocket Parameters");
        final WebSocketApi api = prepareServiceApi(WebSocketApi.class);

        log.debug("Making request -GET Websocket Parameters ");
        return api.getWebsocketParameters();
    }

    /**
     * Connect to Upstox via web socket.<br>
     * Internally, this method retrieves the published parameters to connect to Upstox
     * via web sockets and then tries to setup a web socket connection.
     *
     * @param subscribers The subscribers interested in the messages received at the web socket.
     *                    <em>Mandatory.</em>
     * @return The web socket wrapped in custom class for usability.
     * @throws ExecutionException   When there is an exception creating the connection.
     * @throws InterruptedException When there is an error while waiting for the connection to complete.
     */
    public WrappedWebSocket connect(final List<MessageSubscriber> subscribers)
            throws ExecutionException, InterruptedException {

        if (null == subscribers || subscribers.isEmpty()) {
            throw new IllegalArgumentException("Subscribers not provided. Not connecting to the socket.");
        }

        // Retrieve the webSocket parameters before connecting, as per Upstox documentation.
        return getWebsocketParameters()
                .thenApplyAsync((response) -> {
                    if (null == response || response.getCode() != 200) {
                        log.fatal("Unable to retrieve websocket parameters. " +
                                "Response was {} with response code: {}", response, response.getCode());
                        throw new WebRequestException(
                                "Unable to retrieve parameters for making a websocket connection.");
                    }
                    return makeConnection(response.getData(), subscribers);
                })
                .exceptionally((throwable) -> {
                    log.error("Error opening and connecting to WebSocket", throwable);
                    return null;
                })
                .get();
    }

    private WrappedWebSocket makeConnection(final WebsocketParameters parameters,
                                            final List<MessageSubscriber> subscribers) {

        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        final OkHttpClient httpClient = httpClientBuilder
                .connectionPool(
                        new ConnectionPool(5, parameters.getKeepaliveInterval(), TimeUnit.SECONDS))
                .readTimeout(parameters.getPythonPingTimeout() * 3, TimeUnit.SECONDS)
                .writeTimeout(parameters.getPythonPingTimeout() * 3, TimeUnit.SECONDS)
                .pingInterval(parameters.getPythonPingInterval(), TimeUnit.SECONDS)
                .build();

        final Request request = prepareRequest();

        final WebSocket webSocket = httpClient.newWebSocket(request, new MessageListener(subscribers));

        return new WrappedWebSocket(webSocket);
    }

    private Request prepareRequest() {

        log.debug("Preparing request");

        final HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
        final HttpUrl url = urlBuilder
                .host("ws-api.upstox.com")
                .scheme("https")
                .addQueryParameter("apiKey", credentials.getApiKey())
                .addQueryParameter("token", accessToken.getToken())
                .build();

        final String token = accessToken.getType() + " " + accessToken.getToken();

        return new Request.Builder()
                .header("X-API-KEY", credentials.getApiKey())
                .header("Authorization", token)
                .header("Sec-WebSocket-Key", Double.toString(Math.random()))
                .header("Sec-WebSocket-Version", "13")
                .url(url)
                .build();
    }
}
