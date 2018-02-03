package com.example.m_7el.training.country;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.m_7el.training.country.models.CountryInfo;

import java.util.ArrayList;
import java.util.List;


public class HomeViewPagerAdapter extends FragmentPagerAdapter implements WeatherFragment.SelectedFragmentListener {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public HomeViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) { return mFragmentList.get(position); }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mFragmentTitleList.get(position);
    }

    @Override
    public void SelectedFragment(CountryInfo country) {

        ((TodayFragment) mFragmentList.get(0)).SelectedFragment(country);
        ((TomorrowFragment) mFragmentList.get(1)).SelectedFragment(country);
    }
}