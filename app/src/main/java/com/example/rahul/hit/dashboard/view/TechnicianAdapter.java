package com.example.rahul.hit.dashboard.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.rahul.hit.R;
import com.example.rahul.hit.createcomplaint.view.CreateComplaintModel;
import com.example.rahul.hit.util.TechnicianModel;

import java.util.ArrayList;

public class TechnicianAdapter extends RecyclerView.Adapter<TechnicianAdapter.TechnicianViewHolder> {

    private Context context;
    ArrayList<TechnicianModel> technicianList;
    ColorGenerator generator;

    public TechnicianAdapter(Context context, ArrayList<TechnicianModel> technicianList){
        this.context=context;
        this.technicianList=technicianList;
        this.generator=ColorGenerator.MATERIAL;
    }

    @NonNull
    @Override
    public TechnicianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TechnicianViewHolder(LayoutInflater.from(context).inflate(R.layout.dashboard_technician_recyclerview_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicianViewHolder technicianViewHolder, int position) {

        TechnicianModel technicianModel=technicianList.get(position);
        String name = technicianList.get(position).getName();
        String firstLetter = name.substring(0,1);
        Log.d("tech firstletter",""+firstLetter);

        technicianViewHolder.mName.setText(technicianList.get(position).getName());
        technicianViewHolder.mEmail.setText(technicianList.get(position).getEmail());
        technicianViewHolder.mPhoneNo.setText(technicianList.get(position).getPhoneNo());
        technicianViewHolder.mJobProfile.setText(technicianList.get(position).getJobPro());


        TextDrawable drawable=TextDrawable.builder().buildRound(firstLetter,generator.getColor(technicianList.get(position).getName()));
        technicianViewHolder.imageView.setImageDrawable(drawable);

    }

    @Override
    public int getItemCount() {
        return technicianList.size();
    }

    class TechnicianViewHolder extends RecyclerView.ViewHolder{

        TextView mName;
        TextView mEmail;
        TextView mPhoneNo;
        TextView mJobProfile;
        ImageView imageView;

        public TechnicianViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mName=itemView.findViewById(R.id.textView_TechnicianList_TechName);
            this.mEmail=itemView.findViewById(R.id.textView_TechnicianList_TechEmail);
            this.mPhoneNo=itemView.findViewById(R.id.textView_TechnicianList_TechPhoneNo);
            this.mJobProfile=itemView.findViewById(R.id.textView_TechnicianList_TechJobProfile);
            this.imageView=itemView.findViewById(R.id.imageView_TechnicianList_FirstLetter);
        }
    }
}
