package com.example.rahul.hit.signup.view;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.R;
import com.example.rahul.hit.homescreen.view.HomescreenActivity;
import com.example.rahul.hit.login.view.LoginActivity;
import com.example.rahul.hit.util.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    //@BindView(R.id.editText_SignupPage_FirstName)
    EditText firstNameEditText;

    //@BindView(R.id.editText_SignupPage_LastName)
    EditText lastNameEditText;

    //@BindView(R.id.editText_SignupPage_RoomNo)
    EditText roomNoEditText;

    //@BindView(R.id.editText_SignupPage_Email)
    EditText emailEditText;

    //@BindView(R.id.editText_Loginpage_Password)
    EditText passwordEditText;

    //@BindView(R.id.editText_SignupPage_ConfirmPassword)
    EditText confirmPasswordEditText;

    //@BindView(R.id.textView_SignupPage_Login)
    TextView loginTextView;

    //@BindView(R.id.button_SignupPage_SignUp)
    Button signupButton;

    Intent signupToLoginTextviewIntent;
    Intent signupToHomeScreenIntent;
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstNameEditText=findViewById(R.id.editText_SignupPage_FirstName);
        lastNameEditText=findViewById(R.id.editText_SignupPage_LastName);
        roomNoEditText=findViewById(R.id.editText_SignupPage_RoomNo);
        emailEditText=findViewById(R.id.editText_SignupPage_Email);
        passwordEditText=findViewById(R.id.editText_SignupPage_Password);
        confirmPasswordEditText=findViewById(R.id.editText_SignupPage_ConfirmPassword);
        ButterKnife.bind(this);

        signupButton=findViewById(R.id.button_SignupPage_SignUp);
        loginTextView=findViewById(R.id.textView_SignupPage_Login);

        signupButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);

    }
    private void userSignUp(){

        final String firstName=firstNameEditText.getText().toString();
        final String roomNo=roomNoEditText.getText().toString();
        final String email=emailEditText.getText().toString();
        final String lastName=lastNameEditText.getText().toString();
        final String password=passwordEditText.getText().toString();
        final String confirmPassword=confirmPasswordEditText.getText().toString();

        if(TextUtils.isEmpty(firstName)){
            Toast.makeText(SignUpActivity.this,"Fullname is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(roomNo)){
            Toast.makeText(SignUpActivity.this,"roomno is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignUpActivity.this,"email is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(lastName)){
            Toast.makeText(SignUpActivity.this,"username is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SignUpActivity.this,"password is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(SignUpActivity.this,"confirm password is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        /*firebaseAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Users users= new Users(fullname,roomno,email,username,password,confirmPassword);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(context,"Registered Successfully",Toast.LENGTH_SHORT).show();
                                        signupToLoginTextviewIntent=new Intent(SignUpActivity.this,LoginActivity.class);
                                        startActivity(signupToLoginTextviewIntent);
                                    }
                                    else{
                                        Toast.makeText(context,"Registration unsuccessful",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        *//*else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }*//*
                    }
                });*/

       /* mDatabase = FirebaseDatabase.getInstance().getReference();
        String randomid=mDatabase.push().getKey();
        final Users users= new Users(fullname,roomno,email,username,password,confirmPassword);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Toast.makeText(SignUpActivity.this,"User already exists..",Toast.LENGTH_SHORT).show();
                }
                else{
                    mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).setValue(users);
                    signupToHomeScreenIntent = new Intent(SignUpActivity.this, HomescreenActivity.class);
                    startActivity(signupToHomeScreenIntent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        mDatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference usersRef = mDatabase.child("Users").child(email.substring(0, email.indexOf("@")));
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) { // User is already registered user
                    //Show Error Message
                    Toast.makeText(SignUpActivity.this,"User already exists..",Toast.LENGTH_SHORT).show();
                } else {
                    //Perform Next Step as Validation is successful
                    final Users users= new Users(firstName,roomNo,email,lastName,password,confirmPassword);

                    //usersRef.child("Users").child(email.substring(0,email.indexOf("@"))).setValue(users);
                    mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).setValue(users);
                    //signupToHomeScreenIntent = new Intent(SignUpActivity.this, HomescreenActivity.class);
                    //startActivity(signupToHomeScreenIntent);
                    //finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


    }

    /*@OnClick({R.id.textView_SignupPage_Login,R.id.button_SignupPage_SignUp})
    public void checkViewOnSignupPage()
    {
        if(loginTextView){
            signupToLoginTextviewIntent =new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(signupToLoginTextviewIntent);
        }
    }*/

    @Override
    public void onClick(View v) {

        if (v== signupButton){
            userSignUp();
            finish();
        }
        if (v==loginTextView){
            signupToLoginTextviewIntent =new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(signupToLoginTextviewIntent);
            finish();
        }
    }
}

