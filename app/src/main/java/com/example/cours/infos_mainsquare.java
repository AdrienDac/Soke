package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.content.Intent;
import android.net.Uri;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class infos_mainsquare extends AppCompatActivity implements LifecycleObserver {
    ImageView logo;
    String s1, s2;
    int mylogo;

    String fest_name;
    String name;
    String date;
    String lineup;
    String lieu;
    String url;

    TextView txt_lieu;
    TextView txt_name;
    TextView txt_date;
    TextView txt_lineup;
    Button button_url;
    ProgressBar progressBar;

    private FirebaseFirestore db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_mainsquare);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);


        txt_name = (TextView) findViewById(R.id.fest_name);
        txt_date = (TextView) findViewById(R.id.fest_date);
        txt_lineup = (TextView) findViewById(R.id.fest_lineup);
        txt_lieu = (TextView) findViewById(R.id.fest_lieu);
        button_url = (Button) findViewById(R.id.fest_url);
        progressBar = (ProgressBar) findViewById(R.id.progressBarfirestore);

        db = FirebaseFirestore.getInstance();

        logo = findViewById(R.id.logo);

        progressBar.setVisibility(View.VISIBLE);
        txt_lieu.setVisibility(View.GONE);
        txt_date.setVisibility(View.GONE);
        txt_lineup.setVisibility(View.GONE);
        txt_name.setVisibility(View.GONE);
        button_url.setVisibility(View.GONE);

        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_200));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.teal_200));





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
                lieu = documentSnapshot.getString("lieu");
                url = documentSnapshot.getString("url");
                txt_date.setText(date);
                txt_name.setText(name);
                txt_lineup.setText(lineup);
                txt_lieu.setText(lieu);
                button_url.setText(url);

                txt_lieu.setVisibility(View.VISIBLE);
                txt_date.setVisibility(View.VISIBLE);
                txt_lineup.setVisibility(View.VISIBLE);
                txt_name.setVisibility(View.VISIBLE);
                button_url.setVisibility(View.VISIBLE);

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

    public void browser1 (View view){
        Intent button_urlIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(button_urlIntent);
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

    @Override
    protected void onResume() {
        super.onResume();

        VideoView videoView = (VideoView) findViewById(R.id.video_info);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.select_style));
        if (videoView.isPlaying()){
            videoView.suspend();
        }
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        CommonMethod.player.pause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAppForgrounded() {
        CommonMethod.player.start();
    }
}