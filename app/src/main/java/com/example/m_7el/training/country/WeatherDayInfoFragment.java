package com.example.m_7el.training.country;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDayInfoFragment extends Fragment {
    public final static String EXTRA_DATE = WeatherDayInfoFragment.class + "_DATE_EXTRA";
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";

    private TextView mHumidity;
    private TextView mPressure;
    private TextView mTemp;
    private WeatherData mWeatherInfo;
    private SimpleDateFormat dateFormat;
    private CountryInfo mCountryInfo;


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
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        if (getArguments() != null) {
            Calendar calendar = (Calendar) getArguments().getSerializable(EXTRA_DATE);
            mDateTextView.setText(dateFormat.format(calendar.getTime()));
        }

        return view;
    }



    public void weatherDayInfo(WeatherInfo weatherInfo) {
        if (mWeatherInfo == null || !isAdded()) return;
        mPressure.setText(String.valueOf(weatherInfo.getPressure()));
        mHumidity.setText(String.valueOf(weatherInfo.getHumidity()));
        mTemp.setText(String.format(Locale.ENGLISH, "%f - %f ", weatherInfo.getTempMin(), weatherInfo.getTempMax()));
    }


    //get data from countries list in activity
    public void setCountry(final CountryInfo countryInfo) {
        mCountryInfo = countryInfo;
        getWeather();
    }


    private void getWeather() {
        if (mCountryInfo.getLatlng().size() == 0) return;
        Call<WeatherData> call2 = WeatherApiClient
                .getClient()
                .create(RetrofitInterface.class)
                .getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
        call2.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(@NonNull Call<WeatherData> call, @NonNull Response<WeatherData> response) {
                mWeatherInfo = response.body();
                List<WeatherInfo> mWeatherInfo = new ArrayList<>();
                if (setTodayWeather() != null) {
                    weatherDayInfo(setTodayWeather());
                }
                if (setTomorrowWeather() != null) {
                    weatherDayInfo(setTodayWeather());

                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherData> call, @NonNull Throwable t) {
                LogMessages.getMessage(Arrays.toString(t.getStackTrace()));
            }
        });
    }

    @Nullable
    public WeatherInfo setTodayWeather() {
        if (mWeatherInfo == null) return null;
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = DateUtil.getToday();
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
    public WeatherInfo setTomorrowWeather() {
        if (mWeatherInfo == null) return null;
        List<WeatherDetails> mWeatherDetails = mWeatherInfo.getWeatherDetails();
        Calendar calendar = DateUtil.getTomorrow();
        String tomorrow = dateFormat.format(calendar.getTime());

        for (WeatherDetails details : mWeatherDetails) {
            String tomorrowDate = details.getDtTxt().split(" ")[0];
            if (tomorrowDate.equalsIgnoreCase(String.valueOf(tomorrow))) {
                return details.getWeatherInfo();
            }
        }
        return null;
    }



}