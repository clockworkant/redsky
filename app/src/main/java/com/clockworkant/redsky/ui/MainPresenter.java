package com.clockworkant.redsky.ui;

import com.clockworkant.redsky.network.OpenWeatherApi;
import com.clockworkant.redsky.network.model.ForecastViewModelConverter;

import java.util.List;

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
        view.showLoading(true);
        view.clearForecasts();
        weatherApi.listForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new ForecastViewModelConverter())
                .toList()
                .subscribe(new Observer<List<ForecastViewModel>>() {
                    @Override
                    public void onCompleted() {
                        view.showLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        view.showLoading(false);
                    }

                    @Override
                    public void onNext(List<ForecastViewModel> forecastViewModels) {
                        view.showForecasts(forecastViewModels);
                    }
                });
    }
}
