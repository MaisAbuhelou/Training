package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.di.MyApp;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.WeatherData;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.databinding.FragmentCountryInfoBinding;
import com.example.m_7el.training.net.ApiCallback;
import com.example.m_7el.training.net.weather.WeatherApis;

import javax.inject.Inject;

public class CountryInfoFragment extends Fragment implements View.OnClickListener {
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";

    private TextView mCountyName;
    private TextView mCountyRegion;
    private TextView mCountyPopulation;
    private TextView mCountyCapital;
    private ImageView mCountryImage;
    private CountryInfo mCountryInfo;
    private String flag = "PS";

    @Inject
    PhotoManager photoManager;
    @Inject
    WeatherApis weatherApiImp;

    private WeatherInfoFragment mTodayWeatherFragment;
    private WeatherInfoFragment mTomorrowWeatherFragment;
    private FragmentCountryInfoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryInfoFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_country_info, container, false);
        View view = binding.getRoot();
        binding.setListener(this);
        mCountyName = binding.countryName;
        mCountyRegion = binding.countryRegion;
        mCountyPopulation = binding.population;
        mCountyCapital = binding.countryCapital;
        mCountryImage = binding.countryImage;
        mTodayWeatherFragment = (WeatherInfoFragment) getChildFragmentManager().findFragmentById(R.id.today_weather);
        mTomorrowWeatherFragment = (WeatherInfoFragment) getChildFragmentManager().findFragmentById(R.id.tomorrow_weather);

        ((MyApp) getActivity().getApplicationContext()).getMyComponent().inject(this);
        if (getArguments() != null) {
            mCountryInfo = getArguments().getParcelable("mCountry");
            setData(mCountryInfo);
        }
        return view;
    }

    public void setCountry(CountryInfo country) {
        mCountryInfo = country;
        setData(mCountryInfo);
    }

    private void getWeather() {
        binding.setLoading(true);
        binding.setError(false);
        weatherApiImp.getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY, new ApiCallback<WeatherData, String>() {
            @Override
            public void onSuccess(WeatherData weatherData) {
                mTodayWeatherFragment.setTodayWeather(weatherData);
                mTomorrowWeatherFragment.setTomorrowWeather(weatherData);
                binding.setLoading(false);
            }

            @Override
            public void onError(String error) {
                binding.setLoading(false);
                binding.setError(true);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setData(CountryInfo countryInfo) {

        if (countryInfo != null) {
            mCountyName.setText(countryInfo.getName());
            mCountyRegion.setText(countryInfo.getRegion());
            mCountyPopulation.setText(countryInfo.getPopulation() + "");
            mCountyCapital.setText(countryInfo.getCapital());
            if (countryInfo.getAltSpellings().size() != 0) {
                flag = countryInfo.getAltSpellings().get(0);
            }
            photoManager.loadImage(getActivity(), mCountryImage, flag);
            getWeather();

        }
    }

    @Override
    public void onClick(View v) {
        getWeather();
    }
}
