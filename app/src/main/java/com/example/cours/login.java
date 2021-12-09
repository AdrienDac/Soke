package com.example.cours;

import android.app.ActionBar;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class login extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private Button button_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonMethod.SoundPlayer(this, R.raw.resonance); // ------- AUDIO ------------

        setContentView(R.layout.activity_login);


        register = (TextView) findViewById(R.id.registerLogin);
        register.setOnClickListener(this);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_select();

            }
        });

    }



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
                open_activity_home_page();
        }

        }


    public void open_activity_select(){
        Intent intent = new Intent(this, layout_electro.class);
        startActivity(intent);
    }
    public void open_activity_home_page(){
        Intent intent = new Intent( this, New_home_page.class);
        startActivity(intent);
    }




}