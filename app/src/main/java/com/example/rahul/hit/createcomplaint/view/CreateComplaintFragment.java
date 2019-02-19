package com.example.rahul.hit.createcomplaint.view;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.R;
import com.example.rahul.hit.technician.view.TechnicianAssignList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter.REQUEST_FOR_ACTIVITY_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateComplaintFragment extends Fragment {

    @BindView(R.id.floating_Button_CreateComplaint_Fragement)
    FloatingActionButton addCreateComplaintFloatingButton;


    Intent createCompButtonToAddComplaint;
    private Intent assignTechToAssignTechList;

    RecyclerView createComplaintRecyclerView;
    DatabaseReference databaseReference;
    ArrayList<CreateComplaintModel> complaintList;
    CreateComplaintAdapter createComplaintAdapter;


    Context context;
    public CreateComplaintFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        databaseReference= FirebaseDatabase.getInstance().getReference().child("Create Complaint");

        complaintList=new ArrayList<CreateComplaintModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    CreateComplaintModel createComplaintModel=dataSnapshot1.getValue(CreateComplaintModel.class);
                    complaintList.add(createComplaintModel);
                }

                createComplaintAdapter=new CreateComplaintAdapter(context,complaintList);

                //Adding lbelow line to separate the data in the recycelrview
                //createComplaintRecyclerView.addItemDecoration(new DividerItemDecoration(createComplaintRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

                createComplaintRecyclerView.setAdapter(createComplaintAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_complaint_fragement, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        createComplaintRecyclerView=view.findViewById(R.id.create_Complaint_RecyclerView);
        createComplaintRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }


    @OnClick(R.id.floating_Button_CreateComplaint_Fragement)
    public void onButtonClick(View view)
    {
        createCompButtonToAddComplaint=new Intent(context, AddCreateComplaint.class);
        startActivity(createCompButtonToAddComplaint);
    }

}
