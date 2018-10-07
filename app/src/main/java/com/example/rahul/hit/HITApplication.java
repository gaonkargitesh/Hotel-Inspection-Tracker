package com.example.rahul.hit;

import android.app.Application;
import android.util.Log;

public class HITApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("HITApplication","Create successful");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("HITApplication","Terminate successful");
    }
}
