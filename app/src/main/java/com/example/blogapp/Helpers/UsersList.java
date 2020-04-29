package com.example.blogapp.Helpers;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blogapp.Adapters.UserlistAdapter;
import com.example.blogapp.Models.UserListItem;
import com.example.blogapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersList extends AppCompatActivity {

    String PostKey, PostTitle;
    TextView posttitle;
    RecyclerView userRV;
    DatabaseReference databaseReference;
    ArrayList<UserListItem> users;
    UserlistAdapter userlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        getSupportActionBar().hide();

        PostKey=getIntent().getExtras().getString("postKey");
        PostTitle=getIntent().getExtras().getString("title");

        posttitle=findViewById(R.id.post_title);
        posttitle.setText(PostTitle);
        setUpUserList();
    }

    private void setUpUserList() {

        userRV=findViewById(R.id.userRV);
        userRV.setHasFixedSize(true);
        userRV.setLayoutManager(new LinearLayoutManager(this));

        databaseReference= FirebaseDatabase.getInstance().getReference("Post's_user_list").child(PostKey);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users=new ArrayList<>();
                for(DataSnapshot usersnap: dataSnapshot.getChildren()){
                    UserListItem userListItem=usersnap.getValue(UserListItem.class);
                    users.add(userListItem);
                }
                userlistAdapter=new UserlistAdapter(getApplicationContext(), users);
                userRV.setAdapter(userlistAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UsersList.this, "Database Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
