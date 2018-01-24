package com.example.m_7el.training.country.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.m_7el.training.R;

/**
 * Created by m_7el on 1/22/2018.
 */

public class PhotoManager {
     public static  void  loadImage(Context context, final ImageView imageView) {
         RequestOptions requestOptions = new RequestOptions();

         Glide.with(context)
                 .setDefaultRequestOptions(requestOptions)
                 .load(ImageUrl.getURL()).into(imageView);
     }
}
