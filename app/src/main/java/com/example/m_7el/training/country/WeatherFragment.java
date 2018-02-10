package com.example.m_7el.training.country;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.BaseFragment;
import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.models.WeatherDetails;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.net.clients.RetrofitInterface;
import com.example.m_7el.training.net.clients.WeatherApiClient;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFragment extends BaseFragment {
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private TabLayout tabs;
    private ViewPager viewPager;
    private CountryInfo mCountryInfo;
    private WeatherData mWeatherInfo;
    private WeatherViewPagerAdapter mPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("WeatherFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        tabs = view.findViewById(R.id.tabLayout);

        if (savedInstanceState != null) {
            mCountryInfo = savedInstanceState.getParcelable("country");
        }
        if (mCountryInfo != null) {
            loadContent();
        }
        setupViewPager();
        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager() {

        mPagerAdapter = new WeatherViewPagerAdapter(getContext(), getFragmentManager());
        if (mTodayWeather() != null) {
            mPagerAdapter.setTodayWeatherInfo(mTodayWeather());
        }
        if (mTomorrowWeather() != null) {
            mPagerAdapter.setTomorrowWeatherInfo(mTomorrowWeather());
        }
        viewPager.setAdapter(mPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    //get data from countries list in activity
    public void setCountry(final CountryInfo countryInfo) {
        mCountryInfo = countryInfo;
        loadContent();
    }

    private void loadContent() {
        if (mCountryInfo.getLatlng().size() == 0) return;
        Call<WeatherData> call2 = WeatherApiClient
                .getClient()
                .create(RetrofitInterface.class)
                .getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
        call2.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                mWeatherInfo = response.body();
                setupViewPager();
            }

            @Override
            public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                LogMessages.getMessage(Arrays.toString(t.getStackTrace()));
            }
        });
    }

    @Nullable
    public WeatherInfo mTodayWeather() {
        if (mWeatherInfo == null) return null;
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String today = dateFormat.format(calendar.getTime());

        for (WeatherDetails details : mWeatherDetails) {
            String todayDate = details.getDtTxt().split(" ")[0];
            if (todayDate.equalsIgnoreCase(String.valueOf(today))) {
                return details.getWeatherInfo();
            }
        }
        return null;
    }

    @Nullable
    public WeatherInfo mTomorrowWeather() {
        if (mWeatherInfo == null) return null;
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String tomorrow = dateFormat.format(calendar.getTime());

        for (WeatherDetails details : mWeatherDetails) {
            String tomorrowDate = details.getDtTxt().split(" ")[0];
            if (tomorrowDate.equalsIgnoreCase(String.valueOf(tomorrow))) {
                return details.getWeatherInfo();
            }
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("country", mCountryInfo);
    }

}


