package com.example.rahul.hit.dashboard.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTechnician extends AppCompatActivity {


    @BindView(R.id.textLayout_AddTechnicianPage_TechEmail)
    TextInputLayout emailTextInputLayout;

    @BindView(R.id.textLayout_AddTechnicianPage_TechName)
    TextInputLayout nameTextInputLayout;

    @BindView(R.id.textLayout_AddTechnicianPage_TechPhoneNo)
    TextInputLayout phoneNoTextInputLayout;

    @BindView(R.id.textLayout_AddTechnicianPage_TechPasswrod)
    TextInputLayout passwordTextInputLayout;

    @BindView(R.id.textLayout_AddTechnicianPage_TechJobProfile)
    TextInputLayout jobProfileTextInputLayout;

    @BindView(R.id.editText_AddTechnicianPage_TechEmail)
    EditText TechEmail;

    @BindView(R.id.editText_AddTechnicianPage_TechName)
    EditText TechName;

    @BindView(R.id.editText_AddTechnicianPage_TechPhoneNo)
    EditText TechPhoneNo;

    @BindView(R.id.editText_AddTechnicianPage_TechJobProfile)
    EditText TechJobProfile;

    @BindView(R.id.edittext_AddTechnicianPage_TechPassword)
    EditText TechPassword;

    @BindView(R.id.buttton_AddTechnicianPage_AddTech)
    Button addTechnician;



    CoordinatorLayout coordinatorLayout;

    private RecyclerView technicianRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    //FirebaseRecyclerAdapter firebaseRecyclerAdapter;


    Toolbar toolbar;
    DatabaseReference mTechnicianDbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_technician);


        toolbar=findViewById(R.id.add_Technician_Toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTechnicianDbReference=FirebaseDatabase.getInstance().getReference();
        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttton_AddTechnicianPage_AddTech)
    public void OnButtonClick(View view){
        Log.d("check","inside buttonclick");
        addTechnician();
    }

    private void addTechnician() {

        Log.d("check","Inside addtehcnicaan");
        final String name=TechName.getText().toString();
        final String email=TechEmail.getText().toString();
        final String phoneNumber=TechPhoneNo.getText().toString();
        final String jobProfile=TechJobProfile.getText().toString();
        final String password=TechPassword.getText().toString();
        final String role="Technician";

        if(TextUtils.isEmpty(name)){
            TechName.setError("Name is empty");
            return;
        }
        else if(TextUtils.isEmpty(email)){
            TechEmail.setError("Email is empty");
            return;
        }
        else if(TextUtils.isEmpty(phoneNumber)){
            TechPhoneNo.setError("Phone number is empty");
            return;
        }
        else if(TextUtils.isEmpty(jobProfile)){
            TechJobProfile.setError("Job Profile is empty");
            return;
        }
        else if(TextUtils.isEmpty((password))){
            TechPassword.setError("Password is empty");
            return;
        }
        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            TechEmail.setError("Invalid Email Address");
        }
        else if(name.equals("") && email.equals("") && phoneNumber.equals("") && jobProfile.equals("") && password.equals(""))
        {
            //addTechnician.setVisibility(View.INVISIBLE);
        }
        else if(!(name.equals("") && email.equals("") && phoneNumber.equals("") && jobProfile.equals("") &&password.equals("")))
        {
            addTechnician.setVisibility(View.VISIBLE);
            finish();
        }
        else{
            Toast.makeText(this, "Technician Added.", Toast.LENGTH_SHORT).show();

        }

        /*mTechnicianDbReference= FirebaseDatabase.getInstance().getReference().child("Technician").push();
        ArrayList<TechnicianModel> techList= new ArrayList<>();
        TechnicianModel technicianModel=new TechnicianModel();
        //technicianModel.put();*/
        DatabaseReference mDatabase= mTechnicianDbReference.child("Users").child(email.substring(0,email.indexOf("@")));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    /*Snackbar snackbar=Snackbar.make(coordinatorLayout,"Technician already exists...!!",Snackbar.LENGTH_SHORT);
                    snackbar.show();*/
                    Toast.makeText(AddTechnician.this, "exists", Toast.LENGTH_SHORT).show();
                }
                else{
                    final TechnicianModel technicianModel=new TechnicianModel(name,email,phoneNumber,jobProfile,password,role);
                    mTechnicianDbReference.child("Users").child(email.substring(0,email.indexOf("@"))).setValue(technicianModel);
                    //mTechnicianDbReference.child("Technician").child(email.substring(0,email.indexOf("@"))).child("role").setValue("Technician");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
