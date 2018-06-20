package com.github.rishabh9.riko.upstox.historical;

import com.github.rishabh9.riko.upstox.common.models.UpstoxResponse;
import com.github.rishabh9.riko.upstox.historical.models.Candle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface HistoricalApi {

    @GET("/historical/ohlc/{exchange}/{symbol}")
    Call<UpstoxResponse<List<Candle>>> getOhlc(@Path("exchange") String exchange,
                                               @Path("symbol") String symbol,
                                               @Query("interval") String interval,
                                               @Query("start_date") String startDate,
                                               @Query("end_date") String endDate,
                                               @Query("format") String format);
}
