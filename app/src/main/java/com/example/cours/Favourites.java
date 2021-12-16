package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Favourites extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);


        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigtion_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        progressBar = (ProgressBar) findViewById(R.id.progressBar_profile);
        final TextView fullname_profile = (TextView) findViewById(R.id.fullname_profile);
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    progressBar.setVisibility(View.GONE);
                    String fullName = userProfile.fullName;
                    fullname_profile.setText("Bienvenue " + fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Favourites.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.nav_favourite:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_list_festival:
                        Intent intent = new Intent(Favourites.this, layout_electro.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_log_out:
                        Intent intent1 = new Intent(Favourites.this, login.class);
                        if (CommonMethod.player.isPlaying()) {
                            CommonMethod.player.stop();
                            CommonMethod.player.release();
                            startActivity(intent1);
                            break;
                        }
                }
            return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            open_activity_layout_electro();
        }
    }

    private void open_activity_layout_electro() {
        Intent intent = new Intent(this, layout_electro.class);
        startActivity(intent);

    }
}