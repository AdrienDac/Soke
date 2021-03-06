package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener, LifecycleObserver {

    private TextView registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    ImageButton volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);


        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerButton);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.registerName);
        editTextAge = (EditText) findViewById(R.id.registerDate);
        editTextEmail = (EditText) findViewById(R.id.registerEmail);
        editTextPassword =(EditText) findViewById(R.id.registerPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        getWindow().setStatusBarColor(getResources().getColor(R.color.pink));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.pink));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Un nom est n??cessaire");
            editTextFullName.requestFocus();
            return;
        }

        if (age.isEmpty()){
            editTextAge.setError("Un ??ge est n??cessaire");
            editTextAge.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Un Email est n??cessaire");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Entrez un Email valide");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Un mot de passe est n??cessaire");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Le mot de passe doit faire au minimum 6 caract??res");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new  OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(fullName, age, email);

                           FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "Le compte a bien ??t?? cr????", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        if (CommonMethod.player.isPlaying()) {
                                            CommonMethod.player.stop();
                                            CommonMethod.player.release();
                                        }
                                        startActivity(new Intent(Register.this, login.class));
                                    }
                                    else{
                                        Toast.makeText(Register.this, "La cr??ation du compte a ??chou??", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(Register.this, "La cr??ation du compte a ??chou??", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        CommonMethod.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!CommonMethod.player.isPlaying()) {
            CommonMethod.player.start();
        }
    }

}