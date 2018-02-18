package com.example.m_7el.training.country;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.m_7el.training.country.di.MyApp;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.country.utils.PhotoManagerImp;
import com.example.m_7el.training.databinding.RecyclerBinding;

import javax.inject.Inject;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    private RecyclerBinding binding;
    private ImageView mCountryImage;
    private Context mContext;
    private String flag = "PS";
    @Inject
    PhotoManager photoManager;


    CountryViewHolder(View view, final CountriesRecyclerViewAdapter.CountrySelectListener countrySelectListener) {
        super(view);
        mContext = view.getContext();
        binding = DataBindingUtil.bind(view);
        mCountryImage = binding.countryImagee;
        ((MyApp) mContext.getApplicationContext()).getMyComponent().inject(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countrySelectListener.onCountrySelected(getPosition());
            }
        });
    }

    void bind(CountryInfo country) {
        if (country.getAltSpellings().size() != 0) {
            flag = country.getAltSpellings().get(0);
        }
        binding.setCountry(country);
        photoManager.loadImage(mContext, mCountryImage, flag);
    }
}