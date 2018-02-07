package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class DayWeatherFragment extends Fragment implements WeatherFragment.SelectedFragmentListener {

    private List<WeatherDetails> mWeatherDetails;
    private TextView date, humidity, pressure, temp;
    private String myDate = "day";
    private String dayDate;

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
        if (savedInstanceState != null) {
            Log.d("ssss", "sd");
            mWeatherDetails = savedInstanceState.getParcelableArrayList("weather");
            dayDate = savedInstanceState.getString("dayDate");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("weather", (ArrayList<? extends Parcelable>) mWeatherDetails);
        outState.putString("dayDate", dayDate);
    }

    @Override
    public void selectedFragment(Info weatherInfo, String day) {
        myDate = day;
        if (weatherInfo != null) {
            mWeatherDetails = weatherInfo.getWeatherDetails();
            Calendar calendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
            if (day.equals("today")) {
                dayDate = mdformat.format(calendar.getTime());
            } else if (day.equals("tomorrow")) {
                Date now = new Date();
                calendar.setTime(now);
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                dayDate = mdformat.format(calendar.getTime());
            }
            if (!isAdded()) return;
            showData();
        }
    }

    @SuppressLint("SetTextI18n")
    private void showData() {

        if (mWeatherDetails == null) return;
        for (int i = 0; i < mWeatherDetails.size(); i++) {
            String[] mdate = mWeatherDetails.get(i).getDtTxt().split(" ");
            if (mdate[0].equals(dayDate)) {
                date.setText(dayDate);
                Main main = mWeatherDetails.get(i).getMain();
                pressure.setText(main.getPressure() + "");
                humidity.setText(main.getHumidity() + "");
                temp.setText(main.getTempMin() + " - " + main.getTempMax());
                break;
            }
        }
    }
}
