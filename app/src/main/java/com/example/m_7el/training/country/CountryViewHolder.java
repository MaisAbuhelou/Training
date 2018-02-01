package com.example.m_7el.training.country;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.databinding.RecyclerBinding;


public class CountryViewHolder extends RecyclerView.ViewHolder {

    private RecyclerBinding binding;
    private ImageView mCountryImage;
    private CountryInfo mCountry;
    private Context mContext;


    public CountryViewHolder(View view, final CountriesRecyclerViewAdapter.CountrySelectListener countrySelectListener) {
        super(view);
        mContext= view.getContext();
        mCountryImage = view.findViewById(R.id.country_image);
        binding = DataBindingUtil.bind(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrySelectListener.onCountrySelected(mCountry);

            }
        });
    }

    public void bind(CountryInfo country) {
        mCountry = country;
        binding.setCountry(country);
        PhotoManager.loadImage(mContext, mCountryImage);


    }
}