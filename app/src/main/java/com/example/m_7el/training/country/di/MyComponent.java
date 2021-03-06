package com.example.m_7el.training.country.di;


import com.example.m_7el.training.country.CountryInfoFragment;
import com.example.m_7el.training.country.CountryViewHolder;
import com.example.m_7el.training.country.CountryWeatherActivity;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface MyComponent {
    void inject(CountryViewHolder viewHolder);
    void inject(CountryInfoFragment countryInfoFragment);
    void inject(CountryWeatherActivity countryWeatherActivity);

}
