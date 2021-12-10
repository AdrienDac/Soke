package com.example.cours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.VideoView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.annotation.Native;

public class layout_electro extends AppCompatActivity {

    RecyclerView recyclerview;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String s1[], s2[];
    int images[] = {R.drawable.logo_msf, R.drawable.logo_hellfest, R.drawable.logo_viellescharrues, R.drawable.logo_rockenscene, R.drawable.logo_garorock, R.drawable.logo_dreamnation};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_electro);

        recyclerview = findViewById(R.id.recyclerview_elec);
        Adapter_elec adapter_elec = new Adapter_elec(this, s1, s2, images);

        recyclerview.setAdapter(adapter_elec);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);




        VideoView videoView = (VideoView) findViewById(R.id.fond_select);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.fond1));
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

        //--------------TOOLBAR-----------------------------------------------------------------
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

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
                        FirebaseAuth.getInstance().signOut();
                        Intent intent1 = new Intent(layout_electro.this, login.class);
                        if (CommonMethod.player.isPlaying()) {
                            CommonMethod.player.stop();
                            CommonMethod.player.release();
                        }
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });
    }
    //--------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
