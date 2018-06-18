package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.CallMe;
import com.github.rishabh9.riko.upstox.common.ServiceGenerator;
import com.github.rishabh9.riko.upstox.common.models.ApiCredentials;
import com.github.rishabh9.riko.upstox.common.models.AuthHeadersBuilder;
import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.users.models.Position;
import com.github.rishabh9.riko.upstox.users.models.Profile;
import com.github.rishabh9.riko.upstox.users.models.ProfileBalance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UsersService {

    private static final Logger log = LogManager.getLogger(UsersService.class);

    /**
     * Retrieves the user's profile synchronously
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return User's profile details. It will be empty if request 'returns' an error.
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<Profile> getProfile(@Nonnull AccessToken accessToken,
                                        @Nonnull final ApiCredentials credentials)
            throws IOException {

        UsersApi api = setupService(accessToken, credentials);

        Response<UpstoxResponse<Profile>> response = api.getProfile().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Retrieves the user's profile asynchronously
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getProfileAsync(@Nonnull final AccessToken accessToken,
                                @Nonnull final ApiCredentials credentials,
                                @Nonnull final CallMe<Profile> callMe) {

        UsersApi api = setupService(accessToken, credentials);

        api.getProfile().enqueue(prepareCallback(callMe));
    }

    /**
     * Retrieve the profile balance.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param accountType The account type - 'security' or 'commodity'
     * @return the user's account balance.
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<ProfileBalance> getProfileBalance(@Nonnull final AccessToken accessToken,
                                                      @Nonnull final ApiCredentials credentials,
                                                      @Nullable final String accountType)
            throws IOException {

        UsersApi api = setupService(accessToken, credentials);

        Response<UpstoxResponse<ProfileBalance>> response = api.getProfileBalance(accountType).execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Retrieve's profile balance asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param accountType The account type - 'security' or 'commodity'
     * @param callMe      The call back interface
     */
    public void getProfileBalanceAsync(@Nonnull final AccessToken accessToken,
                                       @Nonnull final ApiCredentials credentials,
                                       @Nullable final String accountType,
                                       @Nonnull final CallMe<ProfileBalance> callMe) {

        UsersApi api = setupService(accessToken, credentials);

        api.getProfileBalance(accountType).enqueue(prepareCallback(callMe));
    }

    /**
     * Fetches the current positions for the user for the current day.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @return List of Position
     * @throws IOException When an error occurs while making the request.
     */
    public Optional<List<Position>> getPositions(@Nonnull final AccessToken accessToken,
                                                 @Nonnull final ApiCredentials credentials)
            throws IOException {

        UsersApi api = setupService(accessToken, credentials);

        Response<UpstoxResponse<List<Position>>> response = api.getPositions().execute();

        return completeSynchronousRequest(response);
    }

    /**
     * Fetches the current positions for the user for the current day, asynchronously.
     *
     * @param accessToken The user's access token
     * @param credentials The user's API credentials
     * @param callMe      The call back interface
     */
    public void getPositionsAsync(@Nonnull final AccessToken accessToken,
                                  @Nonnull final ApiCredentials credentials,
                                  @Nonnull final CallMe<List<Position>> callMe) {

        UsersApi api = setupService(accessToken, credentials);

        api.getPositions().enqueue(prepareCallback(callMe));
    }

    private UsersApi setupService(@Nonnull final AccessToken accessToken,
                                  @Nonnull final ApiCredentials credentials) {

        final AuthHeadersBuilder builder = new AuthHeadersBuilder()
                .withApiKey(credentials.getApiKey())
                .withToken(accessToken.getType() + " " + accessToken.getToken());

        return ServiceGenerator.createService(UsersApi.class, builder.build());
    }

    private <T> Optional<T> completeSynchronousRequest(final Response<UpstoxResponse<T>> response) throws IOException {

        log.debug("Request to retrieve profile was {}. HTTP response code: {}.",
                response.isSuccessful() ? "successful" : "unsuccessful",
                response.code());

        if (response.isSuccessful()) {
            return Optional.ofNullable(response.body().getData());
        }

        log.error("Error Response: {}", response.errorBody().string());
        return Optional.empty();
    }

    private <T> Callback<UpstoxResponse<T>> prepareCallback(@Nonnull CallMe<T> callMe) {

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
