package com.daneshnaik.jobbyboy.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daneshnaik.jobbyboy.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
TextView btn_signup_login;
TextInputEditText email_signin,password_signin;
AppCompatButton signin_btn_signin;
TextView forgot_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_signup_login=findViewById(R.id.signup_btn_login);
        btn_signup_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), User_sign_up.class));
            }
        });



    }
}