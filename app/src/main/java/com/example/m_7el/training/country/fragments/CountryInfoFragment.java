package com.example.m_7el.training.country.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.country.utils.PhotoManager;


public class CountryInfoFragment extends Fragment {

    private TextView mCountyName;
    private TextView mCountyRegion;
    private TextView mCountyPopulation;
    private TextView mCountyCapital;
    private ImageView mCountryImage;
    private CountryInfo mCountryInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryInfoFragment");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_counrty_info, container, false);

        mCountyName =view.findViewById(R.id.country_name);
        mCountyRegion = view.findViewById(R.id.country_region);
        mCountyPopulation = view.findViewById(R.id.population);
        mCountyCapital = view.findViewById(R.id.country_capital);
        mCountryImage = view.findViewById(R.id.country_image);

        setData(mCountryInfo);
        return view;
    }

    public void setCountry(CountryInfo country) {
        mCountryInfo = country;
        setData(mCountryInfo);
    }

    @SuppressLint("SetTextI18n")
    private void setData(CountryInfo countryInfo) {
        if (countryInfo != null) {
            mCountyName.setText(countryInfo.getName());
            mCountyRegion.setText(countryInfo.getRegion());
            mCountyPopulation.setText(countryInfo.getPopulation()+"");
            mCountyCapital.setText(countryInfo.getCapital());
            PhotoManager.loadImage(getContext(), mCountryImage);

        }
    }

}
