package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.daneshnaik.jobbyboy.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class profile extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation_profile=findViewById(R.id.meowbottomnav_profile);
        bottomNavigation_profile.show(1,true);
        bottomNavigation_profile.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation_profile.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_recommend_24));
        bottomNavigation_profile.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_chat_24));
        bottomNavigation_profile.add(new MeowBottomNavigation.Model(5,R.drawable.baseline_person_4_24));
    }
}