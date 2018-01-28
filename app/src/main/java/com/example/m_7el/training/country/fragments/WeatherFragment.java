package com.example.m_7el.training.country.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.adapters.HomeViewPagerAdapter;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.LogMessages;


public class WeatherFragment extends Fragment {


    private CountryInfo mCountryInfo;
    private TabLayout tabs;
    private ViewPager viewPager;
    private TomorrowFragment tomorrowFragment;
    private TodayFragment todayFragment;
    private CallBacks mCallback;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("WeatherFragment");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Setting ViewPager for each Tabs
        viewPager = view.findViewById(R.id.viewpager);
        tabs = view.findViewById(R.id.tabLayout);
        todayFragment = new TodayFragment();
        tomorrowFragment = new TomorrowFragment();


        setData();

        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager, CountryInfo country) {

        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(todayFragment, "Today");
        adapter.addFragment(tomorrowFragment, "Tomorrow");
        mCallback = adapter;
        viewPager.setAdapter(adapter);
    }

    //get data from countries list in activity
    public void setWeather(CountryInfo countryInfo) {
        mCountryInfo = countryInfo;
        mCallback.onSelectedFragment(mCountryInfo);
        setData();

    }

    private void setData() {
        tabs.setupWithViewPager(viewPager);
        setupViewPager(viewPager, mCountryInfo);

    }

    public interface CallBacks {
        void onSelectedFragment(CountryInfo countryInfo);

    }

}
