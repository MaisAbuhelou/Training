package com.example.m_7el.training.country.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {


    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;
    @SerializedName("pressure")
    @Expose
    private double pressure;

    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }


}
