package com.clockworkant.redsky.network;

import com.clockworkant.redsky.network.model.Forecast;
import com.clockworkant.redsky.network.model.ForecastResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

public class OpenWeatherApi {

    private final OpenWeatherApiService openWeatherApiService;
    private final String appId;

    public OpenWeatherApi(String baseUrl, String appId) {
        this.appId = appId;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        openWeatherApiService = retrofit.create(OpenWeatherApiService.class);
    }

    public OpenWeatherApi(OpenWeatherApiService service){
        openWeatherApiService = service;
        appId = "";
    }

    public Observable<Forecast> listForecast(){
        return openWeatherApiService.listForecast("London, uk", OpenWeatherApiService.Units.metric, appId).flatMap(new Func1<ForecastResponse, Observable<Forecast>>() {
            @Override
            public Observable<Forecast> call(ForecastResponse forecastResponse) {
                return Observable.from(forecastResponse.list);
            }
        });
    }
}
