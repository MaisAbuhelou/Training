package com.example.m_7el.training.net.weather;


import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.net.AbstractRetrofitClients;
import com.example.m_7el.training.net.ApiCallback;
import com.example.m_7el.training.net.RetrofitInterface;

public class WeatherApiImp extends AbstractRetrofitClients<RetrofitInterface> implements WeatherApis {
    private static final String BASE_URL = "http://api.openweathermap.org/";

    public WeatherApiImp() {
        super(BASE_URL, RetrofitInterface.class);
    }


    @Override
    public void getWeatherInfo(double latitude, double longitude, String key, ApiCallback<WeatherData, String> callbacks) {
        enqueueCall(mApis.getWeatherInfo(latitude, longitude, key), callbacks);
    }
}