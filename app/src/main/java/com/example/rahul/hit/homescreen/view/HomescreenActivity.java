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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintFragment;
import com.example.rahul.hit.dashboard.view.DashboardFragment;
import com.example.rahul.hit.login.view.LoginActivity;
import com.example.rahul.hit.settings.view.SettingsFragment;
import com.example.rahul.hit.util.PreferenceHelper;
import com.example.rahul.hit.workorder.view.WorkorderFragment;

import static com.example.rahul.hit.util.PreferenceHelper.*;

public class HomescreenActivity extends BaseActivity {

    SharedPreferences sharedPreferences;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;

    private android.support.v7.widget.Toolbar toolbar;
    PreferenceHelper homepreferencehelper;

    Intent homescreenLogoutToLogin;
    //Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        //Replacing actionbar as toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton floatingActionButton=findViewById(R.id.floating_Button_Workorder_Fragement);

        //Adding navigation bdrawer button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        FragmentManager manager= getSupportFragmentManager();

        //FragmentTransaction manager=getSupportFragmentManager();
        mDrawerLayout = findViewById(R.id.nav_drawer_drawerlayout);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(HomescreenActivity.this, mDrawerLayout, toolbar,R.string.nav_drawer_settings,R.string.nav_drawer_dashboard);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView=findViewById(R.id.navigation_view_homescreen);

        //Add First fragment by default (WorkOrderFragment)
        WorkorderFragment workorderFragment=new WorkorderFragment();
        Log.d("WorkOrderClass","Before Add method");
        setTitle("Work Order");
        addFragment(workorderFragment);
        Log.d("WorkOrderClass","After Add method");

        //navigationView.setCheckedItem(R.id.item_workorder_navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("WorkOrderClass", "inside onNangigationItemSelected"+menuItem);
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
                Log.d("Itemid","ID:"+id);
                if (id == R.id.item_workorder_navigation_drawer) {
                    setTitle("Work Order");
                    WorkorderFragment workorderFragment= new WorkorderFragment();
                    replaceFragment(workorderFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,workorderFragment).commit();*/
                }
                else if (id == R.id.item_create_complaint_navigation_drawer) {
                    setTitle("Create Complaint");
                    CreateComplaintFragment createComplaintFragment= new CreateComplaintFragment();
                    replaceFragment(createComplaintFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,createComplaintFragment).commit();*/

                }
                else if (id == R.id.item_dashboard_navigation_drawer) {
                    Log.d("Dashboardnav","Inside Dashboard case");
                    setTitle("Dashboard");
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    replaceFragment(dashboardFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,dashboardFragment).commit();*/
                }
                else if (id == R.id.item_settings_navigation_drawer) {
                    setTitle("Settings");
                    SettingsFragment settingsFragment= new SettingsFragment();
                    replaceFragment(settingsFragment);
                    /*FragmentManager fragmentManager=getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout,settingsFragment).commit();*/
                }
                else if(id==R.id.item_logout_navigation_drawer){
                    sharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
                    baseActivityPreferenceHelper.putBoolean(IS_LOGIN,false);
                    Intent intent= new Intent(HomescreenActivity.this,LoginActivity.class);
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


        public void addFragment(Fragment fragment){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, fragment)
                    .commit();
        }

        private void replaceFragment(Fragment fragment){
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

