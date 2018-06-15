package com.github.rishabh9.riko.upstox.api;

import com.github.rishabh9.riko.upstox.models.Profile;
import com.github.rishabh9.riko.upstox.models.UpstoxResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UsersService {

    @GET("/index/profile")
    Call<UpstoxResponse<Profile>> getProfile(@Header("X-API-KEY") String apiKey);
}
