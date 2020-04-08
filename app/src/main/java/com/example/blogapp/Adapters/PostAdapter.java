package com.example.blogapp.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.blogapp.Activities.PostDetailActivity;
import com.example.blogapp.Models.Post;
import com.example.blogapp.R;

import java.util.List;
import java.util.zip.Inflater;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context mContext;
    List<Post> mData;

    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View row= LayoutInflater.from(mContext).inflate(R.layout.row_post_item,viewGroup,false);

        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.tvTitle.setText(mData.get(i).getTitle());
        Glide.with(mContext).load(mData.get(i).getPicture()).into(myViewHolder.postImg);
        Glide.with(mContext).load(mData.get(i).getUserPhoto()).into(myViewHolder.postProfilePic);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView postImg;
        ImageView postProfilePic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.row_post_title);
            postImg=itemView.findViewById(R.id.row_post_image);
            postProfilePic=itemView.findViewById(R.id.row_post_profile);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position=getAdapterPosition();

                    postDetailActivity.putExtra("title", mData.get(position).getTitle());
                    postDetailActivity.putExtra("description", mData.get(position).getDescription());
                    postDetailActivity.putExtra("postImg",mData.get(position).getPicture());
                    postDetailActivity.putExtra("postKey",mData.get(position).getPostKey());
                    postDetailActivity.putExtra("userPic", mData.get(position).getUserPhoto());

                    long timestamp=(long)mData.get(position).getTimeStamp();
                    postDetailActivity.putExtra("postDate",timestamp);

                    Pair[] pairs = new Pair[3];
                    pairs[0]=new Pair<View, String>(postImg,"blog_img_transition");
                    pairs[1]=new Pair<View, String>(tvTitle,"blog_title_transition");
                    pairs[2]=new Pair<View, String>(postProfilePic,"user_img_transition");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, pairs);

                    mContext.startActivity(postDetailActivity, options.toBundle());
                }
            });
        }
    }
}
