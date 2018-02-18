package com.example.m_7el.training.country;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.m_7el.training.country.models.CountryInfo;

import java.util.List;


public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private final List<CountryInfo> mCountryInfo;

    WeatherPagerAdapter(FragmentManager manager, List<CountryInfo> countryInfo) {
        super(manager);
        mCountryInfo = countryInfo;
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        CountryInfoFragment mCountryInfoFragment = new CountryInfoFragment();
        if (mCountryInfo != null)
            args.putParcelable("mCountry", mCountryInfo.get(position));
        mCountryInfoFragment.setArguments(args);
        return mCountryInfoFragment;
    }

    @Override
    public int getCount() {
        return mCountryInfo == null ? 0 : mCountryInfo.size();
    }

}


