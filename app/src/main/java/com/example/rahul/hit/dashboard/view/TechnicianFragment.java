package com.example.rahul.hit.dashboard.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahul.hit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicianFragment extends Fragment {

    @BindView(R.id.textView_AddTechnicianPage_AddTechnician)
    TextView addTechnician;

    Intent addTechnicianIntent;
    Context context;
    public TechnicianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_technician, container, false);
        ButterKnife.bind(this,view);
        context=getActivity();
        return view;
    }

    @OnClick(R.id.textView_AddTechnicianPage_AddTechnician)
    public void onTextViewClick(View view){
        addTechnicianIntent=new Intent(context,AddTechnician.class);
        startActivity(addTechnicianIntent);
    }
}
