package com.example.halong.myapplication.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LoadImageUtil {
    public static void load(Context context, String uri, ImageView imageView){
        Glide.with(context).load(uri).into(imageView);
    }
}
