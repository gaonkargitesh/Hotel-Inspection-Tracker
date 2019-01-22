package com.example.rahul.hit.createcomplaint.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rahul.hit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateComplaintFragment extends Fragment {

    @BindView(R.id.floating_Button_CreateComplaint_Fragement)
    FloatingActionButton addCreateComplaintFloatingButton;

    Intent createCompButtonToAddComplaint;


    Context context;
    public CreateComplaintFragment() {
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
        View view = inflater.inflate(R.layout.fragment_create_complaint_fragement, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        return view;
    }



    @OnClick(R.id.floating_Button_CreateComplaint_Fragement)
    public void onButtonClick(View view)
    {
        createCompButtonToAddComplaint=new Intent(context, AddCreateComplaint.class);
        startActivity(createCompButtonToAddComplaint);

    }

}
