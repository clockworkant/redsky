package com.clockworkant.redsky.network.model;

public class Forecast {
    private long dt;
    private Main main;

    public long getDateTime() {
        return dt;
    }

    public float getTemp() {
        return main.temp;
    }

    private static class Main {
        float temp;
        float temp_min;
        float temp_max;
        float pressure;
        float sea_level;
        float grnd_level;
        float humidity;
        float temp_kf;
    }
}
