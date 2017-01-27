package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.view.fragment.EngineerFragment;

public class DashBoard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar mtoolbar;
    ProgressDialog mDailog;
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        mtoolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.toolcolor));
        setSupportActionBar(mtoolbar);

        cardView= (CardView) findViewById(R.id.engineers);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_engineer,new EngineerFragment()).commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mtoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            mtoolbar.setTitle(item.getTitle().toString());
            Intent intent = new Intent(DashBoard.this,DashBoard.class);
            startActivity(intent);
        } else if (id == R.id.nav_engineer) {
            mtoolbar.setTitle(item.getTitle().toString());
            mDailog= new ProgressDialog(DashBoard.this);
            mDailog.setMessage("Loading....");
            mDailog.show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_engineer,new EngineerFragment(this,mDailog)).commit();
        } else if (id == R.id.nav_attend) {
            mtoolbar.setTitle(item.getTitle().toString());

        } else if (id == R.id.nav_report) {
            mtoolbar.setTitle(item.getTitle().toString());
        } else if (id == R.id.nav_logout) {
            mtoolbar.setTitle(item.getTitle().toString());
            Intent intent = new Intent(DashBoard.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_clients) {
            mtoolbar.setTitle(item.getTitle().toString());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
