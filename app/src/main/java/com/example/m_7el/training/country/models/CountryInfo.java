
package com.example.m_7el.training.country.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("population")
    @Expose
    private double population;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = null;

    @SerializedName("altSpellings")
    @Expose
    private List<String> altSpellings = null;

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public double getPopulation() {
        return population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }


}