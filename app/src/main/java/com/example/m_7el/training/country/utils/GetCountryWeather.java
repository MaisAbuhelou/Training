package com.example.m_7el.training.country.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.Info;
import com.example.m_7el.training.country.models.WeatherDetails;
import com.example.m_7el.training.net.clients.RetrofitInterface;
import com.example.m_7el.training.net.clients.WeatherApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetCountryWeather {
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private  Info weatherInfo;

    public  Info getWeather(CountryInfo mCountryInfo) {
        Call<Info> call2 = WeatherApiClient.getClient().create(RetrofitInterface.class).getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
        call2.enqueue(new Callback<Info>() {


            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Info> call, @NonNull Response<Info> response) {

                weatherInfo = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<Info> call, @NonNull Throwable t) {
                t.getStackTrace();
            }
        });
        return weatherInfo;
    }
}

