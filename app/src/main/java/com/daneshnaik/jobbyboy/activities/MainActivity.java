package com.daneshnaik.jobbyboy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daneshnaik.jobbyboy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
TextView btn_signup_login;
TextInputEditText email_signin,password_signin;
AppCompatButton signin_btn_signin;
TextView forgot_text;
FirebaseAuth auth;
FirebaseDatabase database;
LottieAnimationView loading_signIn;

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
         email_signin=findViewById(R.id.email_signin);
         password_signin=findViewById(R.id.password_signin);
         signin_btn_signin=findViewById(R.id.signin_btn_signin);
         forgot_text=findViewById(R.id.forgot_text_signin);
         loading_signIn=findViewById(R.id.loading_signin);
         auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null && auth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(getApplicationContext(),mainscreen.class));
            finish();
        }
         signin_btn_signin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 loading_signIn.setVisibility(View.VISIBLE);
                 String email_taken=email_signin.getEditableText().toString().trim();
                 String password_taken=password_signin.getEditableText().toString().trim();
                 if(!email_taken.isEmpty()){
                     if(!password_taken.isEmpty()){

                         auth.signInWithEmailAndPassword(email_taken,password_taken).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                             @Override
                             public void onSuccess(AuthResult authResult) {
                                 if (auth.getCurrentUser().isEmailVerified()){
                                     loading_signIn.setVisibility(View.INVISIBLE);
                                     startActivity(new Intent(getApplicationContext(), mainscreen.class));
                                     finish();
                                 }else{
                                     loading_signIn.setVisibility(View.INVISIBLE);
                                     Toast.makeText(MainActivity.this, "Please verify your Email we sent already verification email to your Inbox", Toast.LENGTH_SHORT).show();
                                 }
                             }
                         })
                         .addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 loading_signIn.setVisibility(View.INVISIBLE);
                                 Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         });
                     }else{
                         loading_signIn.setVisibility(View.INVISIBLE);
                         password_signin.setError("Please Enter your Password");
                     }
                 }else{
                     loading_signIn.setVisibility(View.INVISIBLE);
                     email_signin.setError("Please Enter your Email");
                     Toast.makeText(MainActivity.this, "please Enter Email", Toast.LENGTH_SHORT).show();
                 }
             }
         });
        forgot_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Forgot_password.class));
            }
        });


    }
}