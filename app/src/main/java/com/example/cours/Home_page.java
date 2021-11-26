package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home_page extends AppCompatActivity {
    private ImageButton  button_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        button_1 = (ImageButton) findViewById(R.id.txt_to_continue);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_activity();

            }
        });
    }
public void open_activity(){
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
        }

        //comment Adrien

    //BITOQ
}


