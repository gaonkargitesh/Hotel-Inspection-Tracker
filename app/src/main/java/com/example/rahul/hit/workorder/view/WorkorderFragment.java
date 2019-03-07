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

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.BaseFragment;
import com.example.rahul.hit.R;
import com.example.rahul.hit.constants.AppConstant;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintModel;
import com.example.rahul.hit.util.TechnicianModel;
import com.example.rahul.hit.util.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */

public class WorkorderFragment extends BaseFragment {

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
        View view = inflater.inflate(R.layout.fragment_workorder, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        workOrderRecyclerView = view.findViewById(R.id.workOrder_RecyclerView);
        final String userRoleAssign = AppConstant.BundleKey.userRole;
        final String emailassign = AppConstant.BundleKey.email;

        Log.d("WORKORDERLOG ", "Role in workOrderFragment " + userRoleAssign);
        Log.d("WORKORDERLOG ", "email in workOrderFragment " + emailassign);


        Log.d("WORKORDERLOG", "email assigned technician: " + (emailassign.substring(0, emailassign.indexOf("@"))));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Create Complaint");

        workOrderList = new ArrayList<WorkOrderModel>();

        workOrderAdapter = new WorkOrderAdapter(context, workOrderList);
        workOrderRecyclerView.setAdapter(workOrderAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //workOrderAdapter.clearCollection();
                Log.d("Complaint Fragment", "Complaint called");
                if (workOrderAdapter != null)
                    workOrderAdapter.clearCollection();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    //Log.d("data1",""+dataSnapshot1.child("AssignedTo").getValue());
                    Log.d(TAG, "onDataChange: Workorder" + dataSnapshot1.getKey());

                    //Log.d("WORKORDERLOG", "Tech assigned boolea:  "+dataSnapshot1.child("AssignedTo").getValue().equals(emailassign));
                    //Log.d("checktech",""+dataSnapshot1.child("AssignedTo").getValue().equals(emailassign));

                    /*Log.d("NEWLOG1", "onDataChange: "+(dataSnapshot1.child("AssignedTo").getValue() == emailassign *//*&& dataSnapshot.getValue().
                            equals(emailassign.substring(0, emailassign.indexOf("@")))*//*|| dataSnapshot1.child("AssignedTo").getValue()!=null ));

                    Log.d("NEWLOG2", "onDataChange: "+(dataSnapshot1.child("AssignedTo").getValue() == emailassign *//*|| dataSnapshot.getValue().
                            equals(emailassign.substring(0, emailassign.indexOf("@")))*//*&& dataSnapshot1.child("AssignedTo").getValue()!=null ));*/

                    //Log.d("Datansaphot OWrkorder", "onDataChange: Inside workorderfragment "+dataSnapshot1);
                    //Users users= dataSnapshot1.getValue(Users.class);
                    //String useremail=users.getEmail();
                    //Log.d("email in workorder",""+useremail);
                    //TechnicianModel technicianModel=dataSnapshot1.getValue(TechnicianModel.class);
                    //String techemail=technicianModel.getEmail();
                    //Log.d("2nd email in work order",""+techemail);
                    switch (userRoleAssign) {
                        case "Admin":
                            Log.d("WORKORDERLOG", "inside admin swith");
                            if (dataSnapshot1.child("AssignedTo").getValue() != null) {
                                Log.d("WORKORDERLOG", "onDataChange: first switch admin call");
                                WorkOrderModel workOrderModel = dataSnapshot1.getValue(WorkOrderModel.class);
                                workOrderList.add(workOrderModel);
                            }
                            break;
                        case "Technician":
                            //Log.d("WORKORDERLOG", "onDataChange: Inside ttech switch");
                            /*if (dataSnapshot1.child("AssignedTo").getValue() == emailassign && dataSnapshot.getValue().
                                    equals(emailassign.substring(0, emailassign.indexOf("@")))&& dataSnapshot1.child("AssignedTo").getValue()!=null ) {
                                Log.d("WORKORDERLOG", "onDataChange: second switch tech call");
                                WorkOrderModel workOrderModel = dataSnapshot1.getValue(WorkOrderModel.class);
                                workOrderList.add(workOrderModel);
                            }*/
                            /*if (dataSnapshot1.child("AssignedTo").getValue() == emailassign || dataSnapshot1.child("AssignedTo").getValue()!=null ) {
                                Log.d("WORKORDERLOG", "onDataChange: second switch tech call");
                                WorkOrderModel workOrderModel = dataSnapshot1.getValue(WorkOrderModel.class);
                                workOrderList.add(workOrderModel);
                            }*/
                            break;
                        case "User":
                            Log.d("WORKORDERLOG", "onDataChange: Indside user switch");
                            if (dataSnapshot1.child("AssignedTo").getValue() != null && dataSnapshot1.getKey().
                                    equals(emailassign.substring(0, emailassign.indexOf("@")))) {
                                Log.d("WORKORDERLOG", "onDataChange: third switch user call");
                                WorkOrderModel workOrderModel = dataSnapshot1.getValue(WorkOrderModel.class);
                                workOrderList.add(workOrderModel);
                            }

                        default:
                            Log.d(TAG, "onDataChange: Nothing is called");
                            break;
                    }

                    /*if(dataSnapshot1.child("AssignedTo").getValue()==emailassign && dataSnapshot.getValue().equals(emailassign.substring(0,emailassign.indexOf("@")))){
                        Log.d(TAG, "onDataChange: second tech call");
                        WorkOrderModel workOrderModel=dataSnapshot1.getValue(WorkOrderModel.class);
                        workOrderList.add(workOrderModel);
                    }

                    //Admin workorders
                    else if(dataSnapshot1.child("AssignedTo").getValue() !=null) {
                        Log.d(TAG, "onDataChange: first admin call");
                        WorkOrderModel workOrderModel = dataSnapshot1.getValue(WorkOrderModel.class);
                        workOrderList.add(workOrderModel);
                    }*/
                    //Technician workorder

                    //Users workorderr

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

    @Override
    protected void init() {

    }

    @OnClick(R.id.floating_Button_Workorder_Fragement)
    public void onFloatingButtonClick(View view) {
        workorderButtonToAddWorkOrder = new Intent(context, AddWorkOrder.class);
        startActivity(workorderButtonToAddWorkOrder);
    }


}
