package com.github.rishabh9.riko.upstox.users;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.users.models.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

interface UsersApi {

    @GET("/index/profile")
    Call<UpstoxResponse<Profile>> getProfile();

    @GET("/live/profile/balance")
    Call<UpstoxResponse<ProfileBalance>> getProfileBalance(@Query("type") String type);

    @GET("/live/profile/positions")
    Call<UpstoxResponse<List<Position>>> getPositions();

    @GET("/live/profile/holdings")
    Call<UpstoxResponse<List<Holding>>> getHoldings();

    @GET("/index/master-contract/{exchange}")
    Call<UpstoxResponse<List<String>>> getMasterContracts(@Path("exchange") String exchange);

    @GET("/index/master-contract/{exchange}")
    Call<UpstoxResponse<Contract>> getMasterContract(@Path("exchange") String exchange,
                                                     @Query("symbol") String symbol,
                                                     @Query("token") String token);
}
