package com.example.m_7el.training.country.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.net.URI;
import java.net.URISyntaxException;


public class PhotoManager {

     public static  void  loadImage(Context context, final ImageView imageView,String countryFlag)  {
         RequestOptions requestOptions = new RequestOptions();
//          String url ="http://www.geognos.com/api/en/countries/flag/PS.png" ;
//         URI uri = new URI(url.replace("PS", countryFlag));

         Glide.with(context)
                 .setDefaultRequestOptions(requestOptions)
                 .load(ImageUrl.getURL()).into(imageView);
     }
}
