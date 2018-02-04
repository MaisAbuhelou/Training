package com.example.m_7el.training.country;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.models.Info;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.net.clients.RetrofitInterface;
import com.example.m_7el.training.net.clients.WeatherApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherFragment extends Fragment {
    private final static String API_KEY = "1867722b6af87e1d0388e10c5a94be34";

    private TabLayout tabs;
    private ViewPager viewPager;
    private SelectedFragmentListener mSelectedFragment;
    private TodayFragment todayFragment;
    private TomorrowFragment tomorrowFragment;
    private CountryInfo mCountryInfo;
    private Info weatherInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("WeatherFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        // Setting ViewPager for each Tabs
        viewPager = view.findViewById(R.id.viewpager);
        tabs = view.findViewById(R.id.tabLayout);
        if (savedInstanceState == null) {
            todayFragment = new TodayFragment();
            tomorrowFragment = new TomorrowFragment();
        }
        if (savedInstanceState != null) {
            mCountryInfo = savedInstanceState.getParcelable("country");
            todayFragment = (TodayFragment) getChildFragmentManager().getFragment(savedInstanceState, "todayFragment");
            tomorrowFragment = (TomorrowFragment) getChildFragmentManager().getFragment(savedInstanceState, "tomorrowFragment");
        }
        setData();
        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(todayFragment, "Today");
        adapter.addFragment(tomorrowFragment, "Tomorrow");
        mSelectedFragment = adapter;
        viewPager.setAdapter(adapter);
    }

    //get data from countries list in activity
    public void setWeather(CountryInfo countryInfo) {
        mCountryInfo = countryInfo;
        if (mCountryInfo.getLatlng().size() != 0) {
            Call<Info> call2 = WeatherApiClient.getClient().create(RetrofitInterface.class).getWeatherInfo(mCountryInfo.getLatlng().get(0), mCountryInfo.getLatlng().get(1), API_KEY);
            call2.enqueue(new Callback<Info>() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<Info> call, @NonNull Response<Info> response) {

                    weatherInfo = response.body();
                    mSelectedFragment.SelectedFragment(weatherInfo);
                    setData();
                }

                @Override
                public void onFailure(@NonNull Call<Info> call, @NonNull Throwable t) {
                    t.getStackTrace();
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putParcelable("country", mCountryInfo);
        getChildFragmentManager().putFragment(outState, "todayFragment", todayFragment);
        getChildFragmentManager().putFragment(outState, "tomorrowFragment", tomorrowFragment);
    }

    private void setData() {
        tabs.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }


    public interface SelectedFragmentListener {
        void SelectedFragment(Info weatherInfo);

    }

}
