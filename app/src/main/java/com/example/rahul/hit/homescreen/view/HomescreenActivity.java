package com.example.rahul.hit.homescreen.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.login.view.LoginActivity;
import com.example.rahul.hit.util.PreferenceHelper;
import com.example.rahul.hit.workorder.view.WorkorderFragment;

import static com.example.rahul.hit.util.PreferenceHelper.*;

public class HomescreenActivity extends BaseActivity {

    SharedPreferences sharedPreferences;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    android.support.v7.widget.Toolbar toolbar;
    PreferenceHelper homepreferencehelper;

    Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        //Replacing actionbar as toolbar
        toolbar=findViewById(R.id.toolbar_navigation_drawer);
        setSupportActionBar(toolbar);


        FragmentManager manager= getSupportFragmentManager();


        //Adding navigation bdrawer button
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        mDrawerLayout=findViewById(R.id.nav_drawer_drawerlayout);

        navigationView=findViewById(R.id.navigation_view_homescreen);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.item_workorder_navigation_drawer) {
                    //Intent homescreenToWorkorder= new Intent(this,WorkorderFragment.class);
                }

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;

            }
        });


        sharedPreferences=getSharedPreferences("HIT_PREFERENCE",MODE_PRIVATE);
        logoutButton=findViewById(R.id.button_Logout_HomeScreenPage);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivityPreferenceHelper.putBoolean(IS_LOGIN,false);
                Intent intent=new Intent(HomescreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    //Action to be performed whwn it clicks on the navigation drawer button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }*/

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void init() {

    }
}

