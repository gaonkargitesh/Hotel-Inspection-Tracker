package com.example.rahul.hit.technician.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TechnicianBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Broadcast is sent", Toast.LENGTH_SHORT).show();
    }
}
