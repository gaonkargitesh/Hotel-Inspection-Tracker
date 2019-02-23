package com.example.rahul.hit.technician.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.R;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter;
import com.example.rahul.hit.util.TechnicianModel;
import com.example.rahul.hit.workorder.view.WorkorderFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter.REQUEST_FOR_ACTIVITY_CODE;

public class TechnicianAssignList extends AppCompatActivity {
    Toolbar toolbar;

    Context context;



    RecyclerView assignTechnicianRecyclerView;
    DatabaseReference databaseReference;
    ArrayList<TechnicianModel> assignTechnicianList;
    TechnicianAssignAdapter technicianAssignAdapter;

    TextView name;
    TextView email;

    private BroadcastReceiver receiver;



    @BindView(R.id.assign_technician_list_linearLayout)
    LinearLayout linearLayout;


    public TechnicianAssignList(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_assign_list);


        context=this;
        toolbar = findViewById(R.id.assign_technician_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        //toolbar.setTitle("Assign Technician");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        assignTechnicianRecyclerView=findViewById(R.id.assign_technician_RecyclerView);
        assignTechnicianRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        assignTechnicianList=new ArrayList<TechnicianModel>();
        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Technician");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    TechnicianModel technicianModel=dataSnapshot1.getValue(TechnicianModel.class);
                    assignTechnicianList.add(technicianModel);
                }
                technicianAssignAdapter=new TechnicianAssignAdapter(TechnicianAssignList.this,assignTechnicianList,ID);
                assignTechnicianRecyclerView.addItemDecoration(new DividerItemDecoration(assignTechnicianRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

                assignTechnicianRecyclerView.setAdapter(technicianAssignAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



}
