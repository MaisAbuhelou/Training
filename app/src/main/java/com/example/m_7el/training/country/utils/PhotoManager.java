package com.example.m_7el.training.country.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class PhotoManager {

    public void loadImage(Context mContext , ImageView imageView, String flag) {

        RequestOptions requestOptions = new RequestOptions();
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(ImageUrl.getURL(flag)).into(imageView);

    }
}
