package com.example.m_7el.training.country;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.m_7el.training.country.models.Info;

import java.util.ArrayList;
import java.util.List;


public class WeatherViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    WeatherViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }



}