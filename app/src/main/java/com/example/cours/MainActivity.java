package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button  button_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        button_1 = (Button) findViewById(R.id.button);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity();

            }
        });
    }
public void open_activity(){
            Intent intent = new Intent(this, Select_music_style.class);
            startActivity(intent);


        }

        //comment Adrien

    //BITOQ
}


