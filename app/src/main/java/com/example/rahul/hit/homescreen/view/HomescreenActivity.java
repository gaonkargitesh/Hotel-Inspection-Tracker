package com.example.rahul.hit.homescreen.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.login.view.LoginActivity;
import com.example.rahul.hit.util.PreferenceHelper;

import static com.example.rahul.hit.util.PreferenceHelper.*;

public class HomescreenActivity extends BaseActivity {

    SharedPreferences sharedPreferences;

    PreferenceHelper homepreferencehelper;

    Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        sharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
        logoutButton=findViewById(R.id.button_HomeScreenPage_Logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivityPreferenceHelper.putBoolean(IS_LOGIN,false);
                Intent intent=new Intent(HomescreenActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void init() {

    }
}

