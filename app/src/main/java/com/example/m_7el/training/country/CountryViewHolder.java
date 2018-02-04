package com.example.m_7el.training.country;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.databinding.RecyclerBinding;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private RecyclerBinding binding;
    private ImageView mCountryImage;
    private CountryInfo mCountry;
    private Context mContext;
    private String flag = "PS";


    public CountryViewHolder(View view, final CountriesRecyclerViewAdapter.CountrySelectListener countrySelectListener) {
        super(view);
        mContext = view.getContext();
        binding = DataBindingUtil.bind(view);
        mCountryImage = binding.countryImagee;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrySelectListener.onCountrySelected(mCountry);
            }
        });
    }

    public void bind(CountryInfo country) {
        mCountry = country;
        if (mCountry.getAltSpellings().size() != 0) {
            flag = mCountry.getAltSpellings().get(0);
        }
        binding.setCountry(country);
        PhotoManager.loadImage(mContext, mCountryImage, flag);
    }
}