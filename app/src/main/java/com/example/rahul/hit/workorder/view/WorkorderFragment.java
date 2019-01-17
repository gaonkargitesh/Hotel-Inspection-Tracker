package com.example.rahul.hit.workorder.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.rahul.hit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkorderFragment extends Fragment {

    @BindView(R.id.floating_Button_Workorder_Fragement)
    FloatingActionButton addWorkOrderFloatingButton;


    Intent workorderButtonToAddWorkOrder;

    Context context;
    public WorkorderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_workorder, container, false);
        ButterKnife.bind(this,view);
        context=getActivity();
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @OnClick(R.id.floating_Button_Workorder_Fragement)
    public void onFloatingButtonClick(View view){
        workorderButtonToAddWorkOrder=new Intent(context,AddWorkOrder.class);
        startActivity(workorderButtonToAddWorkOrder);
    }
}
