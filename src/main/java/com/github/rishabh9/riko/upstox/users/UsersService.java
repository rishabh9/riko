package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.CallMe;
import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.AuthHeadersBuilder;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.users.models.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Optional;

public class UsersService {

    private static final Logger log = LogManager.getLogger(UsersService.class);

    /**
     *
     * @param accessToken
     * @param credentials
     * @return
     * @throws IOException
     */
    public Optional<Profile> getProfile(@Nonnull AccessToken accessToken, @Nonnull final ApiCredentials credentials)
            throws IOException {

        final AuthHeadersBuilder builder = new AuthHeadersBuilder()
                .withApiKey(credentials.getApiKey())
                .withToken(accessToken.getType() + " " + accessToken.getToken());

        UsersApi api = ServiceGenerator.createService(UsersApi.class, builder.build());
        Response<UpstoxResponse<Profile>> response = api.getProfile().execute();

        log.debug("Request to retrieve profile was {}. HTTP response code: {}.",
                response.isSuccessful() ? "successful" : "unsuccessful",
                response.code());

        if (response.isSuccessful()) {
            return Optional.ofNullable(response.body().getData());
        }

        log.error("Error Response (if any) {}", response.errorBody().string());
        return Optional.empty();
    }

    /**
     *
     * @param accessToken
     * @param credentials
     * @param callMe
     */
    public void getProfileAsync(
            @Nonnull AccessToken accessToken,
            @Nonnull final ApiCredentials credentials,
            @Nonnull CallMe<Profile> callMe) {

        final AuthHeadersBuilder builder = new AuthHeadersBuilder()
                .withApiKey(credentials.getApiKey())
                .withToken(accessToken.getType() + " " + accessToken.getToken());

        ServiceGenerator.createService(UsersApi.class, builder.build())
                .getProfile()
                .enqueue(
                        new retrofit2.Callback<>() {
                            @Override
                            public void
                            onResponse(Call<UpstoxResponse<Profile>> call, Response<UpstoxResponse<Profile>> response) {
                                log.debug("Request to retrieve profile was {}. HTTP response code: {}.",
                                        response.isSuccessful() ? "successful" : "unsuccessful",
                                        response.code());

                                if (response.isSuccessful()) {
                                    callMe.onSuccess(response.body().getData());
                                }
                                try {
                                    callMe.onFailure(response.errorBody().string(), null);
                                } catch (IOException e) {
                                    log.error("Error reading error response for request " + call.request(), e);
                                }
                            }

                            @Override
                            public void onFailure(Call<UpstoxResponse<Profile>> call, Throwable t) {
                                log.error(t);
                                callMe.onFailure("", t);
                            }
                        });
    }
}
