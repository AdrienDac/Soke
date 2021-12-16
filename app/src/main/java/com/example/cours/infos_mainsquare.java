package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class infos_mainsquare extends AppCompatActivity {
    ImageView logo;
    String s1, s2;
    int mylogo;

    String fest_name;
    String name;
    String date;
    String lineup;
    TextView txt_name;
    TextView txt_date;
    TextView txt_lineup;
    ProgressBar progressBar;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_mainsquare);

        txt_name = (TextView) findViewById(R.id.fest_name);
        txt_date = (TextView) findViewById(R.id.fest_date);
        txt_lineup = (TextView) findViewById(R.id.fest_lineup);
        progressBar = (ProgressBar) findViewById(R.id.progressBarfirestore);

        db = FirebaseFirestore.getInstance();

        logo = findViewById(R.id.logo);

        progressBar.setVisibility(View.VISIBLE);

        getData();
        setData();
        showData();

    }
    private void showData() {
        db.collection("festival").document(fest_name).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name = documentSnapshot.getString("nom");
                date = documentSnapshot.getString("date");
                lineup = documentSnapshot.getString("lineup");
                txt_date.setText(date);
                txt_name.setText(name);
                txt_lineup.setText(lineup);
                progressBar.setVisibility(View.GONE);

            }
        });
    }
    private void getData() {
    if (getIntent().hasExtra("mylogo") && getIntent().hasExtra("doc_name")) {
        s1 = getIntent().getStringExtra("s1");
        s2 = getIntent().getStringExtra("s2");
        mylogo = getIntent().getIntExtra("mylogo", 1);
        fest_name = getIntent().getStringExtra("doc_name");
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