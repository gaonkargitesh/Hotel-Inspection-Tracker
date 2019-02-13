package com.example.rahul.hit.technician.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahul.hit.BaseActivity;
import com.example.rahul.hit.MainActivity;
import com.example.rahul.hit.R;
import com.example.rahul.hit.dashboard.view.AddTechnician;
import com.example.rahul.hit.util.TechnicianModel;
import com.example.rahul.hit.workorder.view.WorkorderFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter.REQUEST_FOR_ACTIVITY_CODE;

public class TechnicianAssignAdapter extends RecyclerView.Adapter<TechnicianAssignAdapter.AssignTechnicianViewHolder> {

    /*@BindView(R.id.assign_technician_list_linearLayout)
    LinearLayout linearLayout;*/

    private Context mcontext;
    private ArrayList<TechnicianModel> massignTechnicianList;

    TechnicianAssignAdapter(Context context, ArrayList<TechnicianModel> assignTechnicianList) {
        mcontext = context;
        massignTechnicianList = assignTechnicianList;
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
    public void onBindViewHolder(@NonNull AssignTechnicianViewHolder assignTechnicianViewHolder, int position) {

        TechnicianModel technicianModel = massignTechnicianList.get(position);

        assignTechnicianViewHolder.name.setText(massignTechnicianList.get(position).getName());
        assignTechnicianViewHolder.email.setText(massignTechnicianList.get(position).getEmail());

        assignTechnicianViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity) v.getContext();
                Fragment fragment=new WorkorderFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.assign_technician_list_linearLayout,fragment).commit();
                Toast.makeText(mcontext, "clicked", Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return massignTechnicianList.size();
    }



    class AssignTechnicianViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        LinearLayout linearLayout;

        public AssignTechnicianViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.textView_AssignTechnician_Name);
            this.email = itemView.findViewById(R.id.textView_AssignTechnician_Email);
            this.linearLayout = itemView.findViewById(R.id.assign_technician_list_linearLayout);
        }
    }


}
