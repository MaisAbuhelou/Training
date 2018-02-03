package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.net.clients.RetrofitInterface;
import com.example.m_7el.training.net.clients.WeatherApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TomorrowFragment extends Fragment implements WeatherFragment.SelectedFragmentListener {


    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private Info weatherInfo;
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
            setData();

        }

        return view;
    }

    @Override
    public void SelectedFragment(CountryInfo mCountryInfo) {
        this.mCountryInfo = mCountryInfo;

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        tomorrowDate = mdformat.format(calendar.getTime());
        if (mCountryInfo.getLatlng().size() != 0) {
            Call<Info> call2 = WeatherApiClient.getClient().create(RetrofitInterface.class).getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
            call2.enqueue(new Callback<Info>() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<Info> call, @NonNull Response<Info> response) {

                    weatherInfo = response.body();
                    if (weatherInfo != null) {
                        weatherDetails = weatherInfo.getWeatherDetails();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setData();
                        }
                    }, 1000);
                }

                @Override
                public void onFailure(@NonNull Call<Info> call, @NonNull Throwable t) {
                    t.getStackTrace();
                }
            });
        }
    }


    @SuppressLint("SetTextI18n")
    private void setData() {
        for (int i = 0; i < weatherDetails.size(); i++) {
            String[] mdate = weatherDetails.get(i).getDtTxt().split(" ");
            if (mdate[0].equals(tomorrowDate)) {
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
