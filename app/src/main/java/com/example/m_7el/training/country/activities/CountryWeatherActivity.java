package com.example.m_7el.training.country.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.m_7el.training.country.fragments.CountryInofFragment;
import com.example.m_7el.training.country.fragments.CountryListFragment;
import com.example.m_7el.training.R;
import com.example.m_7el.training.country.adapters.RecyclerViewAdapter;
import com.example.m_7el.training.country.fragments.WeatherFragment;
import com.example.m_7el.training.net.models.CountryInfo;
import com.example.m_7el.training.net.models.Main;
import com.example.m_7el.training.net.clients.WeatherApiClient2;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.util.List;

public class CountryWeatherActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

    private Toolbar mToolbar;
    private List<CountryInfo> mCountryInfo;
    private RetrofitInterface retrofitInterface;
    private List<Main> main;
    private List<Double> latlng = null;
    private CountryInofFragment countryInfo;
    private WeatherFragment weatherInfo;
    FragmentManager fragmentManager1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_weather);
        retrofitInterface = WeatherApiClient2.getClient().create(RetrofitInterface.class);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryListFragment mfragment = new CountryListFragment();
              fragmentManager1 = getSupportFragmentManager();
               FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();

                fragmentTransaction1.replace(R.id.container1, mfragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
              //  fragmentTransaction1.addToBackStack(null).commit();

            }
        });

          countryInfo = new CountryInofFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment1, countryInfo);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        weatherInfo=new WeatherFragment();

        FragmentManager fragmentManager2 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        fragmentTransaction2.replace(R.id.fragment2, weatherInfo);
        fragmentTransaction2.addToBackStack(null);
        fragmentTransaction2.commit();



        }




    @Override
    public void onItemClick(CountryInfo countryInfo1) {
        if (countryInfo!=null) {
            countryInfo.setCountry(countryInfo1);




        }
        if (weatherInfo!=null) {
            weatherInfo.setWeather(countryInfo1);

        }

        fragmentManager1.popBackStack();

    }


}
