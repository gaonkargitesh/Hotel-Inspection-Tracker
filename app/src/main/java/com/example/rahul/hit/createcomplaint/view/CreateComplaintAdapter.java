package com.example.rahul.hit.createcomplaint.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.MaskFilter;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.example.rahul.hit.technician.view.TechnicianAssignList;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateComplaintAdapter extends RecyclerView.Adapter<CreateComplaintAdapter.CreateComplaintViewHolder> {

    public static final int REQUEST_FOR_ACTIVITY_CODE=1;

    private Intent assignTechToAssignTechList;
    @BindView(R.id.button_complaint_list_assignComplaint)
    Button assignTech;

    private Context context;
    private ArrayList<CreateComplaintModel> createComplaints;

    public CreateComplaintAdapter(Context context,ArrayList<CreateComplaintModel> createComplaints){

        this.context=context;
        this.createComplaints=createComplaints;
    }

    @NonNull
    @Override
    public CreateComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_complaint_list,parent,false);
        ButterKnife.bind(this,view);
        return new CreateComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateComplaintViewHolder createComplaintViewHolder, int position) {

        CreateComplaintModel createComplaintModel=createComplaints.get(position);

        createComplaintViewHolder.title.setText(createComplaints.get(position).getTitle());
        createComplaintViewHolder.description.setText(createComplaints.get(position).getDescription());
        createComplaintViewHolder.priority.setText(createComplaints.get(position).getPriority());
        /*createComplaintViewHolder.assignTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignTechToAssignTechList=new Intent(context,TechnicianAssignList.class);
                context.startActivity(assignTechToAssignTechList);
            }
        });*/

        Glide.with(context).load(createComplaints.get(position).getImageUrl()).into(createComplaintViewHolder.complaint);
        //Picasso.get().load(createComplaints.get(position).getImageUrl()).into(createComplaintViewHolder.complaint);
    }



    @OnClick(R.id.button_complaint_list_assignComplaint)
    public void OnClick(View view){
        assignTechToAssignTechList=new Intent(context,TechnicianAssignList.class);
        ((Activity)context).startActivityForResult(assignTechToAssignTechList,REQUEST_FOR_ACTIVITY_CODE);
        //context.startActivity(assignTechToAssignTechList);
    }



    @Override
    public int getItemCount() {
        return createComplaints.size();
    }

    class CreateComplaintViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView_complaint_list_compTitle)
        TextView title;

        @BindView(R.id.textView_complaint_list_compDescription)
        TextView description;

        @BindView(R.id.textView_complaint_list_compPriority)
        TextView priority;

        /*@BindView(R.id.materialButton_complaint_list_assignComplaint)
        MaterialButton assignTechnician;*/

        @BindView(R.id.imageView_complaint_list_compImage)
        ImageView complaint;

        public CreateComplaintViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title=itemView.findViewById(R.id.textView_complaint_list_compTitle);
            this.description=itemView.findViewById(R.id.textView_complaint_list_compDescription);
            this.priority=itemView.findViewById(R.id.textView_complaint_list_compPriority);
            this.complaint=itemView.findViewById(R.id.imageView_complaint_list_compImage);
            //this.assignTechnician=itemView.findViewById(R.id.materialButton_complaint_list_assignComplaint);


        }
    }

}
