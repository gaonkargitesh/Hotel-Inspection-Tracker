package com.example.rahul.hit.launcher.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.homescreen.view.HomescreenActivity;
import com.example.rahul.hit.login.view.LoginActivity;
import com.example.rahul.hit.util.PreferenceHelper;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.rahul.hit.util.PreferenceHelper.*;

public class LauncherActivity extends BaseActivity {


    Intent launcherToHomeIntent;
    public static boolean isLogin;
    public static boolean value;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        launcherToHomeIntent=new Intent(this,HomescreenActivity.class);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(baseActivityPreferenceHelper.getBoolean(IS_LOGIN,false)){
                    startActivity(launcherToHomeIntent);
                }
                else {
                    Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },800);
    }


    @Override
    protected void init() {

    }
}
