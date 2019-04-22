package com.example.rahul.hit.util;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rahul.hit.dashboard.view.TechnicianFragment;
import com.example.rahul.hit.dashboard.view.UserFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private int tabNumber;

    public ViewPageAdapter(FragmentManager fm,int tabNumber) {

        super(fm);
        this.tabNumber=tabNumber;

    }

    @Override
    public Fragment getItem(int i) {


        switch (i){
            case 0:
                return new UserFragment();
            case 1:
                return new TechnicianFragment();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position == 0){
            title="Users";
        }
        if(position==1){
            title="Technician";
        }

        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
