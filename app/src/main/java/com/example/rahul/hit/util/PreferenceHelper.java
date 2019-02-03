package com.example.rahul.hit.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    public SharedPreferences HIT_Preferences;
    //public SharedPreferences emailPreferences;

    private static PreferenceHelper instance;
    public static  final String IS_LOGIN = "is_Login";
    public String EMAIL="email";


    private PreferenceHelper(Context context){
        HIT_Preferences=context.getSharedPreferences("HIT_PREFERENCE",Context.MODE_PRIVATE);
        //emailPreferences=context.getSharedPreferences("EMAIL",Context.MODE_PRIVATE);
    }

    public static PreferenceHelper getInstance(Context context)
    {
        if(instance==null){
            instance=new PreferenceHelper(context);
        }
        return instance;
    }

    public void putString(String key,String defaultValue){
        SharedPreferences.Editor editor1=HIT_Preferences.edit();
        editor1.putString(key,defaultValue);
        editor1.apply();
    }

    public String getString(String key,String defaultValue){
        return HIT_Preferences.getString(key,"");
    }

    public void putBoolean(String key,boolean defaultvalue){
        SharedPreferences.Editor editor = HIT_Preferences.edit();
        editor.putBoolean(key, defaultvalue);
        editor.apply();
    }
    public boolean getBoolean(String key,boolean defaultvalue){
        return HIT_Preferences.getBoolean(key,defaultvalue);
    }
}