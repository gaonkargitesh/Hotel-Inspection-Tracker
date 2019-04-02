package com.example.rahul.hit.workorder.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.button_WorkOrderDetails_Fix)
    Button fixButton;


    public String id;
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


        //This will change the status bar color.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#303F9F"));
        }

        String titleData=getIntent().getExtras().getString("titledata");
        String descriptionData=getIntent().getExtras().getString("descriptiondata");
        String priorityData=getIntent().getExtras().getString("prioritydata");
        String assignedToData=getIntent().getExtras().getString("assignedToData");

        id=getIntent().getExtras().getString("ID");

        title.setText(titleData);
        description.setText(descriptionData);
        priority.setText(priorityData);
        assignedTo.setText(assignedToData);
        Glide.with(this.getApplicationContext()).load(getIntent().getExtras().getString("Imagedata")).into(imageView);

        Log.d("checkid",""+id);


    }

    @OnClick(R.id.button_WorkOrderDetails_Fix)
    public void onClick(View view){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Do you want to fix and close the complaint?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseReference=FirebaseDatabase.getInstance().getReference().child("Create Complaint");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                            String compid=dataSnapshot1.getKey();
                            Log.d("compid",""+compid);
                            String idFromDatasnapshot = compid.substring(0,compid.indexOf("_"));
                            Log.d("Xyz","ID's from Datasnapshot "+idFromDatasnapshot);
                            Log.d("Abcd","Condition: "+idFromDatasnapshot.equals(id));
                            if(idFromDatasnapshot.equals(id)){

                                databaseReference.child(compid).child("status").setValue("Completed");

                                //Deletes the Work Order from the database
                                /*databaseReference.child(compid).removeValue();
                                Toast.makeText(WorkOrderDetails.this, "removed", Toast.LENGTH_SHORT).show();*/
                            }
                    /*else{
                        Toast.makeText(WorkOrderDetails.this, "Different", Toast.LENGTH_SHORT).show();
                    }*/
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();


    }

}

//Code for Removing complaint

/*databaseReference=FirebaseDatabase.getInstance().getReference().child("Create Complaint");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    String compid=dataSnapshot1.getKey();
                    Log.d("compid",""+compid);
                    String idFromDatasnapshot = compid.substring(0,compid.indexOf("_"));
                    Log.d("Xyz","ID's from Datasnapshot "+idFromDatasnapshot);
                    Log.d("Abcd","Condition: "+idFromDatasnapshot.equals(id));
                    if(idFromDatasnapshot.equals(id)){
                        databaseReference.child(compid).removeValue();
                        Toast.makeText(WorkOrderDetails.this, "removed", Toast.LENGTH_SHORT).show();
                    }
                    *//*else{
                        Toast.makeText(WorkOrderDetails.this, "Different", Toast.LENGTH_SHORT).show();
                    }*//*
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        finish();*/
