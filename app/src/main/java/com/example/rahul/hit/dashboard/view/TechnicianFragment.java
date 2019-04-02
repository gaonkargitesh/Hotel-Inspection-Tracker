package com.example.rahul.hit.dashboard.view;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahul.hit.R;
import com.example.rahul.hit.util.TechnicianModel;
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
public class TechnicianFragment extends Fragment {


    @BindView(R.id.floating_Button_AddTechnicianPage_AddTechnician)
    FloatingActionButton addTechnician;

    DatabaseReference databaseReference;
    RecyclerView technicianListRecyclerView;
    ArrayList<TechnicianModel> technicianList;
    TechnicianAdapter technicianAdapter;


    Intent addTechnicianIntent;
    Context context;
    public TechnicianFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*databaseReference= FirebaseDatabase.getInstance().getReference().child("Technician");
        technicianList=new ArrayList<TechnicianModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                technicianAdapter.clearCollection();
                if(technicianAdapter!=null){
                    technicianAdapter.clearCollection();
                }
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    TechnicianModel technicianModel=dataSnapshot1.getValue(TechnicianModel.class);
                    technicianList.add(technicianModel);
                }
                technicianAdapter=new TechnicianAdapter(context,technicianList);
                technicianListRecyclerView.addItemDecoration(new DividerItemDecoration(technicianListRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

                technicianListRecyclerView.setAdapter(technicianAdapter);
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
        View view=inflater.inflate(R.layout.fragment_technician, container, false);
        ButterKnife.bind(this,view);
        context=getActivity();

        technicianListRecyclerView=view.findViewById(R.id.technicianList_RecyclerView);



        technicianList=new ArrayList<TechnicianModel>();
        technicianAdapter=new TechnicianAdapter(context,technicianList);
        technicianListRecyclerView.addItemDecoration(new DividerItemDecoration(technicianListRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        technicianListRecyclerView.setAdapter(technicianAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                technicianAdapter.clearCollection();
                if(technicianAdapter!=null){
                    technicianAdapter.clearCollection();
                }
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    TechnicianModel technicianModel=dataSnapshot1.getValue(TechnicianModel.class);
                    if(technicianModel.getRole().equals("Technician")){
                        technicianList.add(technicianModel);
                    }

                }
                technicianAdapter.setTechnician(technicianList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        technicianListRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    @OnClick(R.id.floating_Button_AddTechnicianPage_AddTechnician)
    public void onTextViewClick(View view){
        addTechnicianIntent=new Intent(context,AddTechnician.class);
        startActivity(addTechnicianIntent);
    }
}
