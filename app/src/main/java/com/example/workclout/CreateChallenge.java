package com.example.workclout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateChallenge extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText challengeT, challengeDif, challengeL, challengeDes, activity_1, activity_2,activity_3;
    private String challengeTitle, challengeDescription, challengeID, UID, act1, act2, act3;
    private int challengeDifficulty, challengeLength;
    private Button createChallenge;
    private DocumentReference challengeRef;
    private FirebaseFirestore mFirestore;
    private helperClass x =new helperClass();

    private String createChallengeID(String title, int difficulty, int length){
        String ID = title.toLowerCase().trim() + difficulty + length;
        return ID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_challenge);

        night_mode();

        challengeT=findViewById(R.id.et_challengeTitle);
        challengeDif=findViewById(R.id.challengeDifficulty);
        challengeL=findViewById(R.id.challengeLength);
        challengeDes=findViewById(R.id.challengeDescription);
        createChallenge=findViewById(R.id.createChallengeButton);
        activity_1=findViewById(R.id.Activity_1ID);
        activity_2=findViewById(R.id.Activity_2ID);
        activity_3=findViewById(R.id.Activity_3ID);

        mFirestore = FirebaseFirestore.getInstance();
        UID=x.get_user_id();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        createChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengeTitle = challengeT.getText().toString();
                challengeDifficulty = Integer.parseInt(challengeDif.getText().toString());
                challengeLength = Integer.parseInt(challengeL.getText().toString());
                challengeDescription = challengeDes.getText().toString();
                challengeID = createChallengeID(challengeTitle, challengeDifficulty, challengeLength);
                act1=activity_1.getText().toString();
                act2=activity_2.getText().toString();
                act3=activity_3.getText().toString();

                Map<String, Object> dataToAdd = new HashMap<>();

                dataToAdd.put("title", challengeTitle);
                dataToAdd.put("difficulty", challengeLength);
                dataToAdd.put("length", challengeLength);
                dataToAdd.put("description", challengeDescription);
                dataToAdd.put("challengeID", challengeID);
                dataToAdd.put("UID", UID);
                dataToAdd.put("Activity 1",act1);
                dataToAdd.put("Activity 2",act2);
                dataToAdd.put("Activity 3",act3);


                mFirestore.collection("challenges").document(challengeID).set(dataToAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CreateChallenge.this, "Challenge created", Toast.LENGTH_SHORT).show();
                        mFirestore.collection("Coaches").document(UID).update(challengeID,challengeID);
                        Intent loginSuccess = new Intent(CreateChallenge.this, HomePage.class);
                        startActivity(loginSuccess);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();

                        Toast.makeText(CreateChallenge.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
        getMenuInflater().inflate(R.menu.create_challenge, menu);
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
                Intent challenges = new Intent(CreateChallenge.this, CreateChallenge.class);
                startActivity(challenges);
            }
            else{
                Intent challenges = new Intent(CreateChallenge.this, Challenges.class);
                startActivity(challenges);
            }
        } else if (id == R.id.nav_teams) {
            Intent teams = new Intent(CreateChallenge.this, Teams.class);
            startActivity(teams);
        } else if (id == R.id.nav_settings) {
            Intent settings = new Intent(CreateChallenge.this, Settings.class);
            startActivity(settings);
        } else if (id == R.id.nav_profile) {
            Intent profileView = new Intent(CreateChallenge.this, Profile.class);
            startActivity(profileView);
        } else if (id == R.id.nav_leaderboards) {
            Intent leaderboards = new Intent(CreateChallenge.this, Leaderboard.class);
            startActivity(leaderboards);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(CreateChallenge.this, HomePage.class);
            startActivity(home);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void night_mode()
    {
        if(x.get_lights_on()==true)
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
