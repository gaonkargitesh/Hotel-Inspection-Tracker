package com.example.rahul.hit.workorder.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.example.rahul.hit.constants.AppConstant;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintAdapter;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintModel;

import org.w3c.dom.Text;

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

        if(workOrderList.get(position).getStatus().equals("In Progress")){
            workOrderViewHolder.status.setText(workOrderList.get(position).getStatus());
            workOrderViewHolder.status.setHintTextColor(Color.YELLOW);
            workOrderViewHolder.statusLabel.setTextColor(Color.YELLOW);
        }

        if(workOrderList.get(position).getStatus().equals("Completed")){
            workOrderViewHolder.status.setText(workOrderList.get(position).getStatus());

            workOrderViewHolder.statusLabel.setHintTextColor(Color.parseColor("#7FFF00"));
            workOrderViewHolder.status.setTextColor(Color.parseColor("#7FFF00"));

            workOrderViewHolder.cardView.setCardBackgroundColor(Color.parseColor("#DCDCDC"));
            //workOrderViewHolder.complaint.setColorFilter(Color.LTGRAY);
            workOrderViewHolder.complaint.setColorFilter(Color.parseColor("#DCDCDC"), PorterDuff.Mode.LIGHTEN);
            workOrderViewHolder.cardView.setClickable(false);
            workOrderViewHolder.cardView.setEnabled(false);
        }

        Log.d("sat", "onBindViewHolder: "+workOrderModel.getStatus());

        workOrderViewHolder.status.setText(workOrderList.get(position).getStatus());

        Log.d("msg", "onBindViewHolder: "+workOrderList.get(position).getStatus());

        if(AppConstant.BundleKey.userRole.equals("User")){
            workOrderViewHolder.cardView.setClickable(false);
        }
        else {
            workOrderViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,WorkOrderDetails.class);
                    intent.putExtra("titledata",workOrderViewHolder.title.getText().toString());
                    intent.putExtra("descriptiondata",workOrderViewHolder.description.getText().toString());
                    intent.putExtra("prioritydata",workOrderViewHolder.priority.getText().toString());
                    intent.putExtra("assignedToData",workOrderViewHolder.AssignedTo.getText().toString());
                    intent.putExtra("Imagedata",workOrderModel.getImageUrl());

                    intent.putExtra("ID",workOrderModel.getId());
                    //Toast.makeText(context, ""+workOrderModel.getId(), Toast.LENGTH_SHORT).show();
                    Log.d("imagekey",""+workOrderModel.getImageUrl());

                    context.startActivity(intent);
                }
            });
        }


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

        @BindView(R.id.textView_workOrder_list_workOrderStatus)
        TextView status;

        @BindView(R.id.textView_workOrder_list_workOrderStatusLabel)
        TextView statusLabel;


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
            this.status=itemView.findViewById(R.id.textView_workOrder_list_workOrderStatus);
            this.statusLabel=itemView.findViewById(R.id.textView_workOrder_list_workOrderStatusLabel);
        }
    }
}
