package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Select_music_style extends AppCompatActivity {
    private ImageButton reggae;
    private ImageButton funk;
    private ImageButton rock;
    private ImageButton electro;
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

        rock = (ImageButton) findViewById(R.id.ImageRock);
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_rock();
            }
        });

        electro = (ImageButton) findViewById(R.id.ImageElectro);
        electro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity_electro();
            }
        });
    }



    public void open_activity_reggae(){
        Intent intent = new Intent(this, layout_reggae.class);
        startActivity(intent);
    }

    public void open_activity_funk(){
        Intent intent = new Intent(this,layout_funk.class);
        startActivity(intent);
    }

    public void open_activity_rock(){
        Intent intent = new Intent(this,layout_rock.class);
        startActivity(intent);
    }

    public void open_activity_electro(){
        Intent intent = new Intent(this, layout_electro.class);
        startActivity(intent);
    }
}