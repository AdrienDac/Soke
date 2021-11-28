package com.example.cours;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class New_home_page extends AppCompatActivity {
    private ImageButton taptocontinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home_page);

        MediaPlayer player;
        player = MediaPlayer.create(this, R.raw.resonance);


        VideoView videoView = (VideoView) findViewById(R.id.home_page_vid);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test));
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);


            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        ImageButton taptocontinue = (ImageButton) findViewById(R.id.taptocontinue);
        taptocontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.suspend();
                VideoView videoView1 = (VideoView) findViewById(R.id.login_transition);
                videoView1.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.select_style));
                videoView1.start();
                taptocontinue.setVisibility(taptocontinue.INVISIBLE);

                videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                {
                    @Override
                    public void onCompletion(MediaPlayer mp)
                    {
                        open_loginscreen();
                        taptocontinue.setVisibility(taptocontinue.VISIBLE);
                        player.start();
                    }
                });
            }
        });

    }
    public void open_loginscreen(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }

}