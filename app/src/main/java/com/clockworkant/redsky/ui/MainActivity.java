package com.clockworkant.redsky.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.clockworkant.redsky.R;
import com.clockworkant.redsky.network.OpenWeatherApi;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
    private MainPresenter mainPresenter;
    private ForecastsViewModelAdapter adapter;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this, getWeatherApi());

        initRefresh();
        initForecastList();
        mainPresenter.onRefresh();
    }

    private void initForecastList() {
        adapter = new ForecastsViewModelAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_forecast_recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRefresh() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.main_swipe_container);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
        Toast.makeText(MainActivity.this, "showError() called with: " + "message = [" + message + "]", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showForecasts(List<ForecastViewModel> forecasts) {
        adapter.setForecasts(forecasts);
    }

    @Override
    public void showLoading(boolean isLoading) {
        swipeContainer.setRefreshing(isLoading);
    }

    @Override
    public void clearForecasts() {
        adapter.clear();
    }

}
