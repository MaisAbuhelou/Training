package com.example.m_7el.training.country;


import android.support.annotation.NonNull;

import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.net.clients.CountryApiClient;
import com.example.m_7el.training.net.clients.RetrofitInterface;
import com.example.m_7el.training.net.clients.WeatherApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiImp implements Apis {

    @Override
    public void getCountryInfo(ApiCallback<List<CountryInfo>, String> callbacks) {
        Call<List<CountryInfo>> call2 = CountryApiClient.getClient().create(RetrofitInterface.class).getCountyInfo();
        call2.enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryInfo>> call, @NonNull Response<List<CountryInfo>> response) {

            }

            @Override
            public void onFailure(@NonNull Call<List<CountryInfo>> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void getWeatherInfo(double latitude, double longitude, String key, ApiCallback<WeatherData, String> callbacks) {
        Call<WeatherData> call2 = WeatherApiClient
                .getClient()
                .create(RetrofitInterface.class)
                .getWeatherInfo(latitude, longitude, key);
        call2.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {

            }

            @Override
            public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {

            }
        });
    }
}
