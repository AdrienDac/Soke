package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.annotation.Native;

public class layout_electro extends AppCompatActivity implements LifecycleObserver {

    RecyclerView recyclerview;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton volume;



    String s1[], name[] ={"hellfest", "mainsquare", "dreambeach", "solidays"};
    int images[] = {R.drawable.hellfest, R.drawable.main_square, R.drawable.dream_beach, R.drawable.solidays};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_electro);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        recyclerview = findViewById(R.id.recyclerview_elec);
        Adapter_elec adapter_elec = new Adapter_elec(this, s1, name, images);

        recyclerview.setAdapter(adapter_elec);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setStatusBarColor(getResources().getColor(R.color.pink));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.pink));




        recyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL) {
            @Override
            public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
                // Do not draw the divider
            }
        });






        //--------------TOOLBAR-----------------------------------------------------------------
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);


        volume =(ImageButton) findViewById(R.id.icon_volume);
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonMethod.player.isPlaying()){
                    CommonMethod.player.pause();
                    volume.setImageResource(R.drawable.volume_off);

                }else{
                    CommonMethod.player.start();
                    volume.setImageResource(R.drawable.volume_up);
                }
            }
        });

        navigationView.getItemBackground();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigtion_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.nav_list_festival:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_favourite:
                        Intent intent = new Intent(layout_electro.this, Favourites.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_log_out:
                        new AlertDialog.Builder(layout_electro.this)
                        .setTitle("Déconnexion")
                                .setMessage("Voulez vous vraiment vous déconnecter?")
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseAuth.getInstance().signOut();
                                        Intent intent = new Intent(layout_electro.this, login.class);
                                        if (CommonMethod.player.isPlaying()) {
                                            CommonMethod.player.stop();
                                            CommonMethod.player.release();
                                        }
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();;
                }


                return true;
            }

        });
    }
    //--------------------------------------------------------------------------------------

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onAppBackgrounded() {
        CommonMethod.player.pause();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            new AlertDialog.Builder(this)
                    .setTitle("Déconnexion")
                    .setMessage("Voulez vous vraiment vous déconnecter?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(layout_electro.this, login.class);
                    if (CommonMethod.player.isPlaying()) {
                        CommonMethod.player.stop();
                        CommonMethod.player.release();
                    }
                    startActivity(intent);
                }
            })
    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonMethod.player.isPlaying()) {
            CommonMethod.player.start();
        }

        VideoView videoView = (VideoView) findViewById(R.id.fond_select);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fond_select));
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

}


