package com.clockworkant.redsky.ui;


import java.util.List;

public interface MainView {
    void showError(String message);

    void showForecasts(List<ForecastViewModel> forecast);

    void showLoading(boolean isLoading);

    void clearForecasts();
}
