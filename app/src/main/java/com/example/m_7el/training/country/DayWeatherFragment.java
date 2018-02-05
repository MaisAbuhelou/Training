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
import com.example.m_7el.training.country.models.Info;
import com.example.m_7el.training.country.models.Main;
import com.example.m_7el.training.country.models.WeatherDetails;
import com.example.m_7el.training.country.utils.LogMessages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DayWeatherFragment extends Fragment {

    private List<WeatherDetails> mWeatherDetails;
    private TextView date, humidity, pressure, temp;
    private String myDate;
    private String todayDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogMessages.getMessage("DayWeatherFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_day_weather, container, false);

        date = view.findViewById(R.id.country_date);
        temp = view.findViewById(R.id.country_temp);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        myDate = getArguments().getString("date");

        if (savedInstanceState != null) {
            mWeatherDetails = savedInstanceState.getParcelableArrayList("weather");
        }
        showData();
        return view;
    }


    @SuppressLint("SetTextI18n")
    private void showData() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        if (myDate.equals("today")) {
            todayDate = mdformat.format(calendar.getTime());
        }
        if (myDate.equals("tom")) {
            Date now = new Date();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            todayDate = mdformat.format(calendar.getTime());
        }
        if (mWeatherDetails == null) return;
        for (int i = 0; i < mWeatherDetails.size(); i++) {
            String[] mdate = mWeatherDetails.get(i).getDtTxt().split(" ");
            if (mdate[0].equals(todayDate)) {
                date.setText(todayDate);
                Main main = mWeatherDetails.get(i).getMain();
                pressure.setText(main.getPressure() + "");
                humidity.setText(main.getHumidity() + "");
                temp.setText(main.getTempMin() + " - " + main.getTempMax());
                break;
            }
        }
    }

    public void setFragmentData(Info weatherInfo) {

        if (weatherInfo != null) {
            mWeatherDetails = weatherInfo.getWeatherDetails();
            if (!isAdded()) return;
            showData();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("weather", (ArrayList<? extends Parcelable>) mWeatherDetails);
    }
}
