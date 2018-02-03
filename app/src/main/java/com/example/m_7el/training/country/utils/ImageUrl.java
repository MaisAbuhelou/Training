package com.example.m_7el.training.country.utils;


import java.net.MalformedURLException;
import java.net.URL;

public class ImageUrl {

    private static  String IMAGE_URL= "http://www.geognos.com/api/en/countries/flag/PS.png";
    public static URL getURL(String flag){

        URL url = null;
        try {
            url = new URL(IMAGE_URL.replace("PS", flag));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

}

