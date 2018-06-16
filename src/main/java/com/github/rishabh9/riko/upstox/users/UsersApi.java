package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.users.models.Profile;
import retrofit2.Call;
import retrofit2.http.GET;

interface UsersApi {

    @GET("/index/profile")
    Call<UpstoxResponse<Profile>> getProfile();
}
