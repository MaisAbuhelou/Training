package com.example.m_7el.training.country;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.m_7el.training.R;
import com.example.m_7el.training.country.models.CountryInfo;
import com.example.m_7el.training.country.utils.LogMessages;
import com.example.m_7el.training.country.utils.PhotoManager;

import java.net.URISyntaxException;


public class CountryInfoFragment extends Fragment {

    private TextView mCountyName;
    private TextView mCountyRegion;
    private TextView mCountyPopulation;
    private TextView mCountyCapital;
    private ImageView mCountryImage;
    private CountryInfo mCountryInfo;
    private String flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogMessages.getMessage("CountryInfoFragment" );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_counrty_info, container, false);

        mCountyName = view.findViewById(R.id.country_name);
        mCountyRegion = view.findViewById(R.id.country_region);
        mCountyPopulation = view.findViewById(R.id.population);
        mCountyCapital = view.findViewById(R.id.country_capital);
        mCountryImage = view.findViewById(R.id.country_image);
        if (savedInstanceState == null) {
            Log.i("state null", "null" );
        }
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCountryInfo = savedInstanceState.getParcelable("country" );
            setData(mCountryInfo);
        }

        return view;
    }

    public void setCountry(CountryInfo country) {
        mCountryInfo = country;
        setData(mCountryInfo);
    }

    @SuppressLint("SetTextI18n" )
    private void setData(CountryInfo countryInfo) {
        if (countryInfo != null) {
            mCountyName.setText(countryInfo.getName());
            mCountyRegion.setText(countryInfo.getRegion());
            mCountyPopulation.setText(countryInfo.getPopulation() + "" );
            mCountyCapital.setText(countryInfo.getCapital());
            flag = countryInfo.getAltSpellings().get(0);
            PhotoManager.loadImage(getContext(), mCountryImage,flag);

        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's state here
        outState.putParcelable("country", mCountryInfo);

    }

}
