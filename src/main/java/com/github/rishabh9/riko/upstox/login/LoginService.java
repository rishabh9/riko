package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Optional;

public class LoginService {

    private static final Logger log = LogManager.getLogger(LoginService.class);

    /**
     * Retrieves the access code from Upstox Authorization URL through a synchronous call.<br>
     *
     * @param request     The TokenRequest object containing all fields,
     *                    including the access code received after authenticating the user on Upstox site.
     * @param credentials The API key and secret.
     * @return An 'optional' AccessToken. Return object is empty in case of error.
     * @throws IOException on network failure
     */
    public Optional<AccessToken> getAccessToken(@Nonnull final TokenRequest request,
                                                @Nonnull final ApiCredentials credentials)
            throws IOException {

        if (Strings.isNullOrEmpty(request.getCode())) {
            throw new IllegalArgumentException("Missing value for authorization code. Code: " + request.getCode());
        }

        // Create a very simple REST adapter which points the Upstox API endpoint.
        LoginApi loginApi =
                ServiceGenerator.createService(
                        LoginApi.class,
                        credentials.getApiKey(),
                        credentials.getApiSecret());

        Call<AccessToken> call = loginApi.getAccessToken(request);

        // Make a synchronous call.
        Response<AccessToken> response = call.execute();

        log.debug("Request to retrieve access code was {}. HTTP response code: {}.",
                response.isSuccessful() ? "successful" : "unsuccessful",
                response.code());

        if (response.isSuccessful()) {
            return Optional.ofNullable(response.body());
        }

        return Optional.empty();

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
