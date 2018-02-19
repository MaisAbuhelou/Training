package com.example.m_7el.training.country.di;

import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.country.utils.PhotoManagerImp;
import com.example.m_7el.training.net.country.CountryApiImp;
import com.example.m_7el.training.net.country.CountryApis;
import com.example.m_7el.training.net.weather.WeatherApiImp;
import com.example.m_7el.training.net.weather.WeatherApis;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    PhotoManager getPhotoMangerModule() {
        return new PhotoManagerImp();
    }

    @Provides
    CountryApis getCountryApi() {
        return new CountryApiImp();
    }

    @Provides
    WeatherApis getWeatherApi() {
        return new WeatherApiImp();
    }
}
