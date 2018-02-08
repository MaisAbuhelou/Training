package com.example.m_7el.training.country;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class WeatherViewPagerAdapter extends FragmentStatePagerAdapter {
    private final static String EXTRA_DATE = WeatherDayInfoFragment.class + "_DATE_EXTRA";

    private Context mContext;
    private WeatherDayInfoListener mTodayWeatherInfoListener;
    private WeatherDayInfoListener mTomorrowWeatherInfoListener;
    private WeatherDayInfoFragment fragment;
    WeatherViewPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        fragment = new WeatherDayInfoFragment();
        if (position == 0) {
            args.putSerializable(EXTRA_DATE, calendar);
            fragment.setArguments(args);
            mTodayWeatherInfoListener = fragment;

            return fragment;
        }
        if (position == 1) {
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            args.putSerializable(EXTRA_DATE, calendar);
            fragment.setArguments(args);
            mTomorrowWeatherInfoListener = fragment;

            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return mContext.getString(R.string.today);
        } else {
            return mContext.getString(R.string.tomorrow);

        }
    }

    void setTodayWeatherInfo(WeatherInfo weatherInfo) {
        if (mTodayWeatherInfoListener == null){
            fragment= (WeatherDayInfoFragment) getItem(0);
            mTodayWeatherInfoListener = fragment;
    }

        mTodayWeatherInfoListener.weatherDayInfo(weatherInfo);
    }

    void setTomorrowWeatherInfo(WeatherInfo weatherInfo) {
        if (mTomorrowWeatherInfoListener == null){
            fragment= (WeatherDayInfoFragment) getItem(1);

            mTomorrowWeatherInfoListener = fragment;

        }
        mTomorrowWeatherInfoListener.weatherDayInfo(weatherInfo);

    }
}
