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
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.lang.annotation.Native;

public class layout_electro extends AppCompatActivity {

    RecyclerView recyclerview;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    String s1[], s2[];
    int images[] = {R.drawable.logo_epk2021, R.drawable.logo_tomorrow, R.drawable.logo_margin, R.drawable.logo_ewax_rvb_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_electro);

        recyclerview = findViewById(R.id.recyclerview_elec);

        Adapter_elec adapter_elec = new Adapter_elec(this, s1, s2, images);

        recyclerview.setAdapter(adapter_elec);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));


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
