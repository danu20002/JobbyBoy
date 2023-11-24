package com.daneshnaik.jobbyboy.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daneshnaik.jobbyboy.R;
import com.daneshnaik.jobbyboy.adapters.job_adapter;
import com.daneshnaik.jobbyboy.classes.job_details;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class main_screen_frag extends Fragment {

  FloatingActionButton floatingActionButton_mainfrag;
  RecyclerView recyclerView_main;
  FirebaseAuth auth;
  FirebaseDatabase database;
    job_adapter adapter;
    ArrayList<job_details> details;
    int id =0;
    LottieAnimationView lottiloader;
    private AdView mAdView;

    public main_screen_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_main_screen_frag, container, false);
        floatingActionButton_mainfrag=view.findViewById(R.id.floating_btn_main);
        recyclerView_main=view.findViewById(R.id.recyclerview_mainscreenfrag);
        lottiloader=view.findViewById(R.id.loading_main_frag);
         lottiloader.setVisibility(View.VISIBLE);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        database.getReference().child("Job_Apply").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    job_details detailing=dataSnapshot.getValue(job_details.class);
                    details.add(detailing);
                    lottiloader.setVisibility(View.INVISIBLE);
                    if(snapshot.exists())
                    {
                        id=(int) snapshot.getChildrenCount();
                    }
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              lottiloader.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Please Try after some time", Toast.LENGTH_SHORT).show();
            }
        });


        details=new ArrayList<>();
        adapter=new job_adapter(getContext(),details);

        recyclerView_main.setAdapter(adapter);
        recyclerView_main.smoothScrollToPosition(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView_main.setLayoutManager(layoutManager);


        floatingActionButton_mainfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), job_adder.class));
            }
        });

        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }
}