package com.example.blogapp.Models;

import com.google.firebase.database.ServerValue;

public class Photo {
    private String postKey;
    private String picture;
    private String userid;
    private String userPhoto;
    private Object timeStamp;

    public Photo() {
    }

    public Photo(String picture, String userid, String userPhoto) {
//        this.postKey = postKey;
        this.picture = picture;
        this.userid = userid;
        this.userPhoto = userPhoto;
        this.timeStamp= ServerValue.TIMESTAMP;
    }

    public Photo(String postKey, String picture, String userid, String userPhoto, Object timeStamp) {
        this.postKey = postKey;
        this.picture = picture;
        this.userid = userid;
        this.userPhoto = userPhoto;
        this.timeStamp = timeStamp;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
