package com.clockworkant.redsky.network;

import com.clockworkant.redsky.network.model.Forecast;
import com.clockworkant.redsky.network.model.ForecastResponse;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface OpenWeatherApiService {
    enum Units {
        standard,
        metric,
        imperial;

        @Override
        public String toString() {
            //standard is default, so units is empty
            if (this == standard) {
                return "";
            } else {
                return super.toString();
            }
        }
    }

    @GET("data/2.5/forecast")
    Observable<ForecastResponse> listForecast(@Query("q") String location,
                                              @Query("units") Units unit,
                                              @Query("appid") String appId);
}
