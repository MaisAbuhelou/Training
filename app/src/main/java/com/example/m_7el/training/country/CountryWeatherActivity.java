package com.example.m_7el.training.country;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import com.example.m_7el.training.country.di.MyApp;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.databinding.ActivityCountryWeatherBinding;
import com.example.m_7el.training.net.ApiCallback;
import com.example.m_7el.training.net.country.CountryApis;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class CountryWeatherActivity extends AppCompatActivity
        implements CountriesRecyclerViewAdapter.CountrySelectListener, View.OnClickListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private CountryListFragment mCountryListFragment;
    private ViewPager viewPager;
    private List<CountryInfo> mCountryInfo;
    private ActivityCountryWeatherBinding binding;
    @Inject
    CountryApis countryApiImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_country_weather);
        setToolbar();
        setNavigation();
        ((MyApp) getApplicationContext()).getMyComponent().inject(this);
        binding.setListener(this);
        viewPager = binding.viewpager;
        mCountryListFragment = (CountryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_country_list);
        mCountryListFragment.setCountrySelectionListener(this);
        if (savedInstanceState == null)
            getCountriesInfo();

        if (savedInstanceState != null) {
            binding.setLoading(false);
            binding.setError(false);
            mCountryInfo = savedInstanceState.getParcelableArrayList("country");
            mCountryListFragment.setRecyclerView(mCountryInfo);
            setUpViewPager();
        }
    }

    private void setToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @SuppressLint("SetTextI18n")
    private void setNavigation() {
        mDrawerLayout =binding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView mNavigationView = binding.navView;
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
    public void onCountrySelected(int position) {
        viewPager.setCurrentItem(position);
        mDrawerLayout.closeDrawer(GravityCompat.START);

    }

    // get countries
    private void getCountriesInfo() {
        binding.setLoading(true);
        binding.setError(false);
        countryApiImp.getCountryInfo(new ApiCallback<List<CountryInfo>, String>() {
            @Override
            public void onSuccess(List<CountryInfo> countryInfo) {
                binding.setLoading(false);
                mCountryListFragment.setRecyclerView(countryInfo);
                mCountryInfo=countryInfo;
                setUpViewPager();
            }

            @Override
            public void onError(String error) {
                binding.setLoading(false);
                binding.setError(true);

            }
        });
    }

    private void setUpViewPager() {
        WeatherPagerAdapter mPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(), mCountryInfo);
        viewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("country", (ArrayList<? extends Parcelable>) mCountryInfo);

    }

    @Override
    public void onClick(View v) {
        getCountriesInfo();
    }
}
