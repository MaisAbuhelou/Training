package com.example.m_7el.training.country;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.m_7el.training.country.models.Info;

import java.util.ArrayList;
import java.util.List;


public class WeatherViewPagerAdapter extends FragmentPagerAdapter implements WeatherFragment.SelectedFragmentListener {
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

    void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


    @Override
    public void selectedFragment(Info weatherInfo, String day) {
        ((DayWeatherFragment) mFragmentList.get(0)).selectedFragment(weatherInfo, "today");
        ((DayWeatherFragment) mFragmentList.get(1)).selectedFragment(weatherInfo, "tomorrow");
    }
}