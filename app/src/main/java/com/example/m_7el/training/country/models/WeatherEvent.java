package com.example.m_7el.training.country.models;

import java.util.List;


public class WeatherEvent {

    private List<WeatherInfo> data;
    private int type = 0;
    public List<WeatherInfo> getData() {
        return data;
    }


    public WeatherEvent setType(int type) {
        this.type = type;
        return this;
    }

    public WeatherEvent setData(List<WeatherInfo> data) {
        this.data = data;
        return this;
    }
}
