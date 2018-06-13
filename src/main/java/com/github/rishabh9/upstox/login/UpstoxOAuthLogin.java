package com.github.rishabh9.upstox.login;

import com.github.rishabh9.upstox.api.LoginService;
import com.github.rishabh9.upstox.config.UpstoxConfigUtility;
import com.github.rishabh9.upstox.login.models.AccessToken;
import com.github.rishabh9.upstox.login.models.TokenRequestBuilder;
import com.google.common.base.Strings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpstoxOAuthLogin {

    private static final Logger logger = LogManager.getLogger(UpstoxOAuthLogin.class);
    private static final UpstoxConfigUtility CONFIG = UpstoxConfigUtility.getInstance();

    public void completeLogin(String code) {
        if (Strings.isNullOrEmpty(code)) {
            throw new IllegalArgumentException("Missing value for authorization code. Code: " + code);
        }

        // Create a very simple REST adapter which points the Upstox API endpoint.
        LoginService loginService =
                ServiceGenerator.createService(
                        LoginService.class,
                        CONFIG.getApiKey(),
                        CONFIG.getApiSecret());

        // Prepare for the API call
        TokenRequestBuilder builder = new TokenRequestBuilder()
                .withCode(code)
                .withGrantType("authorization_code")
                .withRedirectUri(CONFIG.getRedirectUri());

        Call<AccessToken> call = loginService.getAccessToken(CONFIG.getApiKey(), builder.build());

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        // The network call was a success and we got a response
                        // TODO: Persist the access token
                        logger.info("CODE:    {}", response.code());
                        logger.info("SUCCESS: {}", response.isSuccessful());
                        logger.info("MESSAGE: {}", response.message());
                        logger.info("BODY:    {}", response.body());
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        // the network call was a failure
                        // TODO: handle error
                        logger.error(t);
                    }
                });
    }
}
