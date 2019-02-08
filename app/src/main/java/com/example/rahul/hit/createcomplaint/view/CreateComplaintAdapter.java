package com.example.rahul.hit.createcomplaint.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rahul.hit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;

public class CreateComplaintAdapter extends RecyclerView.Adapter<CreateComplaintAdapter.CreateComplaintViewHolder> {


    Context context;
    ArrayList<CreateComplaintModel> createComplaints;

    public CreateComplaintAdapter(Context context,ArrayList<CreateComplaintModel> createComplaints){

        this.context=context;
        this.createComplaints=createComplaints;
    }

    @NonNull
    @Override
    public CreateComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CreateComplaintViewHolder(LayoutInflater.from(context).inflate(R.layout.create_complaint_cardview_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateComplaintViewHolder createComplaintViewHolder, int position) {

        CreateComplaintModel createComplaintModel=createComplaints.get(position);

        createComplaintViewHolder.title.setText(createComplaints.get(position).getTitle());
        createComplaintViewHolder.description.setText(createComplaints.get(position).getDescription());
        createComplaintViewHolder.priority.setText(createComplaints.get(position).getPriority());

        Picasso.get().load(createComplaints.get(position).getImageUrl()).into(createComplaintViewHolder.complaint);
    }

    @Override
    public int getItemCount() {
        return createComplaints.size();
    }

    class CreateComplaintViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.complaint_Title)
        TextView title;

        @BindView(R.id.complaint_Description)
        TextView description;

        @BindView(R.id.complaint_Priority)
        TextView priority;

        @BindView(R.id.complaint_Image)
        ImageView complaint;

        public CreateComplaintViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title=itemView.findViewById(R.id.complaint_Title);
            this.description=itemView.findViewById(R.id.complaint_Description);
            this.priority=itemView.findViewById(R.id.complaint_Priority);
            this.complaint=itemView.findViewById(R.id.complaint_Image);

        }
    }

}
