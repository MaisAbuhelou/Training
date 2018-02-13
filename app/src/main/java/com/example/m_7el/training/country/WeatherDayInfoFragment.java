package com.example.m_7el.training.country;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.WeatherEvent;
import com.example.m_7el.training.country.models.WeatherInfo;
import com.example.m_7el.training.country.utils.LogMessages;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WeatherDayInfoFragment extends Fragment implements WeatherDayInfoListener {
    public final static String EXTRA_DATE = WeatherDayInfoFragment.class + "_DATE_EXTRA";

    private TextView mHumidity;
    private TextView mPressure;
    private TextView mTemp;
    private WeatherInfo mWeatherInfo;
    private List<WeatherInfo> list = new ArrayList<>();
    public int day = 0;

    public void setDay(int day) {
        this.day = day;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("WeatherDayInfoFragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day_weather, container, false);
        TextView mDateTextView = view.findViewById(R.id.country_date);
        mTemp = view.findViewById(R.id.country_temp);
        mPressure = view.findViewById(R.id.pressure);
        mHumidity = view.findViewById(R.id.humidity);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        if (getArguments() != null) {
            Calendar calendar = (Calendar) getArguments().getSerializable(EXTRA_DATE);
            mDateTextView.setText(dateFormat.format(calendar.getTime()));
        }
        if (savedInstanceState != null) {
            day = savedInstanceState.getInt("day");
            list.add((WeatherInfo) savedInstanceState.getParcelable("todayWeatherInfo"));
            list.add((WeatherInfo) savedInstanceState.getParcelable("tomorrowWeatherInfo"));
        }
        for (int i = 0; i < list.size(); i++)
            weatherDayInfo(list.get(i));
        return view;
    }

    @Override
    public void weatherDayInfo(WeatherInfo weatherInfo) {
        if (mWeatherInfo == null || !isAdded()) return;
        mPressure.setText(String.valueOf(weatherInfo.getPressure()));
        mHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
        mTemp.setText(String.format(Locale.ENGLISH, "%f - %f ", weatherInfo.getTempMin(), weatherInfo.getTempMax()));

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (list != null && !list.isEmpty()) {
            outState.putInt("day", day);
            outState.putParcelable("todayWeatherInfo", list.get(0));
            outState.putParcelable("tomorrowWeatherInfo", list.get(1));
        }
    }


    @Subscribe
    public void updateData(WeatherEvent event) {
        list = event.getData();
        mWeatherInfo = list.get(day);
        weatherDayInfo(mWeatherInfo);
    }

}
