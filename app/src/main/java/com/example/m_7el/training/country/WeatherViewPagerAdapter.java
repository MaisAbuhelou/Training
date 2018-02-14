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

    private int mPageCounts;

    WeatherViewPagerAdapter( FragmentManager manager,int pageCounts) {
        super(manager);
        mPageCounts=pageCounts;
    }
    @Override
    @NonNull
    public Fragment getItem(int position) {

        return new WeatherDayInfoFragment();
    }

    @Override
    public int getCount() {
        return mPageCounts;
    }

}
