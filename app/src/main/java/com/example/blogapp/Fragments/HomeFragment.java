package com.example.blogapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.blogapp.Activities.Home;
import com.example.blogapp.Activities.RegisterActivity;
import com.example.blogapp.Adapters.PostAdapter;
import com.example.blogapp.Models.Post;
import com.example.blogapp.Models.PostListItem;
import com.example.blogapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView postRecyclerView;
    PostAdapter postAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference2;
    FirebaseUser fuser;
    List<Post> postList;
    List<PostListItem> userPostlist;
    Parcelable mListState;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        // Inflate the layout for this fragment

        FloatingActionButton floatingActionButton = ((Home) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.show();
        }
        View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        postRecyclerView=fragmentView.findViewById(R.id.postRV);
        postRecyclerView.setHasFixedSize(true);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        firebaseDatabase=FirebaseDatabase.getInstance();
//        databaseReference=firebaseDatabase.getReference("Posts");
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference2=FirebaseDatabase.getInstance().getReference("Users's_post_list").child(fuser.getUid());
        return fragmentView ;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userPostlist = new ArrayList<>();
                postList=new ArrayList<>();
                for(DataSnapshot postsnap: dataSnapshot.getChildren()){
                    PostListItem postListItem = postsnap.getValue(PostListItem.class);
                    userPostlist.add(postListItem);
                }
                update(userPostlist);

            }


            void update(final List<PostListItem> userPostlist) {

                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Posts");
                for(PostListItem i : userPostlist){
                    //DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Posts").child(i.getPostid());

                    databaseReference.child(i.getPostid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Post post=dataSnapshot.getValue(Post.class);
                            postList.add(post);
                            if(postList.size()==userPostlist.size()){
                                postAdapter=new PostAdapter(getActivity(),postList);
                                postRecyclerView.setAdapter(postAdapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "database error", Toast.LENGTH_SHORT).show();
            }
        });


//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                postList=new ArrayList<>();
//                for(DataSnapshot postsnap: dataSnapshot.getChildren()){
//                    Post post=postsnap.getValue(Post.class);
//                    postList.add(post);
//                }
//
//                postAdapter=new PostAdapter(getActivity(),postList);
//                postRecyclerView.setAdapter(postAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
