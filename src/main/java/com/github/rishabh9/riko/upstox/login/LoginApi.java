package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginApi {

    @Headers("Content-Type: application/json")
    @POST("/index/oauth/token")
    Call<AccessToken> getAccessToken(@Body TokenRequest tokenRequest);

}
