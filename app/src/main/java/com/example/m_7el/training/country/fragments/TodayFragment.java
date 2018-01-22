package com.example.m_7el.training.country.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.fragments.WeatherFragment;
import com.example.m_7el.training.net.models.CountryInfo;
import com.example.m_7el.training.net.models.Info;
import com.example.m_7el.training.net.models.Main;
import com.example.m_7el.training.net.models.WeatherDetails;
import com.example.m_7el.training.net.clients.WeatherApiClient2;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TodayFragment extends Fragment implements WeatherFragment.CallBacks {


    private RetrofitInterface retrofitInterface;
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";
    private Info weatherInfo;
    private List<WeatherDetails> weatherDetails;
    private Main main;
    private TextView date, humidity, pressure, temp;
    private ImageView mCountryImage;
    private CountryInfo countryInfo;
    private WeatherFragment.CallBacks mCallback;
    private String todayDate;
    private String dateSplit;

    public TodayFragment() {
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

        date = (TextView) view.findViewById(R.id.country_date);
        temp = (TextView) view.findViewById(R.id.country_Temp);
        pressure = (TextView) view.findViewById(R.id.pressure);
        humidity = (TextView) view.findViewById(R.id.humidity);
        mCountryImage = view.findViewById(R.id.country_image);

        return view;
    }

    @Override
    public void onSelectedFragment(CountryInfo countryInfo) {

        retrofitInterface = WeatherApiClient2.getClient().create(RetrofitInterface.class);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");

        todayDate = mdformat.format(calendar.getTime());
        Call<Info> call2 = retrofitInterface.getWeatherInfo(countryInfo.getLatlng().get(0), countryInfo.getLatlng().get(1), API_KEY);
        call2.enqueue(new Callback<Info>() {


            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {

                weatherInfo = response.body();
                weatherDetails = weatherInfo.getWeatherDetails();
                int i = 0;


                for (i = 0; i < weatherDetails.size(); i++) {


                    String[] neww = weatherDetails.get(i).getDtTxt().split(" ");
                    if (neww[0].equals(todayDate)) {
                        date.setText(todayDate);
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
