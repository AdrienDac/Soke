package com.example.cours;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.navigation.NavigationView;

public class login extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private Button button_login;
    private ImageView back;
    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonMethod.SoundPlayer(this, R.raw.resonance); // ------- AUDIO ------------

        setContentView(R.layout.activity_login);


        register = (TextView) findViewById(R.id.registerLogin);
        register.setOnClickListener(this);

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_select();

            }
        });

        //--------------TOOLBAR-----------------------------------------------------------------
        Toolbar toolbar;
        toolbar = (Toolbar)findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);

    drawerLayout = findViewById(R.id.drawer_layout);
    navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.navigtion_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    //--------------------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerLogin:
                Intent intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        if (CommonMethod.player.isPlaying()){
            CommonMethod.player.stop();
            CommonMethod.player.release();
        }

    }

    public void open_activity_select(){
        Intent intent = new Intent(this, Select_music_style.class);
        startActivity(intent);
    }
    public void open_activity_home(){
        Intent intent = new Intent(this, New_home_page.class);
        startActivity(intent);
    }




}