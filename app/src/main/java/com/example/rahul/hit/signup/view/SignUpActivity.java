package com.example.rahul.hit.signup.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.constants.AppConstant;
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

public class SignUpActivity extends BaseActivity implements View.OnClickListener {

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

    ConstraintLayout constraintLayout;

    Intent signupToLoginTextviewIntent;
    Intent signupToHomeScreenIntent;
    FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        constraintLayout=findViewById(R.id.layoutConstraint_SignUpPage);
        firstNameEditText = findViewById(R.id.editText_SignupPage_FirstName);
        lastNameEditText = findViewById(R.id.editText_SignupPage_LastName);
        roomNoEditText = findViewById(R.id.editText_SignupPage_RoomNo);
        emailEditText = findViewById(R.id.editText_SignupPage_Email);
        passwordEditText = findViewById(R.id.editText_SignupPage_Password);
        confirmPasswordEditText = findViewById(R.id.editText_SignupPage_ConfirmPassword);
        ButterKnife.bind(this);

        signupButton = findViewById(R.id.button_SignupPage_SignUp);
        loginTextView = findViewById(R.id.textView_SignupPage_Login);

        signupButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);

    }

    @Override
    protected void init() {

    }

    private void userSignUp() {

        final String firstName = firstNameEditText.getText().toString();
        final String roomNo = roomNoEditText.getText().toString();
        final String email = emailEditText.getText().toString();
        final String lastName = lastNameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        final String confirmPassword = confirmPasswordEditText.getText().toString();
        final String role = "User";
        final String role1 = "Admin";
        final String fullname = firstName + " " + lastName;

        if (TextUtils.isEmpty(firstName)) {
            //Toast.makeText(SignUpActivity.this,"Fullname is empty",Toast.LENGTH_SHORT).show();
            firstNameEditText.setError("First name is empty");
            return;
        } else if (TextUtils.isEmpty(roomNo)) {
            //Toast.makeText(SignUpActivity.this,"roomno is empty",Toast.LENGTH_SHORT).show();
            roomNoEditText.setError("Room no is empty");
            return;
        } else if (TextUtils.isEmpty(email)) {
            //Toast.makeText(SignUpActivity.this,"email is empty",Toast.LENGTH_SHORT).show();
            emailEditText.setError("Email is empty");
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            //Toast.makeText(SignUpActivity.this,"username is empty",Toast.LENGTH_SHORT).show();
            lastNameEditText.setError("Last name is empty");
            return;
        } else if (TextUtils.isEmpty(password)) {
            //Toast.makeText(SignUpActivity.this,"password is empty",Toast.LENGTH_SHORT).show();
            passwordEditText.setError("Password is empty");
            return;
        } else if (TextUtils.isEmpty(confirmPassword)) {
            //Toast.makeText(SignUpActivity.this,"confirm password is empty",Toast.LENGTH_SHORT).show();
            confirmPasswordEditText.setError("Confirm Password is empty");
            return;
        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            emailEditText.setError("Invalid Email Address");
            return;
        } else if (!TextUtils.equals(password, confirmPassword)) {
            confirmPasswordEditText.setError("Password does not match");
            return;
        } else if (firstNameEditText.getText() != null && lastNameEditText.getText() != null && emailEditText.getText() != null
                && roomNoEditText.getText() != null && passwordEditText.getText() != null && confirmPasswordEditText.getText() != null) {
            signupButton.setVisibility(View.VISIBLE);
            signupButton.setEnabled(true);
            signupButton.setClickable(true);
        }
        else if (!(firstNameEditText.getText() != null && lastNameEditText.getText() != null && emailEditText.getText() != null
                && roomNoEditText.getText() != null && passwordEditText.getText() != null && confirmPasswordEditText.getText() != null)) {
            signupButton.setVisibility(View.INVISIBLE);
            signupButton.setEnabled(false);
            signupButton.setClickable(false);
        }
        /*else if(firstName.equals("")|| lastName.equals("")||email.equals("")||roomNo.equals("")||password.equals("")||confirmPassword.equals("")){
            signupButton.setEnabled(false);
        }
        else if(!(firstName.equals("")|| lastName.equals("")||email.equals("")||roomNo.equals("")||password.equals("")||confirmPassword.equals(""))){
            signupButton.setEnabled(true);
        }*/
        else {
            //Toast.makeText(this, "Registration Successful..", Toast.LENGTH_SHORT).show();

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
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null && dataSnapshot.child("email").getValue().toString().equalsIgnoreCase(email)) { // User is already registered user
                    //Show Error Message
                    //Toast.makeText(SignUpActivity.this, "User already exists..", Toast.LENGTH_SHORT).show();
                    Snackbar snackbar=Snackbar.make(constraintLayout,"User already exists.",Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    //Perform Next Step as Validation is successful

                    if (email.contains("gitesh@admin.com")) {
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("email").setValue(email);
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("firstname").setValue(firstName);
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("lastname").setValue(lastName);
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("password").setValue(password);
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("confirmpassword").setValue(confirmPassword);
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("role").setValue(role1);
                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        finish();
                        //mDatabase.child("Users").child(email.substring(0,email.indexOf("@"))).child("role").setValue("admin");
                        //baseActivityPreferenceHelper.putString("role","admin");
                    } else {
                        final Users users = new Users(firstName, roomNo, email, lastName, password, confirmPassword, role);


                        //usersRef.child("Users").child(email.substring(0,email.indexOf("@"))).setValue(users);
                        mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).setValue(users);
                        //mDatabase.child("Users").child(email.substring(0, email.indexOf("@"))).child("role").setValue("User");
                        baseActivityPreferenceHelper.putString("full_name", firstName);
                        baseActivityPreferenceHelper.putString("mail", email);
                        baseActivityPreferenceHelper.putString("role", "User");
                        Log.d("Signup Activity", "Values are: " + AppConstant.BundleKey.fullName);
                        Log.d("Signup Activity", "Values are: " + AppConstant.BundleKey.email);
                        Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        finish();
                        //signupToHomeScreenIntent = new Intent(SignUpActivity.this, HomescreenActivity.class);
                        //startActivity(signupToHomeScreenIntent);
                        //finish();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        /*final DatabaseReference admin=FirebaseDatabase.getInstance().getReference().child("Users").child(email.substring(0,email.indexOf("@")));
        admin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("fd",""+dataSnapshot);
                admin.child("role").setValue("Admin");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


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

        if (v == signupButton) {
            userSignUp();
        }
        if (v == loginTextView) {
            signupToLoginTextviewIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(signupToLoginTextviewIntent);
            finish();
        }
    }
}

