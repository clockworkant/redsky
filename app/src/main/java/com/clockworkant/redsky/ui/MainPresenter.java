package com.clockworkant.redsky.ui;

import com.clockworkant.redsky.network.OpenWeatherApi;
import com.clockworkant.redsky.network.model.Forecast;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {
    private MainView view;
    private final OpenWeatherApi weatherApi;

    public MainPresenter(MainView view, OpenWeatherApi weatherApi) {
        this.view = view;
        this.weatherApi = weatherApi;
    }

    public void onRefresh() {
        weatherApi.listForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Forecast>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Forecast forecast) {
                        view.showForecast(forecast);
                    }
                });
    }
}
