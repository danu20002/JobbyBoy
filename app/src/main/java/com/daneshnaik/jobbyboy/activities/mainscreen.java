package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.daneshnaik.jobbyboy.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class mainscreen extends AppCompatActivity {
SmoothBottomBar bottombar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        bottombar_main=findViewById(R.id.bottomBar_main);
        replace(new main_screen_frag());

      bottombar_main.setOnItemSelectedListener(new OnItemSelectedListener() {
          @Override
          public boolean onItemSelect(int i) {

              switch (i){
                  case 0:
                      replace(new main_screen_frag());
                      bottombar_main.setBarBackgroundColor(getColor(R.color.main_color));
                      break;
                  case 1:
                      replace(new recommend_frag());
                      bottombar_main.setBarBackgroundColor(getColor(R.color.teal_700));
                      break;
                  case 2:
                      replace(new chat_frag());
                      bottombar_main.setBarBackgroundColor(getColor(R.color.chat_color));
                      break;
                  case 3:
                      replace(new profile_frag());
                      bottombar_main.setBarBackgroundColor(getColor(R.color.profile_color));
                      break;
              }
              return true;
          }
      });

    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();

    }


}


