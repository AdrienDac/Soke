package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView registerButton, banner;
    private EditText editTextname, editTextPassword, editTextDate, editTextEmail;

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

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
        //case
        }

    }
}