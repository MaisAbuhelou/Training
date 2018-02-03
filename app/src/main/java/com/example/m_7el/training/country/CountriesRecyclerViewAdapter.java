package com.example.m_7el.training.country;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;

import java.util.List;


public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List<CountryInfo> mCountries;
    private CountrySelectListener mListener;

    public interface CountrySelectListener {
        void onCountrySelected(CountryInfo countryInfo);
    }

    public void setCountry(List<CountryInfo> country) {
        this.mCountries = country;
    }

    public CountriesRecyclerViewAdapter(CountrySelectListener countrySelectListener) {
        mListener = countrySelectListener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new CountryViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {
        holder.bind(mCountries.get(position));
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }


}