package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class infos_mainsquare extends AppCompatActivity {
    ImageView logo;
    String s1, s2;
    int mylogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_mainsquare);

        logo = findViewById(R.id.logo);

        getData();
        setData();

    }
    private void getData() {
    if (getIntent().hasExtra("mylogo")) {
        s1 = getIntent().getStringExtra("s1");
        s2 = getIntent().getStringExtra("s2");
        mylogo = getIntent().getIntExtra("mylogo", 1);
    }
    else{
           Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
    }


    private void setData() {
    logo.setImageResource(mylogo);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        open_activity_layout_electro();
    }

    private void open_activity_layout_electro() {
        Intent intent = new Intent(this, layout_electro.class);
        startActivity(intent);
    }
}