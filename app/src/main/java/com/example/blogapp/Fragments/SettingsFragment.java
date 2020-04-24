package com.example.blogapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blogapp.Activities.Home;
import com.example.blogapp.Models.PostListItem;
import com.example.blogapp.Models.UserListItem;
import com.example.blogapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.mapbox.api.directions.v5.models.DirectionsResponse;
//import com.mapbox.geojson.Point;
//import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;
//import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
//
//import retrofit2.Call;
//import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    FirebaseUser currentUser;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        // Inflate the layout for this fragment

        FloatingActionButton floatingActionButton = ((Home) getActivity()).getFloatingActionButton();
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }


        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();


        final EditText eventcode = getActivity().findViewById(R.id.eventCode);
        Button joinbtn = getActivity().findViewById(R.id.joinBtn);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String eventCode = eventcode.getText().toString();

                if (eventCode.isEmpty()) {
                    Toast.makeText(getContext(), "Enter a code", Toast.LENGTH_SHORT).show();
                } else{

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Posts");

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(eventCode).exists()) {

                                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                                DatabaseReference myRef2 = database2.getReference("Users's_post_list").child(currentUser.getUid()).push();
                                String key2 = myRef2.getKey();
                                PostListItem postListItem = new PostListItem(eventCode);
                                myRef2.setValue(postListItem);


                                FirebaseDatabase database3 = FirebaseDatabase.getInstance();
                                DatabaseReference myRef3 = database3.getReference("Post's_user_list").child(eventCode).push();
                                String key3 = myRef3.getKey();
                                UserListItem userListItem = new UserListItem(currentUser.getUid());
                                myRef3.setValue(userListItem);

                                eventcode.setText("");

                                Toast.makeText(getActivity(), "Event joined", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(getActivity(), "Invalid code", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });

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
