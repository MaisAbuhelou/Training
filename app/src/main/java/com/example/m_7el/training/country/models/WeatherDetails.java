package com.example.m_7el.training.country.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class  WeatherDetails implements Parcelable {


    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;


    protected WeatherDetails(Parcel in) {
        dtTxt = in.readString();
    }

    public static final Creator<WeatherDetails> CREATOR = new Creator<WeatherDetails>() {
        @Override
        public WeatherDetails createFromParcel(Parcel in) {
            return new WeatherDetails(in);
        }

        @Override
        public WeatherDetails[] newArray(int size) {
            return new WeatherDetails[size];
        }
    };

    public Main getMain() {
        return main;
    }

    public String getDtTxt() {
        return dtTxt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dtTxt);
    }
}
