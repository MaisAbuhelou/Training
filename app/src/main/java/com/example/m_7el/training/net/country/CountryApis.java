package com.example.m_7el.training.net.country;


import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.net.ApiCallback;

import java.util.List;


public interface CountryApis {

    void getCountryInfo(ApiCallback<List<CountryInfo>, String> callbacks);
}
