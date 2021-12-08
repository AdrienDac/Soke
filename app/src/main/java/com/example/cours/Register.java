package com.example.cours;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView registerButton, banner;
    private EditText editTextname, editTextPassword, editTextDate, editTextEmail;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        //banner = (Button) findViewById(R.id.banner);
        //banner.setOnClickListener(this);

        editTextname = (EditText) findViewById(R.id.registerName);
        editTextDate = (EditText) findViewById(R.id.registerDate);
        editTextPassword = (EditText) findViewById(R.id.registerPassword);
        editTextEmail = (EditText) findViewById(R.id.registerEmail);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, Home_page.class));
                break;
            case R.id.registeUser:
                registerUser();
                break;
        }
    }*/
    private void registerUser(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String fullName=editTextname.getText().toString().trim();
        String age=editTextDate.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextname.setError("Full name is required !");
            editTextname.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextDate.setError("Your age is required !");
            editTextDate.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Your Email is required !");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email !");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextEmail.setError("Password is required !");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName,age,email);
                            FirebaseAuth.getInstance().getCurrentUser()
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addonCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"User has been registered succesfully !", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }
                                    else{
                                        Toast.makeText(Register.this,"Failed to register! Try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });{

                            }
                        }
                        else{
                            Toast.makeText(Register.this,"Failed to register! Try again", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }
}