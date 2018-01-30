package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TomorrowFragment extends Fragment implements WeatherFragment.CallBacks {


    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private Info weatherInfo;
    private List<WeatherDetails> weatherDetails;
    private Main main;
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

        return view;
    }

    @Override
    public void onSelectedFragment(CountryInfo mCountryInfo) {

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

                    for (int i = 0; i < weatherDetails.size(); i++) {

                        String[] neww = weatherDetails.get(i).getDtTxt().split(" ");
                        if (neww[0].equals(tomorrowDate)) {
                            date.setText(tomorrowDate);
                            main = weatherDetails.get(i).getMain();
                            pressure.setText(main.getPressure() + "");
                            humidity.setText(main.getHumidity() + "");
                            temp.setText(main.getTempMin() + " - " + main.getTempMax());
                            break;
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<Info> call, @NonNull Throwable t) {
                    t.getStackTrace();
                }
            });
        }

    }
}