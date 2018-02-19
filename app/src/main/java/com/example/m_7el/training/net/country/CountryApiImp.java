package com.example.m_7el.training.net.country;


import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.net.AbstractRetrofitClients;
import com.example.m_7el.training.net.ApiCallback;
import com.example.m_7el.training.net.RetrofitInterface;

import java.util.List;

public class CountryApiImp extends AbstractRetrofitClients<RetrofitInterface> implements CountryApis {
    private static final String BASE_URL = "https://restcountries.eu/";

    public CountryApiImp() {
        super(BASE_URL, RetrofitInterface.class);
    }

    @Override
    public void getCountryInfo(final ApiCallback<List<CountryInfo>, String> callbacks) {
        enqueueCall(mApis.getCountyInfo(), callbacks);
    }


}