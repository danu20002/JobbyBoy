package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.daneshnaik.jobbyboy.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;


public class mainscreen extends AppCompatActivity {
MeowBottomNavigation bottombar_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        bottombar_main=findViewById(R.id.bottomBar_main);
        replace(new main_screen_frag());

        bottombar_main.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottombar_main.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_recommend_24));
        bottombar_main.add(new MeowBottomNavigation.Model(3,R.drawable.baseline_message_24));
        bottombar_main.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_person_4_24));
        bottombar_main.show(1,true);
        bottombar_main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                int i=model.getId();
                switch (i){
                  case 1:
                      replace(new main_screen_frag());
                      break;
                  case 2:
                      replace(new recommend_frag());
                      break;
                  case 3:
                      replace(new chat_frag());
                      break;
                  case 4:
                      replace(new profile_frag());
                      break;
              }
              return null;
            }
        });
//      bottombar_main.setOnItemSelectedListener(new OnItemSelectedListener() {
//          @Override
//          public boolean onItemSelect(int i) {
//
//              switch (i){
//                  case 0:
//                      replace(new main_screen_frag());
//                      bottombar_main.setBarBackgroundColor(getColor(R.color.main_color));
//                      break;
//                  case 1:
//                      replace(new recommend_frag());
//                      bottombar_main.setBarBackgroundColor(getColor(R.color.teal_700));
//                      break;
//                  case 2:
//                      replace(new chat_frag());
//                      bottombar_main.setBarBackgroundColor(getColor(R.color.chat_color));
//                      break;
//                  case 3:
//                      replace(new profile_frag());
//                      bottombar_main.setBarBackgroundColor(getColor(R.color.profile_color));
//                      break;
//              }
//              return true;
//          }
//      });

    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();

    }


}


