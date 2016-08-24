package com.clockworkant.redsky.ui;

import com.clockworkant.redsky.network.model.Forecast;

public interface MainView {
    void showError(String message);

    void showForecast(Forecast forecast);
}
