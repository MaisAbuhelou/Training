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


public class TomorrowFragment extends Fragment implements WeatherFragment.SelectedFragmentListener {


    private List<WeatherDetails> mWeatherDetails;
    private TextView date, humidity, pressure, temp;
    private String tomorrowDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("TomorrowFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            tomorrowDate = savedInstanceState.getString("date");
            setData();

        }

        return view;
    }

    @Override
    public void SelectedFragment(@NonNull Info weatherInfo) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        tomorrowDate = mdformat.format(calendar.getTime());
        mWeatherDetails = weatherInfo.getWeatherDetails();
        setData();
    }


    @SuppressLint("SetTextI18n")
    private void setData() {
        for (int i = 0; i < this.mWeatherDetails.size(); i++) {
            String[] mdate = this.mWeatherDetails.get(i).getDtTxt().split(" ");
            if (mdate[0].equals(tomorrowDate)) {
                date.setText(tomorrowDate);
                Main main = this.mWeatherDetails.get(i).getMain();
                pressure.setText(main.getPressure() + "");
                humidity.setText(main.getHumidity() + "");
                temp.setText(main.getTempMin() + " - " + main.getTempMax());
                break;
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's state here
        outState.putParcelableArrayList("weather", (ArrayList<? extends Parcelable>) mWeatherDetails);
        outState.putString("date", tomorrowDate);

    }
}
