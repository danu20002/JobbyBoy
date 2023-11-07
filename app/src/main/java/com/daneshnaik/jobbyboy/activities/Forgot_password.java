package com.daneshnaik.jobbyboy.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.daneshnaik.jobbyboy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {
TextInputEditText email_forgotpassword;
AppCompatButton btn_forgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email_forgotpassword=findViewById(R.id.forgotemail_forgotpassword);
        btn_forgot=findViewById(R.id.btn_forgot);
        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog=new ProgressDialog(Forgot_password.this);
                progressDialog.setTitle("Sending");
                progressDialog.setMessage("We are sending Email please wait");
                progressDialog.show();
                String email_forgotting=email_forgotpassword.getEditableText().toString().trim();
                FirebaseAuth.getInstance().sendPasswordResetEmail(email_forgotting).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isComplete()){
                            progressDialog.setMessage("Please verify your Inbox");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                }
                            },2000);
                        }

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
                        },2000);
                    }
                });
            }
        });
    }
}