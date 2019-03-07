package com.example.rahul.hit.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.constants.AppConstant;
import com.example.rahul.hit.util.PreferenceHelper;
import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.homescreen.view.HomescreenActivity;
import com.example.rahul.hit.signup.view.SignUpActivity;
import com.example.rahul.hit.util.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rahul.hit.util.PreferenceHelper.IS_LOGIN;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    //private FirebaseDatabase database;
    private SharedPreferences loginActivitySharedPreferences;
    public static boolean check;
    public static boolean email;

    PreferenceHelper helper;

    TextInputLayout emailTextInputLayout;
    TextInputLayout passwordTextInputLayout;

    TextView signUpTextView;
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;

    Intent loginToSignUpTextViewIntent;
    Intent loginPageToHomeScreenIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        signUpTextView = findViewById(R.id.textView_SignUp_LoginPage);

        emailTextInputLayout=findViewById(R.id.textlayout_email_loginPage);
        passwordTextInputLayout=findViewById(R.id.textlayout_password_loginPage);


        loginEmail = findViewById(R.id.editText_LoginPage_Email);
        loginPassword = findViewById(R.id.editText_Loginpage_Password);
        loginButton = findViewById(R.id.butoon_Login_LoginPage);


        loginButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);


        /*loginActivitySharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
        check=loginActivitySharedPreferences.getBoolean("isLogin",false);
        if(check){
            startActivity(loginPageToHomeScreenIntent);
        }*/

        //loginActivitySharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);


        /*email=baseActivityPreferenceHelper.getString(,false);*/
        check=baseActivityPreferenceHelper.getBoolean(IS_LOGIN,false);
        /*if(check==false){
            startActivity(loginPageToHomeScreenIntent);
        }*/
    }

    private static final String TAG = "LoginActivity";

    private void userLogin() {
        final String email = loginEmail.getText().toString();
        final String password = loginPassword.getText().toString();
        Log.d(TAG,"Email is "+email);
        if (TextUtils.isEmpty(email)) {
            //Toast.makeText(LoginActivity.this, "email is empty", Toast.LENGTH_SHORT).show();
            //loginEmail.setError("Email is empty");
            emailTextInputLayout.setErrorEnabled(true);
            emailTextInputLayout.setError("Email is empty");
            return;
        }

        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            loginEmail.setError("Invalid Email Address");
        }
        if (TextUtils.isEmpty(password)) {
            //Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            //loginPassword.setError("Password is empty");
            passwordTextInputLayout.setErrorEnabled(true);
            passwordTextInputLayout.setError("Password is empty");
            return;
        }
        if(loginEmail.getText() !=null && loginPassword.getText() !=null){
            loginButton.setEnabled(true);
        }
        if(loginEmail.getText() ==null && loginPassword.getText() ==null){
            loginButton.setEnabled(false);
        }


        else{
            Toast.makeText(this, "Login Succeessful..", Toast.LENGTH_SHORT).show();
        }


        /*firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginToSignUpTextViewIntent=new Intent(LoginActivity.this,HomescreenActivity.class);
                    startActivity(loginToSignUpTextViewIntent);
                }
            }
        });

        if (mDatabase.child(email) == null) {
            mDatabase.child(email).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Users users = dataSnapshot.getValue(Users.class);
                    Log.d("checkvalue","Username:"+email+ "password: "+password);
                    if (password.equals(mDatabase.child(password))) {
                        Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        loginPageToHomeScreenIntent= new Intent(LoginActivity.this,HomescreenActivity.class);
                        startActivity(loginPageToHomeScreenIntent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Log.w("checkwarnign","df"+databaseError.toException());
                }
            });
        else {
            Toast.makeText(LoginActivity.this, "Login failed.. user doesnt exist", Toast.LENGTH_LONG).show();
        }


        Users user=new Users(email,password);
        mDatabase.child("Users").child(email).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            if (password.equals(mDatabase.child(password))) {
                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                loginPageToHomeScreenIntent= new Intent(LoginActivity.this,HomescreenActivity.class);
                                startActivity(loginPageToHomeScreenIntent);
                            }
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                    }
                });*/



        DatabaseReference users= mDatabase.child("Users");
        users.addValueEventListener(new ValueEventListener() {

            boolean checkAuthentication= false;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d(TAG, "Loginq datanapor"+dataSnapshot1);
                    Log.d(TAG, "On datasnapshot change value is" + dataSnapshot1.child("email").getValue());
                    Log.d(TAG, "On datasnapshot change value is" + dataSnapshot1.child("password").getValue());
                    if (dataSnapshot1.getValue() != null && dataSnapshot1.child("email").getValue().toString().equalsIgnoreCase(email)
                            && dataSnapshot1.child("password").getValue().toString().equals(password)) {
                        //Navigate to home screen
                        loginPageToHomeScreenIntent = new Intent(LoginActivity.this, HomescreenActivity.class);
                        /*Intent intent = new Intent(LoginActivity.this,HomescreenActivity.class);*/
                    /*mPreferenceHelper.putBoolean("is_Login",true);
                    mPreferenceHelper.putString("e-mail",mail);
                    mPreferenceHelper.putString("team_name ",mTeamName);*/
                        baseActivityPreferenceHelper.putBoolean(IS_LOGIN, true);
                        baseActivityPreferenceHelper.putString("mail", email);
                        baseActivityPreferenceHelper.putString("loginrole",dataSnapshot1.child("role").getValue().toString());
                        loginPageToHomeScreenIntent = new Intent(LoginActivity.this, HomescreenActivity.class);
                        loginPageToHomeScreenIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(loginPageToHomeScreenIntent);
                        finish();
                    } /*else {
                        Toast.makeText(LoginActivity.this, "Email or Password is incorrect", Toast.LENGTH_LONG).show();
                    }*/
                }

                //The code is not working .. app is getting crashed.... New Users cannot login...
                /*for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Log.d(TAG,"inside dasanpshot 1 value is"+dataSnapshot1);
                    if(dataSnapshot1.child("email").getValue().toString().equals(email) && dataSnapshot1.child("password").getValue().toString().equals(password)){
                        checkAuthentication=true;
                        break;
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Username or Password is invalid..",Toast.LENGTH_SHORT).show();
                    }
                }*/



                /*if(checkAuthentication){
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                    loginPageToHomeScreenIntent=getIntent();
                    //baseActivityPreferenceHelper.putBoolean(EMAIL,true);
                    baseActivityPreferenceHelper.putBoolean(IS_LOGIN,true);
                    loginPageToHomeScreenIntent= new Intent(LoginActivity.this,HomescreenActivity.class);
                    baseActivityPreferenceHelper.putString("name", AppConstant.BundleKey.fullName);
                    Log.d("Login","Value of email "+email);
                    baseActivityPreferenceHelper.putString("mail",email);
                    startActivity(loginPageToHomeScreenIntent);
                    finish();
                    *//*SharedPreferences.Editor editor=loginActivitySharedPreferences.edit();
                    editor.putBoolean("isLogin",true);
                    editor.apply();*//*


                }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v == loginButton) {
            userLogin();
        }
        if (v == signUpTextView) {
            loginToSignUpTextViewIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(loginToSignUpTextViewIntent);
        }
    }

    @Override
    protected void init() {

    }
}
