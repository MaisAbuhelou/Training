
package com.example.m_7el.training.country.models;


import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryInfo extends BaseObservable implements Parcelable   {
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

    protected CountryInfo(Parcel in) {
        name = in.readString();
        capital = in.readString();
        region = in.readString();
        population = in.readDouble();
        altSpellings = in.createStringArrayList();
    }

    public static final Creator<CountryInfo> CREATOR = new Creator<CountryInfo>() {
        @Override
        public CountryInfo createFromParcel(Parcel in) {
            return new CountryInfo(in);
        }

        @Override
        public CountryInfo[] newArray(int size) {
            return new CountryInfo[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(capital);
        dest.writeString(region);
        dest.writeDouble(population);
        dest.writeStringList(altSpellings);
    }
}