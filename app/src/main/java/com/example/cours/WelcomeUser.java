package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeUser extends AppCompatActivity implements View.OnClickListener {

        private Button nex;

        private FirebaseUser user;
        private DatabaseReference reference;
        private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        nex = (Button) findViewById(R.id.nex);
        nex.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView welcome = (TextView) findViewById(R.id.welcome);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    String fullName = userProfile.fullName;
                    welcome.setText("Welcome " + fullName + " !");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WelcomeUser.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.nex:
                startActivity(new Intent(WelcomeUser.this, layout_electro.class));
                break;
        }
    }


}