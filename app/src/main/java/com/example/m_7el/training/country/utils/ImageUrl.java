package com.example.m_7el.training.country.utils;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageUrl {

    private static  String URLL= "http://www.geognos.com/api/en/countries/flag/PS.png";
    public static URL getURL(String flag){

        URL uri = null;
        try {
            uri = new URL(URLL.replace("PS", flag));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return uri;
    }

}

