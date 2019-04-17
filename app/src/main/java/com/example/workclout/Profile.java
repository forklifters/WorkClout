package com.example.workclout;

import android.content.Intent;
import android.os.Bundle;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText bio, weight, height, gender, fullName, age;
    private Button setup;
    private String bioInput, fullNameInput, ageInput, heightInput, weightInput, loginType, UId,genderInput;
    private String databaseBio, databaseName,databaseAge,databaseHeight,databaseWeight,databaseGender;
    private FirebaseFirestore firestoreoreupdate;
    private DocumentReference setUPRef;
    private helperClass x =new helperClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firestoreoreupdate = FirebaseFirestore.getInstance();
        night_mode();
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

        bio = (EditText) findViewById(R.id.BioId);
        age = (EditText) findViewById(R.id.AgeID);
        height = (EditText) findViewById(R.id.HeightId);
        weight = (EditText) findViewById(R.id.WeightID);
        fullName = (EditText) findViewById(R.id.FullNameID);
        gender=(EditText) findViewById(R.id.GenderID);
        setup = (Button) findViewById(R.id.ProfileID);

        UId =x.get_user_id();
        loginType=x.get_login_type();
        setUPRef = firestoreoreupdate.collection(loginType).document(UId);

        if(databaseName==null && databaseBio== null && databaseGender==null && databaseAge== null  && databaseHeight== null && databaseWeight== null)
        {
            fullName.setHint("Full Name");
            bio.setHint("Bio");
            gender.setHint("Gender");
            age.setHint("Age");
            height.setHint("Height in ft" );
            weight.setHint("Weight in lbs" );

        }
        else {
            fullName.setHint("Full name is " + databaseName);
            bio.setHint("full bio is " + databaseBio);
            gender.setHint("gender is " + databaseGender);
            age.setHint("age is " + databaseAge);
            height.setHint("height is " + databaseHeight);
            weight.setHint("weight is " + databaseWeight);
        }
        setUPRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                databaseName = documentSnapshot.getString("Name");
                databaseBio = documentSnapshot.getString("bio");
                databaseGender = documentSnapshot.getString("gender");
                databaseAge = documentSnapshot.getString("age");
                databaseHeight = documentSnapshot.getString("height");
                databaseWeight = documentSnapshot.getString("weight");
                fullName.setText(databaseName);
                bio.setText(databaseBio);
                gender.setText(databaseGender);
                age.setText(databaseAge);
                height.setText(databaseHeight);
                weight.setText(databaseWeight);

            }
        });
        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameInput=fullName.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                ageInput=age.getText().toString().trim();
                heightInput=height.getText().toString().trim();
                weightInput =weight.getText().toString().trim();
                genderInput=gender.getText().toString().trim();

                update();
                Intent loginSuccess = new Intent(Profile.this, Profile.class);
                startActivity(loginSuccess);
            }
        });

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

    public void update() {
        setUPRef.update("Name", fullNameInput);
        setUPRef.update("bio", bioInput);
        setUPRef.update("gender", genderInput);
        setUPRef.update("age", ageInput);
        setUPRef.update("height", heightInput);
        setUPRef.update("weight", weightInput);
        Toast.makeText(Profile.this, "Your profile is updated",Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.profile, menu);
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
            Intent settings = new Intent(Profile.this, HomePage.class);
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
                Intent challenges = new Intent(Profile.this, CreateChallenge.class);
                startActivity(challenges);
            }
            else{
                Intent challenges = new Intent(Profile.this, Challenges.class);
                startActivity(challenges);
            }
        } else if (id == R.id.nav_teams) {
            if(x.get_login_type() == "coaches") {
                Intent challenges = new Intent(Profile.this, CreateTeam.class);
                startActivity(challenges);
            }
            else{
                Intent challenges = new Intent(Profile.this, Teams.class);
                startActivity(challenges);
            }
        } else if (id == R.id.nav_settings) {
            Intent settings = new Intent(Profile.this, Settings.class);
            startActivity(settings);
        } else if (id == R.id.nav_profile) {
            Intent profileView = new Intent(Profile.this, Profile.class);
            startActivity(profileView);
        } else if (id == R.id.nav_leaderboards) {
            Intent leaderboards = new Intent(Profile.this, Leaderboard.class);
            startActivity(leaderboards);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(Profile.this, HomePage.class);
            startActivity(home);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
