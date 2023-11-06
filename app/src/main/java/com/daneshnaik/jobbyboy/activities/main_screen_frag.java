package com.daneshnaik.jobbyboy.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daneshnaik.jobbyboy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class main_screen_frag extends Fragment {

  FloatingActionButton floatingActionButton_mainfrag;

    public main_screen_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_main_screen_frag, container, false);
        floatingActionButton_mainfrag=view.findViewById(R.id.floating_btn_main);
        floatingActionButton_mainfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), job_adder.class));
            }
        });




        return view;
    }
}