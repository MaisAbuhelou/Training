package com.example.m_7el.training.country.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.net.models.CountryInfo;
import com.example.m_7el.training.net.models.Info;
import com.example.m_7el.training.net.models.Main;
import com.example.m_7el.training.net.models.WeatherDetails;
import com.example.m_7el.training.net.clients.WeatherApiClient2;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TomorrowFragment extends Fragment implements WeatherFragment.CallBacks {


    private RetrofitInterface retrofitInterface;
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private Info weatherInfo;
    private List<WeatherDetails> weatherDetails;
    private Main main;
    private TextView date, humidity, pressure, temp;
    private ImageView mCountryImage;
    private WeatherFragment.CallBacks mCallback;
    private String tomorrowDate;


    public TomorrowFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_layouts, container, false);
        date =  view.findViewById(R.id.country_date);
        temp =  view.findViewById(R.id.country_Temp);
        pressure =  view.findViewById(R.id.pressure);
        humidity =  view.findViewById(R.id.humidity);
        mCountryImage = view.findViewById(R.id.country_image);

        return view;
    }

    @Override
    public void onSelectedFragment(CountryInfo mCountryInfo) {
        retrofitInterface = WeatherApiClient2.getClient().create(RetrofitInterface.class);
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        tomorrowDate = mdformat.format(calendar.getTime());

        Call<Info> call2 = retrofitInterface.getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
        call2.enqueue(new Callback<Info>() {


            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

                weatherInfo = response.body();
                weatherDetails = weatherInfo.getWeatherDetails();
                int i = 0;
                for (i = 0; i < weatherDetails.size(); i++) {

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
            public void onFailure(Call<Info> call, Throwable t) {
                t.getMessage();
            }
        });
    }


}
