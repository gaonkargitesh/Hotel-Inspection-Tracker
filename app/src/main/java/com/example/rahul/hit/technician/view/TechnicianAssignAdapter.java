package com.example.rahul.hit.technician.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.MainActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.dashboard.view.AddTechnician;
import com.example.rahul.hit.util.TechnicianModel;
import com.example.rahul.hit.workorder.view.WorkorderFragment;
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

public class TechnicianAssignAdapter extends RecyclerView.Adapter<TechnicianAssignAdapter.AssignTechnicianViewHolder> {

    @BindView(R.id.assign_technician_list_relativeLayout)
    RelativeLayout relativeLayout;

    ColorGenerator generator;

    TextView name;
    TextView email;


    private Context mcontext;
    private ArrayList<TechnicianModel> massignTechnicianList;
    String mID;

    TechnicianAssignAdapter(Context context, ArrayList<TechnicianModel> assignTechnicianList, String ID) {
        mcontext = context;
        massignTechnicianList = assignTechnicianList;
        mID = ID;
        this.generator = ColorGenerator.DEFAULT;
    }

    @NonNull
    @Override
    public AssignTechnicianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.assign_technician, parent, false);
        ButterKnife.bind(this, view);
        return new AssignTechnicianViewHolder(view);
        //return new AssignTechnicianViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.assign_technician,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull final AssignTechnicianViewHolder assignTechnicianViewHolder, int position) {

        final TechnicianModel technicianModel = massignTechnicianList.get(position);

        String name = massignTechnicianList.get(position).getName();
        String firstLetter = name.substring(0, 1).toUpperCase();

        assignTechnicianViewHolder.name.setText(massignTechnicianList.get(position).getName());
        assignTechnicianViewHolder.email.setText(massignTechnicianList.get(position).getEmail());

        TextDrawable drawable = TextDrawable.builder().buildRound(firstLetter, generator.getColor(massignTechnicianList.get(position).getName()));
        assignTechnicianViewHolder.profileImage.setImageDrawable(drawable);

        assignTechnicianViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Create Complaint");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            /*String datasnapshotKey = dataSnapshot1.getKey();
                            String idFromDatabase = datasnapshotKey.substring(0,datasnapshotKey.indexOf("_"));
                            String id =*/
                            String compkey = dataSnapshot1.getKey();
                            String datasnapshotKey = dataSnapshot1.getKey();
                            String idFromDatabase = datasnapshotKey.substring(0, datasnapshotKey.indexOf("_"));
                            Log.d("idfromdatabase", "" + idFromDatabase);
                            if (idFromDatabase.equals(mID)) {
                                String techEmail = technicianModel.getEmail();
                                reference.child(compkey).child("AssignedTo").setValue(techEmail);
                                reference.child(compkey).child("status").setValue("In Progress");
                            }
                            Log.d("firstkey", "" + compkey);

                            if (reference.child(compkey).child("AssignedTo") != null) {
                            }
                            ((Activity) mcontext).finish();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        /*assignTechnicianViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkorderFragment myFragment = new WorkorderFragment();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                activity.getSupportFragmentManager().beginTransaction().add(R.id.insideContainer_Workorder, myFragment).addToBackStack(null).commit();
            }
        });*/
    }

    /*@OnClick(R.id.assign_technician_list_linearLayout)
    public void OnClickLayout(View view) {
        Toast.makeText(mcontext, "clicked", Toast.LENGTH_SHORT).show();
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        WorkorderFragment myFragment = new WorkorderFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.work_order_fragment_container, myFragment).addToBackStack(null).commit();
        Fragment fragment =new Fragment();
        Intent intent=new Intent(mcontext.getApplicationContext(),WorkorderFragment.class);
        //((Activity)mcontext).startActivityForResult(intent,REQUEST_FOR_ACTIVITY_CODE);
        fragment.getActivity().startActivityForResult(intent,REQUEST_FOR_ACTIVITY_CODE);
    }*/



    /*public void broadcastIntent(View view){

        Intent intent=new Intent("myINtenet");
        intent.setAction("com.example.rahul.hit.MyIntent");
        mcontext.sendBroadcast(intent);

    }*/


    @Override
    public int getItemCount() {
        return massignTechnicianList.size();
    }


    public void setTechnician(ArrayList<TechnicianModel> collection) {
        this.massignTechnicianList = collection;
        notifyDataSetChanged();
    }

    public void clearCollection() {
        if (massignTechnicianList != null) {
            massignTechnicianList.clear();
            notifyDataSetChanged();
        }
    }


    class AssignTechnicianViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        ImageView profileImage;
        RelativeLayout relativeLayout;

        public AssignTechnicianViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.textView_AssignTechnician_Name);
            this.email = itemView.findViewById(R.id.textView_AssignTechnician_Email);
            this.profileImage = itemView.findViewById(R.id.imageView_AssignTechnician_Image);
            this.relativeLayout = itemView.findViewById(R.id.assign_technician_list_relativeLayout);
        }
    }
}
