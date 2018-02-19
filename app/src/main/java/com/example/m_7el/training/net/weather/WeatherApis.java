package com.example.m_7el.training.net.weather;

import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.net.ApiCallback;


public interface WeatherApis {

    void getWeatherInfo(double latitude, double longitude, String key, ApiCallback<WeatherData, String> callbacks);


}
