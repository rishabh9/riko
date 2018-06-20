package com.github.rishabh9.riko.upstox.login;

import com.github.rishabh9.riko.upstox.login.models.AccessToken;
import com.github.rishabh9.riko.upstox.login.models.TokenRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Login API endpoints declaration.
 */
public interface LoginApi {

    /**
     * Retrieves the access code from Upstox Authorization URL through a synchronous call.<br>
     *
     * @param tokenRequest The token request, including the access code.
     * @return A Call to execute the request (a)synchronously.
     */
    @Headers("Content-Type: application/json")
    @POST("/index/oauth/token")
    Call<AccessToken> getAccessToken(@Body TokenRequest tokenRequest);

}
