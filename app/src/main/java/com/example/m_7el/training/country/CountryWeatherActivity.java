package com.example.m_7el.training.country;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
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
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.net.clients.CountryApiClient;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CountryWeatherActivity extends AppCompatActivity
        implements CountriesRecyclerViewAdapter.CountrySelectListener {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private CountryListFragment mCountryListFragment;
    private WeatherPagerAdapter mPagerAdapter;
    private ViewPager viewPager;
    private List<CountryInfo> mCountryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_weather);
        setToolbar();
        setNavigation();
        viewPager = findViewById(R.id.viewpager);

        mCountryListFragment = (CountryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_country_list);
        mCountryListFragment.setCountrySelectionListener(this);
        if (savedInstanceState != null) {
            mCountryInfo = savedInstanceState.getParcelableArrayList("country");
            mCountryListFragment.setRecyclerView(mCountryInfo);
            setUpViewPager();
        } else {
            getCountriesInfo();
        }
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
    public void onCountrySelected(int position) {
        viewPager.setCurrentItem(position);
        mDrawerLayout.closeDrawer(GravityCompat.START);

    }

    //    // get countries
    private void getCountriesInfo() {

        Call<List<CountryInfo>> call2 = CountryApiClient.getClient().create(RetrofitInterface.class).getCountyInfo();
        call2.enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryInfo>> call, @NonNull Response<List<CountryInfo>> response) {
                mCountryInfo = response.body();
                mCountryListFragment.setRecyclerView(mCountryInfo);
                setUpViewPager();
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryInfo>> call, @NonNull Throwable t) {

                t.printStackTrace();
            }
        });
    }

    private void setUpViewPager() {
        mPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(), mCountryInfo);
        viewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("country", (ArrayList<? extends Parcelable>) mCountryInfo);

    }

}
