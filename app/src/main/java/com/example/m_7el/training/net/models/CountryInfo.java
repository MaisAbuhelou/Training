
package com.example.m_7el.training.net.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryInfo implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("topLevelDomain")
    @Expose
    private List<String> topLevelDomain = null;
    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;
    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;
    @SerializedName("callingCodes")
    @Expose
    private List<String> callingCodes = null;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("altSpellings")
    @Expose
    private List<String> altSpellings = null;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;
    @SerializedName("population")
    @Expose
    private double population;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = null;
    @SerializedName("demonym")
    @Expose
    private String demonym;
    @SerializedName("area")
    @Expose
    private double area;
    @SerializedName("gini")
    @Expose
    private Double gini;
    @SerializedName("timezones")
    @Expose
    private List<String> timezones = null;
    @SerializedName("borders")
    @Expose
    private List<String> borders = null;
    @SerializedName("nativeName")
    @Expose
    private String nativeName;
    @SerializedName("numericCode")
    @Expose
    private String numericCode;
    @SerializedName("currencies")
    @Expose
    private List<String> currencies = null;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("translations")
    @Expose
    private Translations translations;
    @SerializedName("relevance")
    @Expose
    private String relevance;

    protected CountryInfo(Parcel in) {
        name = in.readString();
        topLevelDomain = in.createStringArrayList();
        alpha2Code = in.readString();
        alpha3Code = in.readString();
        callingCodes = in.createStringArrayList();
        capital = in.readString();
        altSpellings = in.createStringArrayList();
        region = in.readString();
        subregion = in.readString();
        population = in.readDouble();
        demonym = in.readString();
        area = in.readDouble();
        if (in.readByte() == 0) {
            gini = null;
        } else {
            gini = in.readDouble();
        }
        timezones = in.createStringArrayList();
        borders = in.createStringArrayList();
        nativeName = in.readString();
        numericCode = in.readString();
        currencies = in.createStringArrayList();
        languages = in.createStringArrayList();
        relevance = in.readString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Double getGini() {
        return gini;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public static Creator<CountryInfo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(topLevelDomain);
        dest.writeString(alpha2Code);
        dest.writeString(alpha3Code);
        dest.writeStringList(callingCodes);
        dest.writeString(capital);
        dest.writeStringList(altSpellings);
        dest.writeString(region);
        dest.writeString(subregion);
        dest.writeDouble(population);
        dest.writeString(demonym);
        dest.writeDouble(area);
        if (gini == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(gini);
        }
        dest.writeStringList(timezones);
        dest.writeStringList(borders);
        dest.writeString(nativeName);
        dest.writeString(numericCode);
        dest.writeStringList(currencies);
        dest.writeStringList(languages);
        dest.writeString(relevance);
    }


}