package com.example.rahul.hit.createcomplaint.view;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.R;
import com.example.rahul.hit.constants.AppConstant;
import com.example.rahul.hit.technician.view.TechnicianAssignList;
import com.example.rahul.hit.workorder.view.WorkOrderModel;
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
import static android.support.constraint.Constraints.TAG;
import static android.view.View.INVISIBLE;
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

    Button assignButton;

    Context context;

    public CreateComplaintFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*databaseReference= FirebaseDatabase.getInstance().getReference().child("Create Complaint");

        complaintList=new ArrayList<CreateComplaintModel>();

        createComplaintAdapter=new CreateComplaintAdapter(context,complaintList);

        Log.d("complaintview",""+createComplaintRecyclerView);
        createComplaintRecyclerView.setAdapter(createComplaintAdapter);
        //Adding lbelow line to separate the data in the recycelrview
        //createComplaintRecyclerView.addItemDecoration(new DividerItemDecoration(createComplaintRecyclerView.getContext(), DividerItemDecoration.VERTICAL));



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                createComplaintAdapter.clearCollection();
                Log.d("Complaint Fragment","Complaint called");
                if (createComplaintAdapter != null)
                    createComplaintAdapter.clearCollection();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    CreateComplaintModel createComplaintModel=dataSnapshot1.getValue(CreateComplaintModel.class);
                    complaintList.add(createComplaintModel);
                }
                createComplaintAdapter.setCreateComplaints(complaintList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_complaint_fragement, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        createComplaintRecyclerView = view.findViewById(R.id.create_Complaint_RecyclerView);
        assignButton=view.findViewById(R.id.button_complaint_list_assignComplaint);

        final String userRoleAssign = AppConstant.BundleKey.userRole;
        final String emailassign = AppConstant.BundleKey.email;

        Log.d(TAG, "complaintinfo"+userRoleAssign);
        Log.d(TAG, "complaintinfo"+emailassign);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Create Complaint");

        complaintList = new ArrayList<CreateComplaintModel>();

        createComplaintAdapter = new CreateComplaintAdapter(context, complaintList);

        Log.d("complaintview", "" + createComplaintRecyclerView);
        createComplaintRecyclerView.setAdapter(createComplaintAdapter);
        //Adding lbelow line to separate the data in the recycelrview
        //createComplaintRecyclerView.addItemDecoration(new DividerItemDecoration(createComplaintRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                createComplaintAdapter.clearCollection();
                Log.d("Complaint Fragment", "Complaint called");
                if (createComplaintAdapter != null)
                    createComplaintAdapter.clearCollection();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.d("data1", "" + dataSnapshot1.child("AssignedTo").getValue());
                    switch (userRoleAssign) {
                        case "Admin":
                            Log.d("WORKORDERLOG", "inside admin swith");
                            if (dataSnapshot1.child("AssignedTo").getValue() == null) {
                                Log.d("WORKORDERLOG", "onDataChange: first switch admin call");
                                CreateComplaintModel createComplaintModel = dataSnapshot1.getValue(CreateComplaintModel.class);
                                complaintList.add(0,createComplaintModel);
                            }
                            break;
                        case "Technician":
                            //assignButton.setEnabled(false);
                            Log.d(TAG, "check value: "+dataSnapshot1.child("AssignedTo").getValue());
                            if(dataSnapshot1.child("AssignedTo").getValue() == null && dataSnapshot1.getKey().substring(dataSnapshot1.getKey().indexOf("_")+1).
                                    equals(emailassign.substring(0, emailassign.indexOf("@")))){
                                CreateComplaintModel createComplaintModel = dataSnapshot1.getValue(CreateComplaintModel.class);
                                complaintList.add(0,createComplaintModel);
                            }
                            break;
                        case "User":

                            Log.d("WORKORDERLOG", "onDataChange: Indside user switch");
                            Log.d("Nikhil",""+dataSnapshot1.getKey().substring(dataSnapshot1.getKey().indexOf("_")+1).
                                    equals(emailassign.substring(0, emailassign.indexOf("@"))));
                            if (dataSnapshot1.child("AssignedTo").getValue() == null && dataSnapshot1.getKey().substring(dataSnapshot1.getKey().indexOf("_")+1).
                                    equals(emailassign.substring(0, emailassign.indexOf("@")))) {
                                Log.d("WORKORDERLOG", "onDataChange: third switch user call");
                                CreateComplaintModel createComplaintModel = dataSnapshot1.getValue(CreateComplaintModel.class);
                                //assignButton.setVisibility(View.GONE);
                                complaintList.add(0,createComplaintModel);
                                //assignButton.setVisibility(View.GONE);
                            }

                        default:
                            Log.d(TAG, "onDataChange: Nothing is called");
                            break;
                    }
                }
                    /*if (dataSnapshot1.child("AssignedTo").getValue() == null) {
                        CreateComplaintModel createComplaintModel = dataSnapshot1.getValue(CreateComplaintModel.class);
                        complaintList.add(createComplaintModel);
                    }*/

                createComplaintAdapter.setCreateComplaints(complaintList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        createComplaintRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }


    @OnClick(R.id.floating_Button_CreateComplaint_Fragement)
    public void onButtonClick(View view) {
        createCompButtonToAddComplaint = new Intent(context, AddCreateComplaint.class);
        startActivity(createCompButtonToAddComplaint);
    }

    class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
