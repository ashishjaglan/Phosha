package com.example.blogapp.Models;

public class UserListItem {
    String userid;
    String userPhoto;
    String username;

    public UserListItem(String userid, String userPhoto, String username) {
        this.userid = userid;
        this.userPhoto = userPhoto;
        this.username=username;
    }


    public UserListItem() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
