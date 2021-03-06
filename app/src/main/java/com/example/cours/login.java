package com.example.cours;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class login extends AppCompatActivity implements View.OnClickListener, LifecycleObserver {

    private TextView register, forgot_password;
    private Button button_login;


    private EditText editTextEmail,editTextPassword;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CommonMethod.SoundPlayer(this, R.raw.resonance); // ------- AUDIO ------------

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        setContentView(R.layout.activity_login);

        getWindow().setStatusBarColor(getResources().getColor(R.color.pink));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.pink));


        register = (TextView) findViewById(R.id.registerLogin);
        register.setOnClickListener(this);

        forgot_password = (TextView) findViewById(R.id.forgotPassword);
        forgot_password.setOnClickListener(this);

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.emailLogin);
        editTextPassword = (EditText) findViewById(R.id.passwordLogin);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        mAuth = FirebaseAuth.getInstance();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerLogin:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
            case R.id.button_login:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, forgot_password.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Un Email est n??cessaire");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Email non valide");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Un mot de passe est n??cessaire");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Le mot de passe doit faire au moin 6 caract??res");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(login.this, WelcomeUser.class));
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(login.this, "Connexion impossible, verifiez vos informations", Toast.LENGTH_LONG).show();
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
    public void onBackPressed(){
            super.onBackPressed();
            if (CommonMethod.player.isPlaying()){
                CommonMethod.player.stop();
                CommonMethod.player.release();
            }
            startActivity(new Intent(login.this, New_home_page.class));

        }

    @Override
    protected void onResume() {
        super.onResume();

        if (!CommonMethod.player.isPlaying()) {
            CommonMethod.player.start();
        }
    }








}