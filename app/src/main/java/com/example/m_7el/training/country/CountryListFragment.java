package com.example.m_7el.training.country;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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
import com.example.m_7el.training.databinding.FragmentCountryListBinding;
import com.example.m_7el.training.net.clients.CountryApiClient;
import com.example.m_7el.training.net.clients.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListFragment extends Fragment implements View.OnClickListener{
    private List<CountryInfo> mCountryInfo;
    private RecyclerView countriesRecyclerView;
    private CountrySelectListener mCountrySelectionListener;
    private FragmentCountryListBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryListFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_country_list, container, false);
        View view = binding.getRoot();
        binding.setListener(this);
        countriesRecyclerView = binding.countryListRecyclerView;
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        countriesRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mCountryInfo = savedInstanceState.getParcelableArrayList("country");
            if (mCountryInfo!=null){
            showLoadingView(false);
            setRecyclerView();
            return;
        }
        }
        getCountriesInfo();


    }

    public void showLoadingView(boolean show) {
        binding.setLoading(show);
    }

    private void getCountriesInfo() {
        // get All countries
        binding.setError(false);
        showLoadingView(true);
        Call<List<CountryInfo>> call2 = CountryApiClient.getClient().create(RetrofitInterface.class).getCountyInfo();
        call2.enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryInfo>> call, @NonNull Response<List<CountryInfo>> response) {
                mCountryInfo = response.body();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRecyclerView();
                        showLoadingView(false);
                    }
                }, 2000);
            }

            @Override
            public void onFailure(@NonNull Call<List<CountryInfo>> call, @NonNull Throwable t) {
                showLoadingView(false);
                binding.setError(true);
                t.printStackTrace();
            }
        });
    }

    public void setCountrySelectionListener(CountrySelectListener mCountrySelectionListener) {
        this.mCountrySelectionListener = mCountrySelectionListener;
    }

    private void setRecyclerView() {
        CountriesRecyclerViewAdapter countriesRecyclerViewAdapter = new CountriesRecyclerViewAdapter(mCountrySelectionListener);
        countriesRecyclerViewAdapter.setCountry(mCountryInfo);
        countriesRecyclerView.setAdapter(countriesRecyclerViewAdapter);
        countriesRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putParcelableArrayList("country", (ArrayList<? extends Parcelable>) mCountryInfo);

    }


    @Override
    public void onClick(View v) {
        getCountriesInfo();
    }

}
