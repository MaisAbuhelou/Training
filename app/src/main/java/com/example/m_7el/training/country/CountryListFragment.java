package com.example.m_7el.training.country;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.RecyclerViewAdapter.CountrySelectOnListener;
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
    private CountrySelectOnListener countrySelectionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryListFragment");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_country_list, container, false);
        countriesRecyclerView = view.findViewById(R.id.country_list_recycler_view);
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
                setRecyclerView();
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryInfo>> call, @NonNull Throwable t) {

                t.printStackTrace();
            }
        });
    }

    public void setCountrySelectionListener(CountrySelectOnListener countrySelectionListener) {

        this.countrySelectionListener = countrySelectionListener;
    }

    private void setRecyclerView() {
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        countriesRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        countriesRecyclerView.setAdapter(new RecyclerViewAdapter(getActivity(), mCountryInfo, countrySelectionListener));
    }


}
