package com.example.m_7el.training.country.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by m_7el on 1/22/2018.
 */

public class PhotoManager {
     public static  void  loadImage(Context context, ImageView imageView){
         Glide.with(context).
                 load(ImageUrl.getURL())
                 .into(imageView);

     }
}
