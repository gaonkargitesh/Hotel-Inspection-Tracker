package com.example.rahul.hit.workorder.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.rahul.hit.R;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkorderFragment extends Fragment {

    @BindView(R.id.floating_Button_Workorder_Fragement)
    FloatingActionButton addWorkOrderFloatingButton;


    RecyclerView workOrderRecyclerView;
    DatabaseReference databaseReference;
    ArrayList<WorkOrderModel> workOrderList;
    WorkOrderAdapter workOrderAdapter;

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
        workOrderRecyclerView=view.findViewById(R.id.workOrder_RecyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Create Complaint");

        workOrderList=new ArrayList<WorkOrderModel>();

        workOrderAdapter=new WorkOrderAdapter(context,workOrderList);
        workOrderRecyclerView.setAdapter(workOrderAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                workOrderAdapter.clearCollection();
                Log.d("Complaint Fragment","Complaint called");
                if (workOrderAdapter != null)
                    workOrderAdapter.clearCollection();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    Log.d("data1",""+dataSnapshot1.child("Assigned to").getValue());

                    if(dataSnapshot1.child("Assigned to").getValue() !=null) {
                        WorkOrderModel workOrderModel = dataSnapshot1.getValue(WorkOrderModel.class);
                        workOrderList.add(workOrderModel);
                    }

                }
                workOrderAdapter.setCreateComplaints(workOrderList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        workOrderRecyclerView.setLayoutManager(new LinearLayoutManager(context));

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
