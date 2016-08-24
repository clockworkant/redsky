package com.clockworkant.redsky.network.model;

import java.util.List;

public class Forecast {
    long dt;
    String dt_txt;
    Main main;
    List<Weather> weather;

    static class Main {
        float temp;
        float temp_min;
        float temp_max;
        float pressure;
        float sea_level;
        float grnd_level;
        float humidity;
        float temp_kf;
    }

    static class Weather {
        int id;
        String main;
        String description;
        String icon;
    }
}
