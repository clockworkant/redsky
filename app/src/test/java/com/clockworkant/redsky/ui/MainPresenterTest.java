package com.clockworkant.redsky.ui;

import com.clockworkant.redsky.network.OpenWeatherApi;
import com.clockworkant.redsky.network.model.Forecast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView view;

    @Mock
    OpenWeatherApi openWeatherApi;

    private MainPresenter presenter;

    @Before
    public void setup() {
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
        presenter = new MainPresenter(view, openWeatherApi);
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void onRefresh_clearOldAndShowNewForecasts() throws Exception {
        when(openWeatherApi.listForecast()).thenReturn(Observable.<Forecast>empty());
        presenter.onRefresh();
        verify(view).showLoading(true);
        verify(view).clearForecasts();
        verify(openWeatherApi).listForecast();
        verify(view).showForecasts(anyList());
        verify(view).showLoading(false);
        verifyNoMoreInteractions(view, openWeatherApi);
    }
}