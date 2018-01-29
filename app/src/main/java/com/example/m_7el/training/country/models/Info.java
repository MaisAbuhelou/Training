package com.example.m_7el.training.country.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Info {


    @SerializedName("list")
    @Expose
    private List<WeatherDetails> weatherDetails = null;

    public java.util.List<WeatherDetails> getWeatherDetails() {
        return weatherDetails;
    }


}
