package com.example.rahul.hit.dashboard.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.util.TechnicianModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTechnician extends AppCompatActivity {

    @BindView(R.id.editText_AddTechnicianPage_TechEmail)
    EditText TechEmail;

    @BindView(R.id.editText_AddTechnicianPage_TechName)
    EditText TechName;

    @BindView(R.id.editText_AddTechnicianPage_TechPhoneNo)
    EditText TechPhoneNo;

    @BindView(R.id.editText_AddTechnicianPage_TechJobProfile)
    EditText TechJobProfile;

    @BindView(R.id.buttton_AddTechnicianPage_AddTech)
    Button addTewchnician;

    CoordinatorLayout coordinatorLayout;


    DatabaseReference mTechnicianDbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_technician);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttton_AddTechnicianPage_AddTech)
    public void OnButtonClick(View view){
        Log.d("check","inside buttonclick");
        addTechnician();
        finish();
    }

    private void addTechnician() {

        Log.d("check","Inside addtehcnicaan");
        final String name=TechName.getText().toString();
        final String email=TechEmail.getText().toString();
        final String phoneNumber=TechPhoneNo.getText().toString();
        final String jobProfile=TechJobProfile.getText().toString();

        if(TextUtils.isEmpty(name)){
            Snackbar snackbar=Snackbar.make(coordinatorLayout,"9",Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        if(TextUtils.isEmpty(email)){
            Snackbar snackbar=Snackbar.make(coordinatorLayout,"hello",Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        if(TextUtils.isEmpty(phoneNumber)){
            Snackbar snackbar=Snackbar.make(coordinatorLayout,"hello",Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        if(TextUtils.isEmpty(jobProfile)){
            Snackbar snackbar=Snackbar.make(coordinatorLayout,"hello",Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        mTechnicianDbReference= FirebaseDatabase.getInstance().getReference();
        final DatabaseReference mDatabase= mTechnicianDbReference.child("Technician").child(email.substring(0,email.indexOf("@")));
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Snackbar snackbar=Snackbar.make(coordinatorLayout,"Technician already exists...!!",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else{
                    final TechnicianModel technicianModel=new TechnicianModel(name,email,phoneNumber,jobProfile);
                    mTechnicianDbReference.child("Technician").child(email.substring(0,email.indexOf("@"))).setValue(technicianModel);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
