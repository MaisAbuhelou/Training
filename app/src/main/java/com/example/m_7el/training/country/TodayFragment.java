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
import java.util.List;


public class TodayFragment extends Fragment implements WeatherFragment.SelectedFragmentListener {

    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private Info mWeatherInfo;
    private List<WeatherDetails> mWeatherDetails;
    private TextView date, humidity, pressure, temp;
    private String todayDate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogMessages.getMessage("TodayFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_layouts, container, false);

        date = view.findViewById(R.id.country_date);
        temp = view.findViewById(R.id.country_temp);
        pressure = view.findViewById(R.id.pressure);
        humidity = view.findViewById(R.id.humidity);
        if (savedInstanceState == null) {
            Log.i("state null", "null");
        }
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mWeatherDetails = savedInstanceState.getParcelableArrayList("weather");
            todayDate = savedInstanceState.getString("date");
            Log.d("ll", "null");
            setData();
        }

        return view;
    }

    @Override
    public void SelectedFragment(Info weatherInfo) {
        mWeatherInfo = weatherInfo;
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = mdformat.format(calendar.getTime());

        if (weatherInfo != null) {
            mWeatherDetails = weatherInfo.getWeatherDetails();
        }
        setData();


    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        if (mWeatherDetails!=null){
        for (int i = 0; i < this.mWeatherDetails.size(); i++) {
            String[] mdate = this.mWeatherDetails.get(i).getDtTxt().split(" ");
            if (mdate[0].equals(todayDate)) {
                date.setText(todayDate);
                Main main = this.mWeatherDetails.get(i).getMain();
                pressure.setText(main.getPressure() + "");
                humidity.setText(main.getHumidity() + "");
                temp.setText(main.getTempMin() + " - " + main.getTempMax());
                break;
            }
        }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
        outState.putParcelableArrayList("weather", (ArrayList<? extends Parcelable>) mWeatherDetails);
        outState.putString("date", todayDate);

    }
}
