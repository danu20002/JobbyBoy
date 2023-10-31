package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.daneshnaik.jobbyboy.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class mainscreen extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        bottomNavigation=findViewById(R.id.meowbottomnav);
        bottomNavigation.show(1,true);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_recommend_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_chat_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.baseline_person_4_24));
    }
}


