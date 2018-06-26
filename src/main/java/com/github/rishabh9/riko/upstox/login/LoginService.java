package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LoginService {

    private static final Logger log = LogManager.getLogger(LoginService.class);

    private final TokenRequest request;

    private final ApiCredentials credentials;

    /**
     * @param request     The TokenRequest object containing all fields,
     *                    including the access code received after authenticating the user on Upstox site.
     * @param credentials The API key and secret.
     */
    public LoginService(@Nonnull final TokenRequest request,
                        @Nonnull final ApiCredentials credentials) {

        this.request = Objects.requireNonNull(request);
        this.credentials = Objects.requireNonNull(credentials);
    }

    /**
     * Retrieves the access code from Upstox Authorization URL through a synchronous call.<br>
     *
     * @return An 'optional' AccessToken. Return object is empty in case of error.
     */
    public AccessToken getAccessToken() throws ExecutionException, InterruptedException {

        if (Strings.isNullOrEmpty(request.getCode())) {
            throw new IllegalArgumentException(
                    "Missing value for authorization code. Code: " + request.getCode());
        }

        // Create a very simple REST adapter which points the Upstox API endpoint.
        LoginApi loginApi =
                ServiceGenerator.createService(
                        LoginApi.class,
                        credentials.getApiKey(),
                        credentials.getApiSecret());

        CompletableFuture<AccessToken> future = loginApi.getAccessToken(request);

        // Make a synchronous call.
        return future.whenComplete((accessToken, throwable) -> {
            if (null != throwable) {
                log.debug("Request to retrieve access code was unsuccessful", throwable);
            } else {
                log.debug("Request to retrieve access code was successful");
            }
        }).get();

/*
        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        // The network call was a success and we got a response
                        logger.debug("Request for access code was {}. HTTP response code: {}.",
                                response.isSuccessful() ? "successful" : "unsuccessful",
                                response.code());
                        if (response.isSuccessful()) {
                            // Persist the access token
                            AccessToken accessToken = response.body();
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        // The network call was a failure
                        logger.error(t);
                    }
                });
*/
    }
}
