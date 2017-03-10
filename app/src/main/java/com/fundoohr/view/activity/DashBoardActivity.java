package com.fundoohr.view.activity;

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
import com.fundoohr.view.fragment.EngineerFragment;
/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/

public class DashBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static String TAG ="DashBoardActivity";
    Toolbar mToolbar;
    ProgressDialog mDailog;
    CardView cardView;
    EngineerFragment enggFrag;

    public DashBoardActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        enggFrag = new EngineerFragment(this);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.toolcolor));
        setSupportActionBar(mToolbar);

        cardView= (CardView) findViewById(R.id.engineers);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_engineer,enggFrag).commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
            mToolbar.setTitle(item.getTitle().toString());
            Intent intent = new Intent(DashBoardActivity.this,DashBoardActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_engineer) {
            mToolbar.setTitle(item.getTitle().toString());
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_engineer,enggFrag).commit();
        } else if (id == R.id.nav_attend) {
            mToolbar.setTitle(item.getTitle().toString());

        } else if (id == R.id.nav_report) {
            mToolbar.setTitle(item.getTitle().toString());
        } else if (id == R.id.nav_logout) {
            mToolbar.setTitle(item.getTitle().toString());
            Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_clients) {
            mToolbar.setTitle(item.getTitle().toString());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
