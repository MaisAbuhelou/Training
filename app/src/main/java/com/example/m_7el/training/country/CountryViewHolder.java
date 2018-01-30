package com.example.m_7el.training.country;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.databinding.RecyclerBinding;

import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class CountryViewHolder extends RecyclerView.ViewHolder {

    private ImageView mCountryImage;
    private RecyclerBinding binding;

    public CountryViewHolder(View view) {
        super(view);
        binding = DataBindingUtil.bind(view);

    }

    public void bind(CountryInfo country, Context mContext) {
        binding.setCountry(country);



    }
}