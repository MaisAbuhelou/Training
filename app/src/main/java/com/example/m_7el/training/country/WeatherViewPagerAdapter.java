package com.example.m_7el.training.country;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeatherViewPagerAdapter extends FragmentPagerAdapter {
    private final static String EXTRA_DATE = WeatherDayInfoFragment.class + "_DATE_EXTRA";
    private final FragmentManager mManager;
    private Context mContext;
    private WeatherDayInfoListener mTodayWeatherInfoListener;
    private WeatherDayInfoListener mTomorrowWeatherInfoListener;
    private ArrayList<WeatherDayInfoFragment> mFragmentsLists = new ArrayList<>();
    private WeatherInfo mWeatherInfo;
    private boolean check;

    WeatherViewPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mManager = manager;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Calendar calendar = Calendar.getInstance();
        Bundle args = new Bundle();
        if (position == 0) {
            WeatherDayInfoFragment todayFragment = new WeatherDayInfoFragment();
            args.putSerializable(EXTRA_DATE, calendar);
            todayFragment.setArguments(args);
            mTodayWeatherInfoListener = todayFragment;
            return todayFragment;
        }
        if (position == 1) {
            WeatherDayInfoFragment tomorrowFragment = new WeatherDayInfoFragment();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            args.putSerializable(EXTRA_DATE, calendar);
            tomorrowFragment.setArguments(args);
            mTomorrowWeatherInfoListener = tomorrowFragment;
            return tomorrowFragment;

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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        assert (0 <= position && position < mFragmentsLists.size());
        mManager.beginTransaction().
                remove(mFragmentsLists.get(position)).
                commit();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {

        Fragment fragment = getItem(position);
        mManager.beginTransaction()
                .add(container.getId(), fragment)
                .commit();
        return fragment;
    }

    void setTodayWeatherInfo(WeatherInfo weatherInfo) {
        mTodayWeatherInfoListener.weatherDayInfo(weatherInfo);
    }

    void setTomorrowWeatherInfo(WeatherInfo weatherInfo) {
          mTomorrowWeatherInfoListener.weatherDayInfo(weatherInfo);

    }
}
