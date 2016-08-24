package com.clockworkant.redsky.network;

import android.support.annotation.NonNull;

import com.clockworkant.redsky.network.model.Forecast;
import com.clockworkant.redsky.network.model.ForecastResponse;
import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherApiTest {

    @Test
    public void testDeserialization() throws Exception {
        OpenWeatherApi api = new OpenWeatherApi(getOpenWeatherApiService());

        TestSubscriber<Forecast> testSubscriber = new TestSubscriber<>();
        api.listForecast().subscribe(testSubscriber);
        List<Forecast> forecastList = testSubscriber.getOnNextEvents();

        assertThat(forecastList, hasSize(37));
    }

    @NonNull
    private OpenWeatherApiService getOpenWeatherApiService() throws IOException {
        String forecastResponseString = IOUtils.toString(getClass().getClassLoader().getResource("forecastResponseString.json"), "UTF-8");
        Gson gson = new Gson();
        ForecastResponse forecastResponse = gson.fromJson(forecastResponseString, ForecastResponse.class);

        OpenWeatherApiService openWeatherApiService = mock(OpenWeatherApiService.class);
        when(openWeatherApiService.listForecast(anyString(), any(OpenWeatherApiService.Units.class), anyString()))
                .thenReturn(Observable.just(forecastResponse));
        return openWeatherApiService;
    }
}