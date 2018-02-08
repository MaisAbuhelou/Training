package com.example.m_7el.training.country;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
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
import com.example.m_7el.training.country.models.CountryInfo;

public class CountryWeatherActivity extends AppCompatActivity
        implements CountriesRecyclerViewAdapter.CountrySelectListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private CountryInfoFragment mCountryInfoFragment;
    private WeatherFragment mWeatherInfoFragment;
    private CountryListFragment mCountryListFragment;
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_weather);
        setToolbar();
        orientation = this.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setNavigation();
        }

        mCountryListFragment = (CountryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_country_list);
        mCountryListFragment.setCountrySelectionListener(this);
        mCountryInfoFragment = (CountryInfoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_countryInfo);
        mWeatherInfoFragment = (WeatherFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_weather_info);
    }

    private void setToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @SuppressLint("SetTextI18n")
    private void setNavigation() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        View header = mNavigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.name);
        ImageView userImage = header.findViewById(R.id.user_image);
        name.setText("Mais Abu Helou");
        Glide.with(this)
                .load(R.drawable.profile_image)
                .apply(RequestOptions.circleCropTransform())
                .into(userImage);
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onCountrySelected(CountryInfo countryInfo) {
        if (mCountryInfoFragment != null) {
            mCountryInfoFragment.setCountry(countryInfo);
        }
        if (mWeatherInfoFragment != null) {
            mWeatherInfoFragment.setCountry(countryInfo);
        }
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
            mDrawerLayout.closeDrawer(GravityCompat.START);
    }


}
