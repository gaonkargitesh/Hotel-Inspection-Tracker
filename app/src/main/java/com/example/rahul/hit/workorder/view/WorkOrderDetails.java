package com.example.rahul.hit.workorder.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkOrderDetails extends AppCompatActivity {

    @BindView(R.id.editText_WorkOrderDetails_Title)
    EditText title;

    @BindView(R.id.editText_WorkOrderDetails_Description)
    EditText description;

    @BindView(R.id.editText_WorkOrderDetails_Priority)
    EditText priority;

    @BindView(R.id.editText_WorkOrderDetails_AssignedTo)
    EditText assignedTo;

    @BindView(R.id.imageView_WorkOrderDetails_Image)
    ImageView imageView;


    Toolbar toolbar;
    Context context;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_order_details);

        ButterKnife.bind(this);

        toolbar=findViewById(R.id.workOrder_Details_Toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String titleData=getIntent().getExtras().getString("titledata");
        String descriptionData=getIntent().getExtras().getString("descriptiondata");
        String priorityData=getIntent().getExtras().getString("prioritydata");
        String assignedToData=getIntent().getExtras().getString("assignedToData");

        title.setText(titleData);
        description.setText(descriptionData);
        priority.setText(priorityData);
        assignedTo.setText(assignedToData);
        Glide.with(this.getApplicationContext()).load("Imagedata").into(imageView);


    }
}
