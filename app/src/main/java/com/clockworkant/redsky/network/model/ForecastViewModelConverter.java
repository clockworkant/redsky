package com.clockworkant.redsky.network.model;

import com.clockworkant.redsky.ui.ForecastViewModel;

import rx.functions.Func1;

public class ForecastViewModelConverter implements Func1<Forecast, ForecastViewModel> {
    @Override
    public ForecastViewModel call(final Forecast forecast) {
        //TODO implement null checking

        return new ForecastViewModel() {
            @Override
            public String getTitle() {
                return forecast.weather.get(0).main;
            }

            @Override
            public String getDesc() {
                return forecast.weather.get(0).description;
            }

            @Override
            public String getDateTimeString() {
                return forecast.dt_txt;
            }
        };
    }
}
