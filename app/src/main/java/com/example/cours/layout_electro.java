package com.example.cours;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class layout_electro extends AppCompatActivity {

    RecyclerView recyclerview;

    String s1[], s2[];
    int images[] = {R.drawable.logo_epk2021,R.drawable.logo_tomorrow,R.drawable.logo_margin,R.drawable.logo_ewax_rvb_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_electro);

        recyclerview = findViewById(R.id.recyclerview_elec);

        Adapter_elec adapter_elec = new Adapter_elec(this, s1, s2, images);

        recyclerview.setAdapter(adapter_elec);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

    }


}