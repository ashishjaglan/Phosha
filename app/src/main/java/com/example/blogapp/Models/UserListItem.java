package com.example.blogapp.Models;

public class UserListItem {
    String userid;
    String userPhoto;
    String usesrname;

    public UserListItem(String userid, String userPhoto, String username) {
        this.userid = userid;
        this.userPhoto = userPhoto;
        this.usesrname=username;
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

    public String getUsesrname() {
        return usesrname;
    }

    public void setUsesrname(String usesrname) {
        this.usesrname = usesrname;
    }
}
