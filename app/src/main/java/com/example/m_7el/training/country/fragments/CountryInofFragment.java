package com.example.m_7el.training.country.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.net.models.CountryInfo;


public class CountryInofFragment extends Fragment {

    private TextView mCountyName;
    private TextView mCountyRegion;
    private TextView mCountyPopulation;
    private TextView mCountyCapital;
    private ImageView mCountryImage;
    private CountryInfo mCountryInfo;

    public CountryInofFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Log.w("Fragment", "CountryInfoFragment created  " + this.toString());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     Log.w("Fragment", "CountryInfoFragment created  " + this.toString());

        View view = inflater.inflate(R.layout.fragment_counrty_info, container, false);

        mCountyName = (TextView) view.findViewById(R.id.country_name);
        mCountyRegion = (TextView) view.findViewById(R.id.country_region);
        mCountyPopulation = (TextView) view.findViewById(R.id.population);
        mCountyCapital = (TextView) view.findViewById(R.id.country_capital);
        mCountryImage = view.findViewById(R.id.country_image);

        setData(mCountryInfo);
        return view;
    }

    public void setCountry(CountryInfo country) {
        mCountryInfo = country;
        setData(mCountryInfo);
    }

    private void setData(CountryInfo countryInfo) {
        if (countryInfo != null) {
            mCountyName.setText(countryInfo.getName());
            mCountyRegion.setText(countryInfo.getRegion());
            mCountyPopulation.setText(countryInfo.getPopulation() + "");
            mCountyCapital.setText(countryInfo.getCapital());
            PhotoManager.loadImage(getContext(), mCountryImage);


        }
    }

}
