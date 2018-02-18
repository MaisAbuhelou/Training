package com.example.m_7el.training.country;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
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

import java.util.List;

public class CountryListFragment extends Fragment {
    private RecyclerView countriesRecyclerView;
    private CountrySelectListener mCountrySelectionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryListFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentCountryListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_country_list, container, false);
        View view = binding.getRoot();
        countriesRecyclerView = binding.countryListRecyclerView;
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        countriesRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        return view;
    }

    public void setCountrySelectionListener(CountrySelectListener mCountrySelectionListener) {
        this.mCountrySelectionListener = mCountrySelectionListener;
    }

    void setRecyclerView(List<CountryInfo> countryInfo) {
        CountriesRecyclerViewAdapter countriesRecyclerViewAdapter = new CountriesRecyclerViewAdapter(mCountrySelectionListener);
        countriesRecyclerViewAdapter.setCountry(countryInfo);
        countriesRecyclerView.setAdapter(countriesRecyclerViewAdapter);
        countriesRecyclerViewAdapter.notifyDataSetChanged();
    }
}
