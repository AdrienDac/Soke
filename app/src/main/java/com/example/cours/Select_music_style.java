package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Select_music_style extends AppCompatActivity {
    private ImageButton reggae;
    private ImageButton funk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music_style);

        reggae = (ImageButton) findViewById(R.id.ImageReggae);
        reggae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_reggae();
            }
        });

        funk = (ImageButton) findViewById(R.id.ImageFunk);
        funk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_funk();
            }
        });


    }



    public void open_activity_reggae(){
        Intent intent = new Intent(this, infos1.class);
        startActivity(intent);
    }

    public void open_activity_funk(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}