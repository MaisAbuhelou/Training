package com.example.m_7el.training.country;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherEvent;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.utils.DateUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class WeatherViewPagerAdapter extends FragmentPagerAdapter {
    private final static int TODAY_INDEX = 0;
    private final static int PAGES_COUNT = 2;
    private Context mContext;
    private List<WeatherDayInfoFragment> mFragmentList;

    WeatherViewPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        mContext = context;
        mFragmentList = new ArrayList<>();
    }

    @Override
    @NonNull
    public Fragment getItem(int position) {
        if (mFragmentList.isEmpty() || mFragmentList.size() != PAGES_COUNT || mFragmentList.get(position) == null) {
            Bundle args = new Bundle();
            WeatherDayInfoFragment mFragment = new WeatherDayInfoFragment();
            args.putSerializable(WeatherDayInfoFragment.EXTRA_DATE, position == 0 ? DateUtil.getToday() : DateUtil.getTomorrow());
            mFragment.setArguments(args);
            mFragment.setDay(position);
            mFragmentList.add(mFragment);
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

    void setDayWeather(List<WeatherInfo> weatherInfo) {
        for (int i = 0; i < weatherInfo.size(); i++) {
            EventBus.getDefault().post(new WeatherEvent()
                    .setData(weatherInfo)
                    .setType(i));
        }
    }
}
