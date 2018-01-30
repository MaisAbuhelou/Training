package com.example.m_7el.training.country.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDetails {


    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public Main getMain() {
        return main;
    }

    public String getDtTxt() {
        return dtTxt;
    }


}
