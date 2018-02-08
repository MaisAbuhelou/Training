package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.utils.LogMessages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeatherDayInfoFragment extends Fragment implements WeatherDayInfoListener {
    private final static String EXTRA_DATE =WeatherDayInfoFragment.class +"_DATE_EXTRA";

    private TextView mHumidity;
    private TextView mPressure;
    private TextView mTemp;
    private WeatherInfo mWeatherInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("WeatherDayInfoFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day_weather, container, false);
        TextView mDateTextView = view.findViewById(R.id.country_date);
        mTemp = view.findViewById(R.id.country_temp);
        mPressure = view.findViewById(R.id.pressure);
        mHumidity = view.findViewById(R.id.humidity);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        if (getArguments()!=null) {
            Calendar calendar = (Calendar) getArguments().getSerializable(EXTRA_DATE);
            mDateTextView.setText(dateFormat.format(calendar.getTime()));
        }
        if (savedInstanceState!=null){
            mWeatherInfo=savedInstanceState.getParcelable("weather");
            weatherDayInfo(mWeatherInfo);
        }
        return view;
    }


    @SuppressLint("DefaultLocale")
    @Override
    public void weatherDayInfo(WeatherInfo weatherInfo) {
        mWeatherInfo=weatherInfo;
        mPressure.setText(String.valueOf(weatherInfo.getPressure()));
        mHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
        mTemp.setText(String.format("%f - %f ", weatherInfo.getTempMin(), weatherInfo.getTempMax()));

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("weather", mWeatherInfo);
    }
}
