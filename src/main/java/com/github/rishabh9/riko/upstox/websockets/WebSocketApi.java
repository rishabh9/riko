package com.github.rishabh9.riko.upstox.websockets;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.websockets.models.WebsocketParameters;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

/**
 * Web socket API endpoints declaration.
 */
public interface WebSocketApi {

    /**
     * Retrieve the parameters required to connect and maintain web socket
     * @return A CompletableFuture to execute the request (a)synchronously.
     */
    @GET("/live/socket-params")
    CompletableFuture<UpstoxResponse<WebsocketParameters>> getWebsocketParameters();
}
