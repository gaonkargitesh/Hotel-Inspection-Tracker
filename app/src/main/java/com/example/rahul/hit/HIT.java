package com.example.rahul.hit;

import android.app.Application;
import android.util.Log;

public class HIT extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("HIT","Create successful");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("HIT","Terminate successful");
    }
}
