package com.clockworkant.redsky.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.clockworkant.redsky.R;
import com.clockworkant.redsky.network.OpenWeatherApi;
import com.clockworkant.redsky.network.model.Forecast;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this, getWeatherApi());

        initRefresh();
    }

    private void initRefresh() {
        findViewById(R.id.main_refresh_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.onRefresh();
            }
        });
    }

    private OpenWeatherApi getWeatherApi() {
        String baseUrl = getResources().getString(R.string.openweatherapi_baseurl);
        String apikey = getResources().getString(R.string.openweatherapi_key);
        return new OpenWeatherApi(baseUrl, apikey);
    }

    @Override
    public void showError(String message) {
        Log.d(TAG, "showError() called with: " + "message = [" + message + "]");
    }

    @Override
    public void showForecast(Forecast forecast) {
        Log.d(TAG, "showForecast() called with: " + "forecast = [" + forecast + "]");
    }
}
