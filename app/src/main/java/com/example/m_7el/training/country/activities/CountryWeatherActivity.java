package com.example.m_7el.training.country.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.m_7el.training.R;
import com.example.m_7el.training.country.adapters.RecyclerViewAdapter;
import com.example.m_7el.training.country.fragments.CountryInfoFragment;
import com.example.m_7el.training.country.fragments.WeatherFragment;
import com.example.m_7el.training.country.models.CountryInfo;


public class CountryWeatherActivity extends AppCompatActivity
        implements RecyclerViewAdapter.OnItemClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDraweLayout;
    private CountryInfoFragment mCountryInfoFragment;
    private WeatherFragment mWeatherInfoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_weather);
        setToolbar();
        setNavigation();
        setCountryFragment();
        setWeatherFragment();
    }

    private void setToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @SuppressLint("SetTextI18n")
    private void setNavigation() {
        mDraweLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDraweLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDraweLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        View header = mNavigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.name);
        ImageView userImage = header.findViewById(R.id.user_image);
        name.setText("Mais Abu Helou");
        Glide.with(this)
                .load(R.drawable.download)
                .apply(RequestOptions.circleCropTransform())
                .into(userImage);
    }

    private void setCountryFragment() {
        mCountryInfoFragment = new CountryInfoFragment();
        mCountryInfoFragment = (CountryInfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_countryInfo);
    }

    private void setWeatherFragment() {
        mWeatherInfoFragment = new WeatherFragment();
        mWeatherInfoFragment = (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_weather_info);
    }

    @Override
    public void onBackPressed() {

        if (mDraweLayout.isDrawerOpen(GravityCompat.START)) {
            mDraweLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(CountryInfo countryInfo) {
        if (mCountryInfoFragment != null) {
            mCountryInfoFragment.setCountry(countryInfo);
        }
        if (mWeatherInfoFragment != null) {
            mWeatherInfoFragment.setWeather(countryInfo);
        }
        mDraweLayout.closeDrawer(GravityCompat.START);

    }


}
