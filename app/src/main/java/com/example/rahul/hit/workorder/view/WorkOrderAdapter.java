package com.example.rahul.hit.workorder.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintModel;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkOrderAdapter extends RecyclerView.Adapter<WorkOrderAdapter.WorkOrderViewHolder> {

    private Context context;
    ArrayList<WorkOrderModel> workOrderList;

    public WorkOrderAdapter(Context context,ArrayList<WorkOrderModel> workOrderList){
        this.context=context;
        this.workOrderList=workOrderList;

    }

    @NonNull
    @Override
    public WorkOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_workorder_list, parent, false);
        ButterKnife.bind(this, view);
        return new WorkOrderAdapter.WorkOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkOrderViewHolder workOrderViewHolder, int position) {

        final WorkOrderModel workOrderModel=workOrderList.get(position);


        workOrderViewHolder.title.setText(workOrderList.get(position).getTitle());
        workOrderViewHolder.description.setText(workOrderList.get(position).getDescription());
        workOrderViewHolder.priority.setText(workOrderList.get(position).getPriority());
        workOrderViewHolder.AssignedTo.setText(workOrderList.get(position).getAssignedTo());

        workOrderViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WorkOrderDetails.class);
                intent.putExtra("titledata",workOrderViewHolder.title.getText().toString());
                intent.putExtra("descriptiondata",workOrderViewHolder.description.getText().toString());
                intent.putExtra("prioritydata",workOrderViewHolder.priority.getText().toString());
                intent.putExtra("assignedToData",workOrderViewHolder.AssignedTo.getText().toString());
                intent.putExtra("Imagedata",workOrderModel.getImageUrl());

                Log.d("imagekey",""+workOrderModel.getImageUrl());

                context.startActivity(intent);
            }
        });

        Glide.with(context).load(workOrderList.get(position).getImageUrl()).into(workOrderViewHolder.complaint);
    }

    @Override
    public int getItemCount() {
        return workOrderList.size();
    }
    public void setCreateComplaints(ArrayList<WorkOrderModel> collection) {
        this.workOrderList = collection;
        notifyDataSetChanged();
    }

    public void clearCollection() {
        if (workOrderList != null) {
            workOrderList.clear();
            notifyDataSetChanged();
        }
    }

    class WorkOrderViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView_workOrder_list_compTitle)
        TextView title;

        @BindView(R.id.textView_workOrder_list_compDescription)
        TextView description;

        @BindView(R.id.textView_workOrder_list_compPriority)
        TextView priority;

        @BindView(R.id.imageVie_workOrder_list_compImage)
        ImageView complaint;

        @BindView(R.id.textView_workOrder_list_compAssignedTo)
        TextView AssignedTo;

        @BindView(R.id.work_Order_CardView_Container)
        CardView cardView;

        public WorkOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.textView_workOrder_list_compTitle);
            this.description = itemView.findViewById(R.id.textView_workOrder_list_compDescription);
            this.priority = itemView.findViewById(R.id.textView_workOrder_list_compPriority);
            this.complaint=itemView.findViewById(R.id.imageVie_workOrder_list_compImage);
            this.AssignedTo=itemView.findViewById(R.id.textView_workOrder_list_compAssignedTo);
            this.cardView=itemView.findViewById(R.id.work_Order_CardView_Container);

        }
    }
}
