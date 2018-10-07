package com.example.rahul.hit.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private SharedPreferences HIT_Preferences;

    private static PreferenceHelper instance;
    public static  final String IS_LOGIN = "is_Login";

    private PreferenceHelper(Context context){
        HIT_Preferences=context.getSharedPreferences("HIT_PREFERENCE",Context.MODE_PRIVATE);

    }

    public static PreferenceHelper getInstance(Context context)
    {
        if(instance==null){
            instance=new PreferenceHelper(context);
        }
        return instance;
    }
    public void putBoolean(String key,boolean defaultvalue){

        SharedPreferences.Editor editor = HIT_Preferences.edit();
        editor.putBoolean(key, defaultvalue);
        editor.apply();
    }
    private boolean getBoolean(String key,boolean defaultvalue){
        return HIT_Preferences.getBoolean(key, defaultvalue);
    }
}