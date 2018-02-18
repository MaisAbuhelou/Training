package com.example.m_7el.training.country;


import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.country.models.WeatherInfo;

import java.util.List;


public interface Apis {

    void getCountryInfo(ApiCallback<List<CountryInfo>, String > callbacks);

    void getWeatherInfo(double latitude, double longitude,String key, ApiCallback<WeatherData, String > callbacks);


}
