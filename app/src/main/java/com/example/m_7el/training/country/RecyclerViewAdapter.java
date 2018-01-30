package com.example.m_7el.training.country;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private  Context mContext;
    private final List<CountryInfo> mCountries;
    private CountrySelectOnListener mListener;
    private CountryInfo country;

    public interface CountrySelectOnListener {
        void onCountrySelected(CountryInfo countryInfo);
    }

    public RecyclerViewAdapter(Context context, List<CountryInfo> countries,CountrySelectOnListener countrySelecetListener) {
        mContext = context;
        mCountries = countries;
        mListener = countrySelecetListener;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCountrySelected(country);
            }
        });
        return new CountryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, final int position) {

            country = mCountries.get(position);
            holder.bind(country,mContext);
    }

    @Override
    public int getItemCount() {

        return mCountries.size();
    }



}