package com.example.blogapp.Models;

public class UserListItem {
    String userid;

    public UserListItem(String userid) {
        this.userid = userid;
    }

    public UserListItem() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
