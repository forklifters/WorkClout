package com.example.workclout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Challenges extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private helperClass x =new helperClass();
    //Recycler things------------------------------------
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();

    private void initImageBitmaps(){

        mImageUrls.add("https://www.mensjournal.com/wp-content/uploads/mf/man_workout_resting_get_rid_of_chin_fat_main_0.jpg?w=1200");
        mNames.add("Dude chillin");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://hungryrunnergirl.com/wp-content/uploads/2016/04/workouts.jpg");
        mNames.add("Lady chillin");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://cdn1.coachmag.co.uk/sites/coachmag/files/styles/16x9_480/public/2018/03/home-dumbbell-workout-plan.jpg?itok=2rSFbB9H&timestamp=1520599000");
        mNames.add("Pushup");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://images.askmen.com/1080x540/2018/03/08-044252-the_date_night_workout.jpg");
        mNames.add("Plank");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://www.shape.com/sites/shape.com/files/how-to-build-circuit-workout-_0.jpg");
        mNames.add("Ropes");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://www.shape.com/sites/shape.com/files/how-to-build-circuit-workout-_0.jpg");
        mNames.add("Ropes");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://www.rd.com/wp-content/uploads/2017/01/01-same-reasons-hit-workout-plateau-500886685-ferrantraite.jpg");
        mNames.add("Running");
        mDescriptions.add("Stuff about challenge");

        mImageUrls.add("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/body-building-workout-royalty-free-image-612262390-1535040444.jpg?resize=480:*");
        mNames.add("Deadlift");
        mDescriptions.add("Stuff about challenge");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mDescriptions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    //---------------------------------------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        night_mode();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recycler things----------------------------------------------------
        initImageBitmaps();
        //-------------------------------------------------------------------

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.challenges, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings = new Intent(Challenges.this, HomePage.class);
            startActivity(settings);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_challenges) {
            if(x.get_login_type() == "coaches") {
                Intent challenges = new Intent(Challenges.this, CreateChallenge.class);
                startActivity(challenges);
            }
            else{
                Intent challenges = new Intent(Challenges.this, Challenges.class);
                startActivity(challenges);
            }
        } else if (id == R.id.nav_teams) {
            Intent teams = new Intent(Challenges.this, Teams.class);
            startActivity(teams);
        } else if (id == R.id.nav_settings) {
            Intent settings = new Intent(Challenges.this, Settings.class);
            startActivity(settings);
        } else if (id == R.id.nav_profile) {
            Intent profileView = new Intent(Challenges.this, Profile.class);
            startActivity(profileView);
        } else if (id == R.id.nav_leaderboards) {
            Intent leaderboards = new Intent(Challenges.this, Leaderboard.class);
            startActivity(leaderboards);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(Challenges.this, HomePage.class);
            startActivity(home);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void night_mode()
    {
        if(x.get_lights_on())
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
