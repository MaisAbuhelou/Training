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
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.Info;
import com.example.m_7el.training.country.models.Main;
import com.example.m_7el.training.country.models.WeatherDetails;
import com.example.m_7el.training.country.utils.GetCountryWeather;
import com.example.m_7el.training.country.utils.LogMessages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TomorrowFragment extends Fragment implements WeatherFragment.CallBacks {


    private List<WeatherDetails> weatherDetails;
    private TextView date, humidity, pressure, temp;
    private String tomorrowDate;
    private CountryInfo mCountryInfo;

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
            mCountryInfo = savedInstanceState.getParcelable("country");
            weatherDetails = savedInstanceState.getParcelableArrayList("weather");
            tomorrowDate = savedInstanceState.getString("date");
            setData(weatherDetails);
        }
        return view;
    }

    @Override
    public void onSelectedFragment(CountryInfo mCountryInfo) {
        this.mCountryInfo = mCountryInfo;
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        tomorrowDate = mdformat.format(calendar.getTime());
        if (mCountryInfo.getLatlng().size() != 0) {
            Info weatherInfo = GetCountryWeather.getWeather(mCountryInfo);
            if (weatherInfo != null) {
                List<WeatherDetails> weatherDetails = weatherInfo.getWeatherDetails();
                this.weatherDetails=weatherDetails;
                setData(weatherDetails);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void setData(List<WeatherDetails>weatherDetails) {

        for (int i = 0; i < weatherDetails.size(); i++) {
            String[] neww = weatherDetails.get(i).getDtTxt().split(" ");
            if (neww[0].equals(tomorrowDate)) {
                date.setText(tomorrowDate);
                Main main = weatherDetails.get(i).getMain();
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
        outState.putParcelable("country", mCountryInfo);
        outState.putParcelableArrayList("weather", (ArrayList<? extends Parcelable>) weatherDetails);
        outState.putString("date", tomorrowDate);
    }
}
