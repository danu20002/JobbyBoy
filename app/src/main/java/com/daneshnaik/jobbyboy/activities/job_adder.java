package com.daneshnaik.jobbyboy.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.daneshnaik.jobbyboy.R;
import com.daneshnaik.jobbyboy.classes.job_details;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class job_adder extends AppCompatActivity {
TextInputEditText companyname_jobadder,location_jobadder,jobrole_jobadder,branch_jobadder,jobdescription_jobadder,packageamount_jobadder,link_jobadder,lastdate_jobadder;
AppCompatButton upload_btn_jobadder;
    FirebaseAuth auth;
    FirebaseDatabase database;
    Uri company_image;
    ImageView company_pic;
    FirebaseStorage storage;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_adder);
        companyname_jobadder=findViewById(R.id.companyname_jobadder);
        location_jobadder=findViewById(R.id.location_jobadder);
        jobrole_jobadder=findViewById(R.id.jobrole_jobadder);
        branch_jobadder=findViewById(R.id.branch_jobadder);
        jobdescription_jobadder=findViewById(R.id.jobdescription_jobadder);
        packageamount_jobadder= findViewById(R.id.package_amount_jobadder);
        link_jobadder=findViewById(R.id.link_jobadder);
        lastdate_jobadder=findViewById(R.id.lastdate_jobadder);
        upload_btn_jobadder=findViewById(R.id.upload_btn_jobadder);
        company_pic=findViewById(R.id.image_logo);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        final String senderId=FirebaseAuth.getInstance().getUid();
        upload_btn_jobadder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(job_adder.this);
                progressDialog.setTitle("Uploading Data");
                progressDialog.setMessage("Please wait For second");
                progressDialog.show();
                StorageReference reference=storage.getReference().child("Company_Images").child(auth.getUid());
                reference.putFile(company_image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isComplete()){
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photo_data_taker=uri.toString();
                                    String company_name_taker=companyname_jobadder.getEditableText().toString().trim();
                                    String job_role_taker=jobrole_jobadder.getEditableText().toString().trim();
                                    String branch_name_taker=branch_jobadder.getEditableText().toString().trim();
                                    String job_discription_taker=jobdescription_jobadder.getEditableText().toString().trim();
                                    String package_amount_taker=packageamount_jobadder.getEditableText().toString().trim();
                                    String link_data_taker=link_jobadder.getEditableText().toString().trim();
                                    String location_data_taker=location_jobadder.getEditableText().toString().trim();
                                    String last_date_data_taker=lastdate_jobadder.getEditableText().toString().trim();
                                    job_details jobby_boy=new job_details(senderId,job_role_taker,company_name_taker,branch_name_taker,package_amount_taker,job_discription_taker,photo_data_taker,location_data_taker,link_data_taker,last_date_data_taker);
                                    database.getReference().child("Job_Apply").push().setValue(jobby_boy).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            progressDialog.dismiss();
                                             startActivity(new Intent(getApplicationContext(), main_screen_frag.class));
                                             finish();
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

        company_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,46);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(data.getData()!=null){
                company_pic.setImageURI(data.getData());
                company_image=data.getData();
            }
        }
    }
}