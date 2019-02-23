package com.example.rahul.hit.workorder.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(@NonNull WorkOrderViewHolder workOrderViewHolder, int position) {

        WorkOrderModel workOrderModel=workOrderList.get(position);

        workOrderViewHolder.title.setText(workOrderList.get(position).getTitle());
        workOrderViewHolder.description.setText(workOrderList.get(position).getDescription());
        workOrderViewHolder.priority.setText(workOrderList.get(position).getPriority());

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



        public WorkOrderViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.textView_workOrder_list_compTitle);
            this.description = itemView.findViewById(R.id.textView_workOrder_list_compDescription);
            this.priority = itemView.findViewById(R.id.textView_workOrder_list_compPriority);
            this.complaint=itemView.findViewById(R.id.imageVie_workOrder_list_compImage);
        }
    }
}
