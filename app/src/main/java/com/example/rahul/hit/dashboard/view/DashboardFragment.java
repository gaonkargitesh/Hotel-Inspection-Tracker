package com.example.rahul.hit.dashboard.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rahul.hit.R;
import com.example.rahul.hit.util.ViewPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    @BindView(R.id.tabLayout_DashboardPage)
    TabLayout dashboardTablayout;

    @BindView(R.id.viewPager_DashboardPage)
    ViewPager viewPager;


    ViewPageAdapter viewPageAdapter;

    CoordinatorLayout coordinatorLayout;

    Context context;
    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_dashboard, container, false);
        context=getActivity();
        ButterKnife.bind(this,view);
        viewPageAdapter=new ViewPageAdapter(getChildFragmentManager(),2);
        viewPager.setAdapter(viewPageAdapter);
        dashboardTablayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                /*if(i==0){
                    Snackbar snackbar=Snackbar.make(coordinatorLayout,"Users is selected",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                if(i==1){
                    Snackbar snackbar=Snackbar.make(coordinatorLayout,"Technicain is selected",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

}
