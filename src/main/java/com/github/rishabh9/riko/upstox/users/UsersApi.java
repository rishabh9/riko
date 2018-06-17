package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.users.models.Profile;
import com.github.rishabh9.riko.upstox.users.models.ProfileBalance;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface UsersApi {

    @GET("/index/profile")
    Call<UpstoxResponse<Profile>> getProfile();

    @GET("/live/profile/balance")
    Call<UpstoxResponse<ProfileBalance>> getProfileBalance(@Query("type") String type);
}
