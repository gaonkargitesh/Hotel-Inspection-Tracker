package com.example.rahul.hit.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.util.PreferenceHelper;
import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.homescreen.view.HomescreenActivity;
import com.example.rahul.hit.signup.view.SignUpActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.rahul.hit.util.PreferenceHelper.IS_LOGIN;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    //private FirebaseDatabase database;
    private SharedPreferences loginActivitySharedPreferences;
    public static boolean check;

    PreferenceHelper helper;

    TextView signupTextView;
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;

    Intent loginToSignupTextviewIntent;
    Intent loginPageToHomeScreenIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        signupTextView = findViewById(R.id.textView_SignUp_LoginPage);

        loginEmail = findViewById(R.id.editText_LoginPage_Email);
        loginPassword = findViewById(R.id.editText_Loginpage_Password);
        loginButton = findViewById(R.id.butoon_Login_LoginPage);


        loginButton.setOnClickListener(this);
        signupTextView.setOnClickListener(this);


        /*loginActivitySharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
        check=loginActivitySharedPreferences.getBoolean("isLogin",false);
        if(check){
            startActivity(loginPageToHomeScreenIntent);
        }*/

        //loginActivitySharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
        check=baseActivityPreferenceHelper.getBoolean(IS_LOGIN,false);
        /*if(check==false){
            startActivity(loginPageToHomeScreenIntent);
        }*/
    }

    private void userLogin() {
        final String email = loginEmail.getText().toString();
        final String password = loginPassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "email is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }


        /*firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    loginToSignupTextviewIntent=new Intent(LoginActivity.this,HomescreenActivity.class);
                    startActivity(loginToSignupTextviewIntent);
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
        users.addListenerForSingleValueEvent(new ValueEventListener() {

            boolean checkAuthentication= false;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if(dataSnapshot1.child("email").getValue().toString().equals(email) && dataSnapshot1.child("password").getValue().toString().equals(password)){
                        checkAuthentication=true;
                        break;
                    }
                }
                if(checkAuthentication){
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    baseActivityPreferenceHelper.putBoolean(IS_LOGIN,true);
                    loginPageToHomeScreenIntent= new Intent(LoginActivity.this,HomescreenActivity.class);
                    startActivity(loginPageToHomeScreenIntent);
                    finish();
                    /*SharedPreferences.Editor editor=loginActivitySharedPreferences.edit();
                    editor.putBoolean("isLogin",true);
                    editor.apply();*/


                }
                else{
                    Toast.makeText(LoginActivity.this,"Username or Password is invalid..",Toast.LENGTH_SHORT).show();
                }
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
        if (v == signupTextView) {
            loginToSignupTextviewIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(loginToSignupTextviewIntent);
        }
    }

    @Override
    protected void init() {

    }
}
