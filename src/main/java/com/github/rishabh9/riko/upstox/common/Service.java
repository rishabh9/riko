package com.github.rishabh9.riko.upstox.common;

import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.AuthHeaders;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Optional;

/**
 * Parent class for every Service class. Holds common methods.
 */
public abstract class Service {

    private static final Logger log = LogManager.getLogger(Service.class);

    protected <T> T prepareServiceApi(@Nonnull final Class<T> type,
                                      @Nonnull final AccessToken accessToken,
                                      @Nonnull final ApiCredentials credentials) {

        final String token = accessToken.getType() + " " + accessToken.getToken();
        return ServiceGenerator.createService(type, new AuthHeaders(token, credentials.getApiKey()));
    }

    protected <T> Optional<T> completeSynchronousRequest(final Response<UpstoxResponse<T>> response)
            throws IOException {

        log.debug("Request to retrieve profile was {}. HTTP response code: {}.",
                response.isSuccessful() ? "successful" : "unsuccessful",
                response.code());

        if (response.isSuccessful() && null != response.body()) {
            return Optional.ofNullable(response.body().getData());
        }

        log.error("Error Response: {}", response.errorBody().string());
        return Optional.empty();
    }

    protected <T> Callback<UpstoxResponse<T>> prepareCallback(@Nonnull CallMe<T> callMe) {

        return new Callback<>() {
            @Override
            public void onResponse(Call<UpstoxResponse<T>> call, Response<UpstoxResponse<T>> response) {

                log.debug("Request to retrieve profile was {}. HTTP response code: {}.",
                        response.isSuccessful() ? "successful" : "unsuccessful",
                        response.code());

                if (response.isSuccessful()) {
                    callMe.onSuccess(response.body().getData());
                } else {
                    try {
                        callMe.onFailure(response.errorBody().string(), null);
                    } catch (IOException e) {
                        log.error("Error reading error response for request " + call.request(), e);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpstoxResponse<T>> call, Throwable t) {

                log.error(t);
                callMe.onFailure("", t);
            }
        };
    }
}
