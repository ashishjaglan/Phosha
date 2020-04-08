package com.example.blogapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.blogapp.Models.Comment;
import com.example.blogapp.R;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context mContext;
    private List<Comment> mData;

    public CommentAdapter(Context mContext, List<Comment> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row= LayoutInflater.from(mContext).inflate(R.layout.row_comment,viewGroup,false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder commentViewHolder, int i) {

        commentViewHolder.cUsername.setText(mData.get(i).getUserName());
        commentViewHolder.cdata.setText(mData.get(i).getEditComment());
        commentViewHolder.cDate.setText(timeStamptoString((Long)mData.get(i).getTimeStamp()));
        Glide.with(mContext).load(mData.get(i).getUserimg()).into(commentViewHolder.cUserImg);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView cUsername, cdata, cDate;
        ImageView cUserImg;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            cUsername=itemView.findViewById(R.id.cmt_user_name);
            cdata=itemView.findViewById(R.id.cmt_text);
            cDate=itemView.findViewById(R.id.cmt_date);
            cUserImg=itemView.findViewById(R.id.cmt_user_img);


        }
    }

    private String timeStamptoString(long time){
        Calendar calendar=Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);

        String date= DateFormat.format("hh:mm",calendar).toString();
        return date;
    }

}
