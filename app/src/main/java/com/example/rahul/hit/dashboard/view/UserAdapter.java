package com.example.rahul.hit.dashboard.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahul.hit.R;
import com.example.rahul.hit.util.Users;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    Context context;
    ArrayList<Users> userList;

    public UserAdapter(Context context,ArrayList<Users> userList){
        this.context=context;
        this.userList=userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder((LayoutInflater.from(context).inflate(R.layout.dashboard_user_recyclerview_list,parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {

        Users users=userList.get(position);

        userViewHolder.mFirstName.setText(userList.get(position).getFirstname());
        userViewHolder.mLastName.setText(userList.get(position).getLastname());
        userViewHolder.mEmail.setText(userList.get(position).getEmail());
        userViewHolder.mRoomNo.setText(userList.get(position).getRoomno());
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

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            this.mFirstName=itemView.findViewById(R.id.textView_userList_firstName);
            this.mLastName=itemView.findViewById(R.id.textView_userList_lastName);
            this.mEmail=itemView.findViewById(R.id.textView_userList_email);
            this.mRoomNo=itemView.findViewById(R.id.textView_userList_roomNo);
        }
    }
}
