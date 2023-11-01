package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.daneshnaik.jobbyboy.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Recommend extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation_recommned;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        bottomNavigation_recommned=findViewById(R.id.meowbottomnav_recommend);



        bottomNavigation_recommned.show(2,true);
        bottomNavigation_recommned.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation_recommned.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_recommend_24));
        bottomNavigation_recommned.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_chat_24));
        bottomNavigation_recommned.add(new MeowBottomNavigation.Model(5,R.drawable.baseline_person_4_24));
        bottomNavigation_recommned.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                if (model.getId() == 1) {
                    startActivity(new Intent(getApplicationContext(), mainscreen.class));
                    finish();
                } else if (model.getId() == 2) {
                    startActivity(new Intent(getApplicationContext(), Recommend.class));
                } else if (model.getId() == 3) {
                    startActivity(new Intent(getApplicationContext(), Chat.class));
                    finish();
                } else if (model.getId() == 4) {
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    finish();
                }
                return null;
            }
        });
        bottomNavigation_recommned.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                if (model.getId() == 1) {
                    startActivity(new Intent(getApplicationContext(), mainscreen.class));
                    finish();
                }
                return null;
            }
        });
        bottomNavigation_recommned.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                if (model.getId() == 2) {
                    startActivity(new Intent(getApplicationContext(), Recommend.class));
                    finish();
                }
                return null;
            }
        });
        bottomNavigation_recommned.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                if (model.getId() == 3) {
                    startActivity(new Intent(getApplicationContext(), Chat.class));
                    finish();
                }
                return null;
            }
        });
        bottomNavigation_recommned.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                if (model.getId() == 4) {
                    startActivity(new Intent(getApplicationContext(), profile.class));
                    finish();
                }
                return null;
            }
        });

    }
}