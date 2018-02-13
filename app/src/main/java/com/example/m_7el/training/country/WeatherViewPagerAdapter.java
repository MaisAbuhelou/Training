package com.example.m_7el.training.country;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.utils.DateUtil;
import com.example.m_7el.training.country.utils.LogMessages;

import java.util.ArrayList;
import java.util.List;


public class WeatherViewPagerAdapter extends FragmentPagerAdapter {
    private final static int TODAY_INDEX = 0;
    private final static int TOMORROW_INDEX = 1;
    private final static int PAGES_COUNT = 2;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private List<WeatherDayInfoFragment> mFragmentList;

    WeatherViewPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mContext = context;
        mFragmentManager = manager;
        mFragmentList = new ArrayList<>();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (position < 0 || position >= mFragmentList.size()) return;
        mFragmentManager.beginTransaction().
                remove(mFragmentManager.findFragmentByTag("fragment:" + position))
                .commit();
        mFragmentList.remove(position);
        LogMessages.getMessage("destroyItem: " + position);
    }

    @NonNull
    @Override
    public Fragment instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = getItem(position);
        mFragmentManager.beginTransaction()
                .add(container.getId(), fragment, "fragment:" + position)
                .commit();
        LogMessages.getMessage("instantiateItem: " + position + " size: " + mFragmentList.size());
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragmentList.size() != PAGES_COUNT) {
            Bundle args = new Bundle();
            WeatherDayInfoFragment mFragment = new WeatherDayInfoFragment();
            args.putSerializable(WeatherDayInfoFragment.EXTRA_DATE, position == 0 ? DateUtil.getToday() : DateUtil.getTomorrow());
            mFragment.setArguments(args);
            mFragmentList.add(mFragment);
            Log.d("newFragmentAdded", "new fragment added");
        }
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return PAGES_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == TODAY_INDEX ? mContext.getString(R.string.today) : mContext.getString(R.string.tomorrow);
    }

    void setTodayWeatherInfo(WeatherInfo weatherInfo) {
        WeatherDayInfoListener mTodayWeatherInfoListener = (WeatherDayInfoListener) getItem(TODAY_INDEX);
        mTodayWeatherInfoListener.weatherDayInfo(weatherInfo);
    }

    void setTomorrowWeatherInfo(WeatherInfo weatherInfo) {
        WeatherDayInfoListener mTomorrowWeatherInfoListener = (WeatherDayInfoListener) getItem(TOMORROW_INDEX);
        mTomorrowWeatherInfoListener.weatherDayInfo(weatherInfo);
    }
}