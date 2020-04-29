package com.example.blogapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.blogapp.Models.UserListItem;
import com.example.blogapp.R;

import java.util.List;

public class UserlistAdapter extends RecyclerView.Adapter<UserlistAdapter.UserViewHolder>{
    private Context mContext;
    private List<UserListItem> mData;

    public UserlistAdapter(Context mContext, List<UserListItem> mData){
        this.mContext=mContext;
        this.mData=mData;
        }

    public UserlistAdapter.UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View row= LayoutInflater.from(mContext).inflate(R.layout.row_userlist_item,viewGroup,false);
        return new UserlistAdapter.UserViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

        userViewHolder.usrname.setText(mData.get(i).getUsername());
        Glide.with(mContext).load(mData.get(i).getUserPhoto()).into(userViewHolder.userpic);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usrname;
        ImageView userpic;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            usrname=itemView.findViewById(R.id.usrname);
            userpic=itemView.findViewById(R.id.userpic);
        }
    }
}
