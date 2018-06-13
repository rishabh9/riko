package com.github.rishabh9.upstox.api;

import com.github.rishabh9.upstox.login.models.AccessToken;
import com.github.rishabh9.upstox.login.models.TokenRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {

    @Headers("Content-Type: application/json")
    @POST("/index/oauth/token")
    Call<AccessToken> getAccessToken(
            @Header("X-API-KEY") String apiKey,
            @Body TokenRequest tokenRequest
    );

}
