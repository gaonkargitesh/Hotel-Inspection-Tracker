package com.example.rahul.hit.homescreen.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintFragment;
import com.example.rahul.hit.dashboard.view.DashboardFragment;
import com.example.rahul.hit.login.view.LoginActivity;
import com.example.rahul.hit.settings.view.SettingsFragment;
import com.example.rahul.hit.util.PreferenceHelper;
import com.example.rahul.hit.util.Users;
import com.example.rahul.hit.workorder.view.WorkorderFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rahul.hit.login.view.LoginActivity.email;
import static com.example.rahul.hit.util.PreferenceHelper.*;

public class HomescreenActivity extends BaseActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences emailSharedPreferences;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private static final String TAG = "HomescreenActivity";
    private android.support.v7.widget.Toolbar toolbar;
    PreferenceHelper homepreferencehelper;

    TextView navHeaderUsername;
    TextView navHeaderEmail;
    DatabaseReference reference;

    Intent homescreenLogoutToLogin;
    //Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        ButterKnife.bind(this);
        //Replacing actionbar as toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reference = FirebaseDatabase.getInstance().getReference();

        //Email SharedPrederenes
        /*emailSharedPreferences=getSharedPreferences("EMAIL",MODE_PRIVATE);
        baseActivityPreferenceHelper.putBoolean("EMAIL",);*/


        FloatingActionButton floatingActionButton = findViewById(R.id.floating_Button_Workorder_Fragement);

        //Adding navigation bdrawer button
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);*/

        FragmentManager manager = getSupportFragmentManager();

        //FragmentTransaction manager=getSupportFragmentManager();
        mDrawerLayout = findViewById(R.id.nav_drawer_drawerlayout);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(HomescreenActivity.this, mDrawerLayout, toolbar, R.string.nav_drawer_settings, R.string.nav_drawer_dashboard);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        //The following line is used to give the HamBurgerIcon color.
        //We can also give the HamBurgerIcon color by Property named as "itemIconTint"
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.White));
        actionBarDrawerToggle.syncState();


        navigationView = findViewById(R.id.navigation_view_homescreen);
        View header = navigationView.getHeaderView(0);
        navHeaderUsername = header.findViewById(R.id.textView_NavHeader_Name);
        navHeaderEmail = header.findViewById(R.id.textView_NavHeader_Email);
        String email = baseActivityPreferenceHelper.getString("mail", "");
        Log.d(TAG, "Email is " + email);
        String name = baseActivityPreferenceHelper.getString("name", "");



        Log.d(TAG, "Values are: " + email);
        Log.d(TAG, "Values are: " + name);
        DatabaseReference ref = reference.child("Users").child(email.substring(0, email.indexOf("@")));
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.d(TAG, "An email is " + dataSnapshot.child("email").getValue().toString());
                navHeaderUsername.setText(dataSnapshot.child("firstname").getValue().toString().concat(" " + dataSnapshot.child("lastname").getValue().toString()));
                navHeaderEmail.setText(dataSnapshot.child("email").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //DatabaseReference e=reference.child("Users").child(email.)

        /*DatabaseReference reference1=reference.child("Users").child(email.substring(0,email.indexOf("@"))).child("email");
        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                *//*for(DataSnapshot data:dataSnapshot.getChildren()){

                    String m=data.child("email").getValue().toString();
                    Log.d("email","email"+m);
                    navHeaderEmail.setText(m);
                }*//*

                Users u=dataSnapshot.getValue(Users.class);
                navHeaderEmail.setText(u.getEmail());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        //navHeaderEmail.setText(PreferenceHelper.getInstance(this).ge);
        /*String email=navHeaderEmail.toString();
        DatabaseReference e =reference.child("Users").child("email" +
                "");
        Log.d("email",""+email);*/


        //Add First fragment by default (WorkOrderFragment)
        WorkorderFragment workorderFragment = new WorkorderFragment();
        Log.d("WorkOrderClass", "Before Add method");
        setTitle("Work Order");
        addFragment(workorderFragment);
        Log.d("WorkOrderClass", "After Add method");

        //navigationView.setCheckedItem(R.id.item_workorder_navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("WorkOrderClass", "inside onNangigationItemSelected" + menuItem);

                /*int id =menuItem.getItemId();

                Log.d("Itemid","ID:"+id);
                switch (menuItem.getItemId()) {

                    case R.id.item_workorder_navigation_drawer:
                        Log.d("WorkOrderNav","Inside work irder case");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                new WorkorderFragment()).commit();
                        break;
                    case R.id.item_create_complaint_navigation_drawer:
                        Log.d("Complaintnav","Inside COmplainr case");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                new CreateComplaintFragment()).commit();
                        break;
                    case R.id.item_dashboard_navigation_drawer:
                        Log.d("Dashboardnav","Inside Dashboard case");
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,
                                new DashboardFragment()).commit();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                //Replace Fragment by currently clicked item
                menuItem.setChecked(true);
                return true;*/
                int id = menuItem.getItemId();
                Log.d("Itemid", "ID:" + id);
                if (id == R.id.item_workorder_navigation_drawer) {
                    setTitle("Work Order");
                    WorkorderFragment workorderFragment = new WorkorderFragment();
                    replaceFragment(workorderFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,workorderFragment).commit();*/
                } else if (id == R.id.item_create_complaint_navigation_drawer) {
                    setTitle("Create Complaint");
                    CreateComplaintFragment createComplaintFragment = new CreateComplaintFragment();
                    replaceFragment(createComplaintFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,createComplaintFragment).commit();*/

                } else if (id == R.id.item_dashboard_navigation_drawer) {
                    Log.d("Dashboardnav", "Inside Dashboard case");
                    setTitle("Dashboard");
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    replaceFragment(dashboardFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,dashboardFragment).commit();*/
                } else if (id == R.id.item_settings_navigation_drawer) {
                    setTitle("Settings");
                    SettingsFragment settingsFragment = new SettingsFragment();
                    replaceFragment(settingsFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,settingsFragment).commit();*/
                } else if (id == R.id.item_logout_navigation_drawer) {
                    sharedPreferences = getSharedPreferences("HIT_PREFERENCE", MODE_PRIVATE);
                    baseActivityPreferenceHelper.putBoolean(IS_LOGIN, false);
                    Intent intent = new Intent(HomescreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                //Replace Fragment by currently clicked item
                menuItem.setChecked(true);
                return true;
            }
        });


        /*sharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
        logoutButton=findViewById(R.id.button_Logout_HomeScreenPage);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivityPreferenceHelper.putBoolean(IS_LOGIN,false);
                Intent intent=new Intent(HomescreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, fragment)
                .commit();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }


    //Action to be performed whwn it clicks on the navigation drawer button
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    protected void init() {

    }

}

