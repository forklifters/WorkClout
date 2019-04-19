package com.example.workclout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private helperClass x =new helperClass();
    NotificationCompat.Builder notification;
    private static final int uniqueID = 68734;

    private String UId, loginType, databaseUser, databaseCh1, databaseCh2, databaseCh3;
    private Double databaseClout;
    private FirebaseFirestore firestoreoreupdate;
    private DocumentReference setUPRef;
    private TextView clout, user;
    private ProgressBar challenge1, challenge2, challenge3;
    private TextView tv_ch1, tv_ch2, tv_ch3, tv_act1_1, tv_act1_2, tv_act1_3, tv_act2_1, tv_act2_2, tv_act2_3,
            tv_act3_1, tv_act3_2, tv_act3_3;
    private Button btn_ch1, btn_ch2, btn_ch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        firestoreoreupdate = FirebaseFirestore.getInstance();
        night_mode();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SetupVars---------------------------------------------------------------------
        tv_ch1 = (TextView) findViewById(R.id.tv_challenge1);
        tv_ch2 = (TextView) findViewById(R.id.tv_challenge2);
        tv_ch3 = (TextView) findViewById(R.id.tv_challenge3);
        tv_act1_1 = (TextView) findViewById(R.id.tv_activity1_1);
        tv_act1_2 = (TextView) findViewById(R.id.tv_activity1_2);
        tv_act1_3 = (TextView) findViewById(R.id.tv_activity1_3);
        tv_act2_1 = (TextView) findViewById(R.id.tv_activity2_1);
        tv_act2_2 = (TextView) findViewById(R.id.tv_activity2_2);
        tv_act2_3 = (TextView) findViewById(R.id.tv_activity2_3);
        tv_act3_1 = (TextView) findViewById(R.id.tv_activity3_1);
        tv_act3_2 = (TextView) findViewById(R.id.tv_activity3_2);
        tv_act3_3 = (TextView) findViewById(R.id.tv_activity3_3);
        btn_ch1 = (Button) findViewById(R.id.btn_challenge1);
        btn_ch2 = (Button) findViewById(R.id.btn_challenge2);
        btn_ch3 = (Button) findViewById(R.id.btn_challenge3);
        challenge1 = (ProgressBar) findViewById(R.id.pb_challenge1);
        challenge2 = (ProgressBar) findViewById(R.id.pb_challenge2);
        challenge3 = (ProgressBar) findViewById(R.id.pb_challenge3);

        UId =x.get_user_id();
        loginType=x.get_login_type();
        setUPRef = firestoreoreupdate.collection(loginType).document(UId);

        clout = (TextView) findViewById(R.id.tv_cloutScore);
        user = (TextView) findViewById(R.id.tv_user);
        //------------------------------------------------------------------------------

        setUPRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                databaseClout = documentSnapshot.getDouble("clout");
                clout.setText("Clout: " + databaseClout);
                databaseUser = documentSnapshot.getString("username");
                user.setText(databaseUser);

                //Set challenge and activity names----------------------------------------------
                databaseCh1 = documentSnapshot.getString("challenge1");
                databaseCh2 = documentSnapshot.getString("challenge2");
                databaseCh3 = documentSnapshot.getString("challenge3");
                tv_ch1.setText(databaseCh1);
                tv_ch2.setText(databaseCh2);
                tv_ch3.setText(databaseCh3);
                //TODO: Set the activity names
                //------------------------------------------------------------------------------
            }
        });

        //Increment progress bars-------------------------------------------------------
        btn_ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challenge1.incrementProgressBy(40);
                if(challenge1.getProgress() == 120){
                    databaseClout = databaseClout + 20;
                    update();
                }
            }
        });


        btn_ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challenge2.incrementProgressBy(40);
                if(challenge2.getProgress() == 120){
                    databaseClout = databaseClout + 20;
                    update();
                }
            }
        });

        btn_ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challenge3.incrementProgressBy(40);
                if(challenge3.getProgress() >= 120){
                    databaseClout = databaseClout + 20;
                    update();
                }
            }
        });
        //------------------------------------------------------------------------------

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
        getMenuInflater().inflate(R.menu.home_page, menu);
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
            Intent settings = new Intent(HomePage.this, HomePage.class);
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
                Intent challenges = new Intent(HomePage.this, CreateChallenge.class);
                startActivity(challenges);
            }
            else{
                Intent challenges = new Intent(HomePage.this, Challenges.class);
                startActivity(challenges);
            }
        } else if (id == R.id.nav_teams) {
            if(x.get_login_type() == "coaches") {
                Intent challenges = new Intent(HomePage.this, CreateTeam.class);
                startActivity(challenges);
            }
            else{
                Intent challenges = new Intent(HomePage.this, Teams.class);
                startActivity(challenges);
            }
        } else if (id == R.id.nav_settings) {
            Intent settings = new Intent(HomePage.this, Settings.class);
            startActivity(settings);
        } else if (id == R.id.nav_profile) {
            Intent profileView = new Intent(HomePage.this, Profile.class);
            startActivity(profileView);
        } else if (id == R.id.nav_leaderboards) {
            Intent leaderboards = new Intent(HomePage.this, Leaderboard.class);
            startActivity(leaderboards);
        } else if (id == R.id.nav_home) {
            Intent home = new Intent(HomePage.this, HomePage.class);
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

    public void update() {
        setUPRef.update("clout", databaseClout);
        Toast.makeText(HomePage.this, "Clout increased!",Toast.LENGTH_SHORT).show();
        clout.setText("Clout: " + databaseClout);
    }

}
