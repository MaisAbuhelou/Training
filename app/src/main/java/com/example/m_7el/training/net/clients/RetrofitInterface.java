package com.example.m_7el.training.net.clients;

import com.example.m_7el.training.net.models.CountryInfo;
import com.example.m_7el.training.net.models.Info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by m_7el on 1/17/2018.
 */

public interface RetrofitInterface {


    // get Country
    @GET("https://restcountries.eu/rest/v1/all")
    Call<List<CountryInfo>> getCountyInfo();
    // get Country
    @GET("data/2.5/forecast")
    Call<Info> getWeatherInfo(@Query("lat") double lat, @Query("lon")double lon, @Query("appid")  String key);
}
