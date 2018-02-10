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
import android.widget.Toast;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.country.models.WeatherDetails;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.utils.DateUtil;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.net.clients.RetrofitInterface;
import com.example.m_7el.training.net.clients.WeatherApiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFragment extends Fragment {
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private TabLayout tabs;
    private ViewPager viewPager;
    private CountryInfo mCountryInfo;
    WeatherInfo todayWeatherInfo;
    WeatherInfo tomorrowWeatherInfo;
    private WeatherViewPagerAdapter mPagerAdapter;
    private WeatherData mWeatherInfo;
    private SimpleDateFormat dateFormat;


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
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        if (savedInstanceState != null) {
            mCountryInfo = savedInstanceState.getParcelable("country");
        }
        if (mCountryInfo != null) {
            GetCountryWeather();
        }
        setupViewPager();

        return view;
    }


    @Nullable
    public WeatherInfo mTodayWeather() {
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = DateUtil.getToday();
        String today = dateFormat.format(calendar.getTime());
        for (WeatherDetails details : mWeatherDetails) {
            String todayDate = details.getDtTxt().split(" ")[0];
            if (todayDate.equalsIgnoreCase(String.valueOf(today))) {
                todayWeatherInfo = details.getWeatherInfo();
                return details.getWeatherInfo();
            }
        }
        return null;
    }

    @Nullable
    public WeatherInfo mTomorrowWeather() {
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = DateUtil.getTomorrow();
        String tomorrow = dateFormat.format(calendar.getTime());

        for (WeatherDetails details : mWeatherDetails) {
            String tomorrowDate = details.getDtTxt().split(" ")[0];
            if (tomorrowDate.equalsIgnoreCase(String.valueOf(tomorrow))) {
                tomorrowWeatherInfo = details.getWeatherInfo();
                return details.getWeatherInfo();
            }
        }
        return null;

    }

    // Add Fragments to Tabs
    private void setupViewPager() {
        mPagerAdapter = new WeatherViewPagerAdapter(getContext(), getFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }


    //get countryData  from activity
    public void setCountry(final CountryInfo countryInfo) {
        mCountryInfo = countryInfo;
        GetCountryWeather();

    }

    // get weather for the selected country
    private void GetCountryWeather() {
        if (mCountryInfo.getLatlng().size() != 0) {
            Call<WeatherData> call2 = WeatherApiClient.getClient().create(RetrofitInterface.class).getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
            call2.enqueue(new Callback<WeatherData>() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {

                    mWeatherInfo = response.body();
                    mPagerAdapter.setTodayWeatherInfo(mTodayWeather());
                    mPagerAdapter.setTomorrowWeatherInfo(mTomorrowWeather());

                }

                @Override
                public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                    t.getStackTrace();
                    Toast.makeText(getContext(), "error loading", Toast.LENGTH_LONG);
                    setCountry(mCountryInfo);
                }
            });
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("country", mCountryInfo);

    }

}


