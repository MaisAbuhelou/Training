package com.example.m_7el.training.country;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.m_7el.training.country.models.CountryInfo;

import java.util.List;


public class WeatherViewPagerAdapter extends FragmentPagerAdapter {

    private final List<CountryInfo> mCountryInfo;
    private final CountryInfoFragment mCountryInfoFragment;
    private int mPageCounts;

    WeatherViewPagerAdapter(FragmentManager manager, CountryInfoFragment countryInfoFragment, List<CountryInfo> countryInfo, int pageCounts) {
        super(manager);
        mPageCounts = pageCounts;
        mCountryInfo = countryInfo;
        mCountryInfoFragment =countryInfoFragment;
    }


    @Override
    @NonNull
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putParcelable("mCountry", mCountryInfo.get(position));
        mCountryInfoFragment.setCountry(mCountryInfo.get(position));
        return mCountryInfoFragment;

    }

    @Override
    public int getCount() {
        return mPageCounts;
    }

}
