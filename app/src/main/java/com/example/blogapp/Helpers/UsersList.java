package com.example.blogapp.Helpers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.blogapp.R;

public class UsersList extends AppCompatActivity {

    String PostKey, PostTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        PostKey=getIntent().getExtras().getString("postKey");
        PostTitle=getIntent().getExtras().getString("title");
    }
}
