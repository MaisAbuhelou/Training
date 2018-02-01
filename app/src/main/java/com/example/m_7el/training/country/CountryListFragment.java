package com.example.m_7el.training.country;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.CountriesRecyclerViewAdapter.CountrySelectListener;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.country.utils.SimpleDividerItemDecoration;
import com.example.m_7el.training.net.clients.CountryApiClient;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListFragment extends Fragment {


    private List<CountryInfo> mCountryInfo;
    private RecyclerView countriesRecyclerView;
    private CountrySelectListener countrySelectionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryListFragment" );

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        countriesRecyclerView = view.findViewById(R.id.country_list_recycler_view);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        countriesRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        getCountriesInfo();

        return view;
    }

    private void getCountriesInfo() {
        // get All countries
        Call<List<CountryInfo>> call2 = CountryApiClient.getClient().create(RetrofitInterface.class).getCountyInfo();
        call2.enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryInfo>> call, @NonNull Response<List<CountryInfo>> response) {
                mCountryInfo = response.body();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRecyclerView();
                    }
                }, 1000);
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryInfo>> call, @NonNull Throwable t) {

                t.printStackTrace();
            }
        });
    }

    public void setCountrySelectionListener(CountrySelectListener countrySelectionListener) {
        this.countrySelectionListener = countrySelectionListener;
    }

    private void setRecyclerView() {
        CountriesRecyclerViewAdapter countriesRecyclerViewAdapter = new CountriesRecyclerViewAdapter(countrySelectionListener);
        countriesRecyclerViewAdapter.setCountry(mCountryInfo);
        countriesRecyclerView.setAdapter(countriesRecyclerViewAdapter);
        countriesRecyclerViewAdapter.notifyDataSetChanged();
    }
}