package com.example.rahul.hit.createcomplaint.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.MaskFilter;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.example.rahul.hit.constants.AppConstant;
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

public class CreateComplaintAdapter extends RecyclerView.Adapter<CreateComplaintAdapter.CreateComplaintViewHolder> {

    public static final int REQUEST_FOR_ACTIVITY_CODE = 1;

    private Intent assignTechToAssignTechList;

    CreateComplaintModel createComplaintModel;

    @BindView(R.id.button_complaint_list_assignComplaint)
    Button assignTech;


    private Context context;
    private final String userRoleAssign = AppConstant.BundleKey.userRole;
    final String emailassign = AppConstant.BundleKey.email;
    private ArrayList<CreateComplaintModel> createComplaints;

    public CreateComplaintAdapter(Context context, ArrayList<CreateComplaintModel> createComplaints) {
        this.context = context;
        this.createComplaints = createComplaints;
    }

    @NonNull
    @Override
    public CreateComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complaint_list, parent, false);
        ButterKnife.bind(this, view);
        return new CreateComplaintViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CreateComplaintViewHolder createComplaintViewHolder, int position) {

        CreateComplaintModel createComplaintModel = createComplaints.get(position);

        createComplaintViewHolder.title.setText(createComplaints.get(position).getTitle());
        createComplaintViewHolder.description.setText(createComplaints.get(position).getDescription());
        createComplaintViewHolder.priority.setText(createComplaints.get(position).getPriority());
        createComplaintViewHolder.status.setText(createComplaints.get(position).getStatus());

        if (userRoleAssign.equals("User")){
            createComplaintViewHolder.assigntechnician.setVisibility(View.GONE);
        }
        if(userRoleAssign.equals("Technician")){
            createComplaintViewHolder.assigntechnician.setVisibility(View.GONE);
        }
        if(userRoleAssign.equals("Admin")) {
            createComplaintViewHolder.assigntechnician.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    assignTechToAssignTechList = new Intent(context, TechnicianAssignList.class);
                    assignTechToAssignTechList.putExtra("ID", createComplaints.get(createComplaintViewHolder.getAdapterPosition()).getId());
                    //Toast.makeText(context, createComplaints.get(createComplaintViewHolder.getAdapterPosition()).getId(), Toast.LENGTH_LONG).show();
                    context.startActivity(assignTechToAssignTechList);
                }
            });
        }

        //hideView();


        Glide.with(context).load(createComplaints.get(position).getImageUrl()).into(createComplaintViewHolder.complaint);
        //Picasso.get().load(createComplaints.get(position).getImageUrl()).into(createComplaintViewHolder.complaint);
    }





    public void setCreateComplaints(ArrayList<CreateComplaintModel> collection) {
        this.createComplaints = collection;
        notifyDataSetChanged();
    }

    public void clearCollection() {
        if (createComplaints != null) {
            createComplaints.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return createComplaints.size();
    }

    class CreateComplaintViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_complaint_list_compTitle)
        TextView title;

        @BindView(R.id.textView_complaint_list_compDescription)
        TextView description;

        @BindView(R.id.textView_complaint_list_compPriority)
        TextView priority;
        @BindView(R.id.textView_complaint_list_compStatus)
        TextView status;

        @BindView(R.id.button_complaint_list_assignComplaint)
        Button assigntechnician;

        @BindView(R.id.imageView_complaint_list_compImage)
        ImageView complaint;

        @BindView(R.id.createComplaint_CardView_Container)
        CardView cardView;

        public CreateComplaintViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.textView_complaint_list_compTitle);
            this.description = itemView.findViewById(R.id.textView_complaint_list_compDescription);
            this.priority = itemView.findViewById(R.id.textView_complaint_list_compPriority);
            this.status=itemView.findViewById(R.id.textView_complaint_list_compStatus);
            this.complaint = itemView.findViewById(R.id.imageView_complaint_list_compImage);
            this.cardView = itemView.findViewById(R.id.createComplaint_CardView_Container);
            this.assigntechnician = itemView.findViewById(R.id.button_complaint_list_assignComplaint);
        }
    }
}
