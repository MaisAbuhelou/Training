package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.di.MyApp;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.DateUtil;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.country.utils.PhotoManager;

import java.util.Calendar;

import javax.inject.Inject;


public class CountryInfoFragment extends Fragment {

    private TextView mCountyName;
    private TextView mCountyRegion;
    private TextView mCountyPopulation;
    private TextView mCountyCapital;
    private ImageView mCountryImage;
    private CountryInfo mCountryInfo;
    private String flag = "PS";

    @Inject
    PhotoManager photoManager;
    private WeatherDayInfoFragment mTodayWeatherFragment;
    private WeatherDayInfoFragment mTomorrowWeatherFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryInfoFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_info, container, false);

        mCountyName = view.findViewById(R.id.country_name);
        mCountyRegion = view.findViewById(R.id.country_region);
        mCountyPopulation = view.findViewById(R.id.population);
        mCountyCapital = view.findViewById(R.id.country_capital);
        mCountryImage = view.findViewById(R.id.country_image);
        mTodayWeatherFragment = (WeatherDayInfoFragment) getChildFragmentManager().findFragmentById(R.id.fragment_weather_today);
        mTomorrowWeatherFragment = (WeatherDayInfoFragment) getChildFragmentManager().findFragmentById(R.id.fragment_weather_tomorrow);
        Bundle args =new Bundle();
        args.putSerializable(WeatherDayInfoFragment.EXTRA_DATE, DateUtil.getToday());
        mTodayWeatherFragment.setArguments(args);
        args.putSerializable(WeatherDayInfoFragment.EXTRA_DATE, DateUtil.getTomorrow());
        mTomorrowWeatherFragment.setArguments(args);
        ((MyApp) getActivity().getApplicationContext()).getMyComponent().inject(this);
        if (getArguments() != null) {
            mCountryInfo = getArguments().getParcelable("mCountry");

            setData(mCountryInfo);
        }
        if (savedInstanceState != null) {
            mCountryInfo = savedInstanceState.getParcelable("country");
            setData(mCountryInfo);
        }

        return view;
    }

    public void setCountry(CountryInfo country) {
        mCountryInfo = country;
        setData(mCountryInfo);
    }

    @SuppressLint("SetTextI18n")
    private void setData(CountryInfo countryInfo) {

        if (countryInfo != null) {
            mCountyName.setText(countryInfo.getName());
            mCountyRegion.setText(countryInfo.getRegion());
            mCountyPopulation.setText(countryInfo.getPopulation() + "");
            mCountyCapital.setText(countryInfo.getCapital());
            if (countryInfo.getAltSpellings().size() != 0) {
                flag = countryInfo.getAltSpellings().get(0);

            }
            photoManager.loadImage(getActivity(), mCountryImage, flag);

            mTodayWeatherFragment.setCountry(countryInfo);
            mTomorrowWeatherFragment.setCountry(countryInfo);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putParcelable("country", mCountryInfo);

    }

}
