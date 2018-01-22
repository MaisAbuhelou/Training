package com.example.m_7el.training.country.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.country.utils.HomeViewPagerAdapter;
import com.example.m_7el.training.R;
import com.example.m_7el.training.net.models.CountryInfo;


public class WeatherFragment extends Fragment {


    private CountryInfo countryInfo;
    private TabLayout tabs;
    private ViewPager viewPager;
    private TomorrowFragment tomorrowFragment;
    private TodayFragment todayFragment;
    private CallBacks mCallback;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Setting ViewPager for each Tabs
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // Set Tabs inside Toolbar
        tabs = (TabLayout) view.findViewById(R.id.tabLayout);
        todayFragment=new TodayFragment();
        tomorrowFragment=new TomorrowFragment();

        setData();

        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager, CountryInfo country) {


        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getChildFragmentManager(),country);


        adapter.addFragment(todayFragment, "Today");
        adapter.addFragment(tomorrowFragment, "Tomorrow");
        mCallback= (CallBacks)adapter;
        viewPager.setAdapter(adapter);


    }

    public void onButtonPressed(CountryInfo countryInfo) {
        if (mCallback != null) {
          mCallback.onSelectedFragment(countryInfo);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
    public void setWeather(CountryInfo mCountryInfo) {
        countryInfo = mCountryInfo;

        mCallback.onSelectedFragment(mCountryInfo);
        setData();

    }

    private void setData() {
        tabs.setupWithViewPager(viewPager);
        setupViewPager(viewPager, countryInfo);

    }

    public interface  CallBacks{
        void onSelectedFragment(CountryInfo country);

    }

}
