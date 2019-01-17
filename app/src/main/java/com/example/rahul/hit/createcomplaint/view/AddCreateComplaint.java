package com.example.rahul.hit.createcomplaint.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rahul.hit.R;

public class AddCreateComplaint extends AppCompatActivity {


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_create_complaint);

        toolbar=findViewById(R.id.create_complaint_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Add Create Complaint");


    }
}
