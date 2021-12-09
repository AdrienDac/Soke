package com.example.cours;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity implements View.OnClickListener{

    private TextView registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private TextView registerButton, banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerButton);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.registerName);
        editTextAge = (EditText) findViewById(R.id.registerDate);
        editTextEmail = (EditText) findViewById(R.id.registerEmail);
        editTextPassword =(EditText) findViewById(R.id.registerPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

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
            editTextFullName.setError("Un nom est nécessaire");
            editTextFullName.requestFocus();
            return;
        }

        if (age.isEmpty()){
            editTextAge.setError("Un âge est nécessaire");
            editTextAge.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Un Email est nécessaire");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Entrez un Email valide");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Un mot de passe est nécessaire");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editTextPassword.setError("Le mot de passe doit faire au minimum 6 caractères");
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
                                        Toast.makeText(Register.this, "Le compte a bien été créé", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        if (CommonMethod.player.isPlaying()) {
                                            CommonMethod.player.stop();
                                            CommonMethod.player.release();
                                        }
                                        startActivity(new Intent(Register.this, login.class));
                                    }
                                    else{
                                        Toast.makeText(Register.this, "La création du compte a échoué", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(Register.this, "La création du compte a échoué", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (CommonMethod.player.isPlaying()) {
            CommonMethod.player.stop();
            CommonMethod.player.release();
        }
        startActivity(new Intent(Register.this, login.class));
    }
}