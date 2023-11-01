package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.daneshnaik.jobbyboy.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Chat extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation_chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bottomNavigation_chat=findViewById(R.id.meowbottomnav_chat);
        bottomNavigation_chat.show(3,true);
        bottomNavigation_chat.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation_chat.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_recommend_24));
        bottomNavigation_chat.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_chat_24));
        bottomNavigation_chat.add(new MeowBottomNavigation.Model(5,R.drawable.baseline_person_4_24));

    }
}