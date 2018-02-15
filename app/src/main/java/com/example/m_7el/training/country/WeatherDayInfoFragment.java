package com.example.m_7el.training.country;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.country.models.WeatherDetails;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.utils.DateUtil;
import com.example.m_7el.training.country.utils.LogMessages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WeatherDayInfoFragment extends Fragment {
    public final static String EXTRA_DATE = WeatherDayInfoFragment.class + "_DATE_EXTRA";

    private TextView mHumidity;
    private TextView mPressure;
    private TextView mTemp;
    private SimpleDateFormat dateFormat;
    private TextView mDate;
    private Toolbar mToolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("WeatherDayInfoFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day_weather, container, false);
        mToolbar =view.findViewById(R.id.toolbar);
        mTemp = view.findViewById(R.id.country_temp);
        mPressure = view.findViewById(R.id.pressure);
        mHumidity = view.findViewById(R.id.humidity);
        mDate = view.findViewById(R.id.country_date);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return view;
    }


    public void weatherDayInfo(WeatherInfo weatherInfo) {
        if (weatherInfo == null || !isAdded()) return;
        mPressure.setText(String.valueOf(weatherInfo.getPressure()));
        mHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
        mTemp.setText(String.format(Locale.ENGLISH, "%f - %f ", weatherInfo.getTempMin(), weatherInfo.getTempMax()));
    }

    public void setTodayWeather(WeatherData mWeatherInfo) {
        mToolbar.setTitle("Today");
        if (mWeatherInfo == null) return;
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = DateUtil.getToday();
        String today = dateFormat.format(calendar.getTime());
        mDate.setText(today);

        for (WeatherDetails details : mWeatherDetails) {
            String todayDate = details.getDtTxt().split(" ")[0];
            if (todayDate.equalsIgnoreCase(String.valueOf(today))) {
                weatherDayInfo(details.getWeatherInfo());
            }
        }

    }

    public void setTomorrowWeather(WeatherData mWeatherInfo) {
        mToolbar.setTitle("Tomorrow");
        if (mWeatherInfo == null) return;

        Calendar calendar = DateUtil.getTomorrow();
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        String tomorrow = dateFormat.format(calendar.getTime());
        mDate.setText(tomorrow);

        for (WeatherDetails details : mWeatherDetails) {
            String tomorrowDate = details.getDtTxt().split(" ")[0];
            if (tomorrowDate.equalsIgnoreCase(String.valueOf(tomorrow))) {
                weatherDayInfo(details.getWeatherInfo());
            }
        }

    }


}