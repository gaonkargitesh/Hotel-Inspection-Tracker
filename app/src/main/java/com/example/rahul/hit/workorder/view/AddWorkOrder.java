package com.example.rahul.hit.workorder.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rahul.hit.R;

public class AddWorkOrder extends AppCompatActivity {

    Toolbar addWorkOrderToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_order);

        addWorkOrderToolbar=findViewById(R.id.work_order_toolbar);
        setSupportActionBar(addWorkOrderToolbar);
        getSupportActionBar().setTitle("Add Work Order");

        addWorkOrderToolbar.setNavigationIcon(R.drawable.back_arrow);
        //addWorkOrderToolbar.setTitle("Work Order Details");
        addWorkOrderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
