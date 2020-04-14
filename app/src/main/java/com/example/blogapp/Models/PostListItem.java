package com.example.blogapp.Models;

public class PostListItem {
    String postid;

    public PostListItem(String postid) {
        this.postid = postid;
    }

    public PostListItem() {
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
