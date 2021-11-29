package com.example.cours;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.internal.service.Common;

public class login extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private Button button_login;
    private Object intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommonMethod.SoundPlayer(this, R.raw.resonance);

        setContentView(R.layout.activity_login);


        register = (TextView) findViewById(R.id.registerLogin);
        register.setOnClickListener(this);

        button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_home();

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
        }

    }

    public void open_activity_home(){
        Intent intent = new Intent(this, Select_music_style.class);
        startActivity(intent);
    }
}