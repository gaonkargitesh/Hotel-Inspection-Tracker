package com.example.rahul.hit.dashboard.view;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
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
import com.example.rahul.hit.util.Users;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    ColorGenerator generator;
    Context context;
    ArrayList<Users> userList;

    public UserAdapter(Context context,ArrayList<Users> userList){
        this.context=context;
        this.userList=userList;
        this.generator= ColorGenerator.DEFAULT;
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_user_recyclerview_list,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {

        Users users=userList.get(position);

        String name = userList.get(position).getFirstname() + "" + userList.get(position).getLastname();
        String firstLetter = name.substring(0,1).toUpperCase();
        Log.d("Firstletter1",""+firstLetter);

        userViewHolder.mFirstName.setText(userList.get(position).getFirstname());
        userViewHolder.mLastName.setText(userList.get(position).getLastname());
        userViewHolder.mEmail.setText(userList.get(position).getEmail());
        userViewHolder.mRoomNo.setText(userList.get(position).getRoomno());

        TextDrawable drawable=TextDrawable.builder().buildRound(firstLetter, generator.getColor(userList.get(position).getEmail()));
        userViewHolder.userImageView.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends  RecyclerView.ViewHolder{

        TextView mFirstName;
        TextView mLastName;
        TextView mEmail;
        TextView mRoomNo;
        ImageView userImageView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mFirstName=itemView.findViewById(R.id.textView_userList_firstName);
            this.mLastName=itemView.findViewById(R.id.textView_userList_lastName);
            this.mEmail=itemView.findViewById(R.id.textView_userList_email);
            this.mRoomNo=itemView.findViewById(R.id.textView_userList_roomNo);
            this.userImageView=itemView.findViewById(R.id.imageView_userList_firstLetter);
        }
    }
}
