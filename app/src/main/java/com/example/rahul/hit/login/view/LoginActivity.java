package com.example.rahul.hit.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.rahul.hit.R;
import com.example.rahul.hit.signup.view.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    TextView signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupTextView=findViewById(R.id.loginPage_SignUp_TextView);
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent1);
            }
        });
    }

}
