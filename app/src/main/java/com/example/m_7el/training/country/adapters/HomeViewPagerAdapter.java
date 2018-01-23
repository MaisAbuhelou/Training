package com.example.m_7el.training.country.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.m_7el.training.country.fragments.TodayFragment;
import com.example.m_7el.training.country.fragments.TomorrowFragment;
import com.example.m_7el.training.country.fragments.WeatherFragment;
import com.example.m_7el.training.net.models.CountryInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mais on 7/27/2017.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter  implements WeatherFragment.CallBacks
{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private  CountryInfo countryInfo;
    private TodayFragment todayFragment;
    private TomorrowFragment tomorrowFragment;
private  int position;
    public HomeViewPagerAdapter(FragmentManager manager,CountryInfo countryInfo) {

        super(manager);
        this.countryInfo=countryInfo;


    }

    @Override
    public Fragment getItem(int position) {
       this. position=position;
        return mFragmentList.get(position);

    }

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
    public void onSelectedFragment(CountryInfo country) {
        todayFragment= (TodayFragment) mFragmentList.get(0);

        tomorrowFragment= (TomorrowFragment) mFragmentList.get(1);
        todayFragment.onSelectedFragment(country);
        tomorrowFragment.onSelectedFragment(country);
    }
}
