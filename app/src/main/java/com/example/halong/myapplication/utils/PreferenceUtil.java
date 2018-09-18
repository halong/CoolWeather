package com.example.halong.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtil {
    private static SharedPreferences.Editor mEditor;
    public static SharedPreferences.Editor getEditor(Context context){
        if (mEditor == null) {
            mEditor= PreferenceManager.getDefaultSharedPreferences(context).edit();
        }
        return mEditor;
    }
}
