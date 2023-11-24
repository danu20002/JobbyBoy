package com.daneshnaik.jobbyboy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.daneshnaik.jobbyboy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {
TextInputEditText email_forgotpassword;
AppCompatButton btn_forgot;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email_forgotpassword=findViewById(R.id.forgotemail_forgotpassword);
        btn_forgot=findViewById(R.id.btn_forgot);
        auth=FirebaseAuth.getInstance();
        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(Forgot_password.this);
                progressDialog.setTitle("Sending");
                progressDialog.setMessage("We are sending Email please wait");
                progressDialog.show();
                String email_forgotting = email_forgotpassword.getEditableText().toString().trim();
                if (!email_forgotting.isEmpty()) {


                    auth.sendPasswordResetEmail(email_forgotting).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                                progressDialog.setMessage("Please verify your Inbox");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    }
                                }, 2000);
                            }


                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.setMessage(e.getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                }
                            }, 2000);
                        }
                    });
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Forgot_password.this, "Please Enter your Registered Email ", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
}