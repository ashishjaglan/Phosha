package com.example.blogapp.Models;

import com.google.firebase.database.ServerValue;

public class Comment {

    private String editComment, userid, userimg, userName;
    private Object timeStamp;

    public Comment(String editComment, String userid, String userimg, String userName) {
        this.editComment = editComment;
        this.userid = userid;
        this.userimg = userimg;
        this.userName = userName;

        this.timeStamp= ServerValue.TIMESTAMP;

    }

    public Comment(String editComment, String userid, String userimg, String userName, Object timeStamp) {
        this.editComment = editComment;
        this.userid = userid;
        this.userimg = userimg;
        this.userName = userName;
        this.timeStamp = timeStamp;
    }

    public Comment() {
    }

    public String getEditComment() {
        return editComment;
    }

    public void setEditComment(String editComment) {
        this.editComment = editComment;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
