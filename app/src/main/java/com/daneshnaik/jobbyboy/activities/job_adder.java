package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;

import com.daneshnaik.jobbyboy.R;
import com.google.android.material.textfield.TextInputEditText;

public class job_adder extends AppCompatActivity {
TextInputEditText companyname_jobadder,location_jobadder,jobrole_jobadder,branch_jobadder,jobdescription_jobadder,packageamount_jobadder,link_jobadder,lastdate_jobadder;
AppCompatButton upload_btn_jobadder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_adder);


    }
}