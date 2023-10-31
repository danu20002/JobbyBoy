package com.daneshnaik.jobbyboy.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daneshnaik.jobbyboy.R;
import com.daneshnaik.jobbyboy.classes.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_sign_up extends AppCompatActivity {
CircleImageView image_signup;
TextInputEditText fullname_signup,email_signup,password_signup;
AppCompatButton signup_btn_signup;
TextView sigin_text_signup;
Uri selectImage;
FirebaseAuth auth;
FirebaseDatabase database;
FirebaseStorage storage;
LottieAnimationView loading_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        image_signup=findViewById(R.id.image_signup);
        fullname_signup=findViewById(R.id.name_signup);
        email_signup=findViewById(R.id.email_signup);
        password_signup=findViewById(R.id.password_signup);
        signup_btn_signup=findViewById(R.id.signup_btn_signup);
        sigin_text_signup=findViewById(R.id.signin_text_signup);
         auth=FirebaseAuth.getInstance();
         database=FirebaseDatabase.getInstance();
         storage=FirebaseStorage.getInstance();
         loading_signup=findViewById(R.id.lottie_loading_signup);

        sigin_text_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });


        image_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });

        signup_btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading_signup.setVisibility(View.VISIBLE);
                String fullname_taken=fullname_signup.getEditableText().toString().trim();
                String email_taken=email_signup.getEditableText().toString().trim();
                String password_taken=password_signup.getEditableText().toString().trim();
                if(!fullname_taken.isEmpty()){
                    if(!email_taken.isEmpty()){
                        if(!password_taken.isEmpty()){
                             if(selectImage!=null){
                               auth.createUserWithEmailAndPassword(email_taken,password_taken).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if (task.isSuccessful()){
                                          auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task) {
                                                   Toast.makeText(User_sign_up.this, "User created successfully", Toast.LENGTH_SHORT).show();
                                                   StorageReference reference=storage.getReference().child("profiles").child(auth.getUid());
                                                   reference.putFile(selectImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                           if (task.isComplete()){
                                                               reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                   @Override
                                                                   public void onSuccess(Uri uri) {
                                                                       String imageurl=uri.toString();
                                                                       String uid=auth.getUid();
                                                                       String Fullname=fullname_signup.getEditableText().toString().trim();
                                                                       String Email=email_signup.getEditableText().toString().trim();
                                                                       String password=password_signup.getEditableText().toString().trim();
                                                                       Users users=new Users(uid,Fullname,imageurl,Email,password);


                                                                       database.getReference().child("Users").child(uid).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                           @Override
                                                                           public void onComplete(@NonNull Task<Void> task) {
                                                                               loading_signup.setVisibility(View.INVISIBLE);
                                                                               Toast.makeText(User_sign_up.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
                                                                               Intent intent=new Intent(User_sign_up.this,MainActivity.class);
                                                                               startActivity(intent);
                                                                               finish();
                                                                           }
                                                                       }).addOnFailureListener(new OnFailureListener() {
                                                                           @Override
                                                                           public void onFailure(@NonNull Exception e) {
                                                                               loading_signup.setVisibility(View.INVISIBLE);
                                                                               Toast.makeText(User_sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                           }
                                                                       });

                                                                   }
                                                               }).addOnFailureListener(new OnFailureListener() {
                                                                   @Override
                                                                   public void onFailure(@NonNull Exception e) {
                                                                       loading_signup.setVisibility(View.INVISIBLE);
                                                                       Toast.makeText(User_sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                   }
                                                               });
                                                           }
                                                       }
                                                   }).addOnFailureListener(new OnFailureListener() {
                                                       @Override
                                                       public void onFailure(@NonNull Exception e) {
                                                           loading_signup.setVisibility(View.INVISIBLE);
                                                           Toast.makeText(User_sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                       }
                                                   });
                                               }
                                           }).addOnFailureListener(new OnFailureListener() {
                                               @Override
                                               public void onFailure(@NonNull Exception e) {
                                                   loading_signup.setVisibility(View.INVISIBLE);
                                                   Toast.makeText(User_sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                               }
                                           });
                                       }
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {
                                       loading_signup.setVisibility(View.INVISIBLE);
                                       Toast.makeText(User_sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                               });
                             }else{
                                 loading_signup.setVisibility(View.INVISIBLE);
                                 Toast.makeText(User_sign_up.this, "Profile photo is required", Toast.LENGTH_SHORT).show();
                             }
                        }else {
                            loading_signup.setVisibility(View.INVISIBLE);
                            password_signup.setError("please Enter password");
                        }
                    }else{
                        loading_signup.setVisibility(View.INVISIBLE);
                        email_signup.setError("please Enter Email");
                    }
                }else {
                    loading_signup.setVisibility(View.INVISIBLE);
                    fullname_signup.setError("please Enter your Email");
                }

            }
        });
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(data.getData()!=null){
                image_signup.setImageURI(data.getData());
                selectImage=data.getData();
            }
        }

    }
}