package com.example.m_7el.training.net.clients;

import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.WeatherData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitInterface {


    // get Country
    @GET("https://restcountries.eu/rest/v1/all")
    Call<List<CountryInfo>> getCountyInfo();

    // get country weather
    @GET("data/2.5/forecast")
    Call<WeatherData> getWeatherInfo(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String key);

}
