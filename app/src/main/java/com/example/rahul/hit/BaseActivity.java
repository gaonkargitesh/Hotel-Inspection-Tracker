package com.example.rahul.hit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.rahul.hit.util.PreferenceHelper;

public abstract class BaseActivity extends AppCompatActivity {


    protected static PreferenceHelper baseActivityPreferenceHelper;

    public SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        baseActivityPreferenceHelper = PreferenceHelper.getInstance(this);

    }

    protected abstract void init();
}