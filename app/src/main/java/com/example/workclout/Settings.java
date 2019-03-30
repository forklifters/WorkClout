package com.example.workclout;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Settings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String UId, loginType, resetPassWordVal, resetEmailVal, resetUserName;
    private String databaseBio, databaseName, databaseAge, databasePassword, databaseHeight, databaseWeight, databaseGender;
    private EditText resetPassWordInput, resetEmailInput;
    private Button resetPassWord, deleteAccount, resetEmail;
    private Switch nightmode, wifi, notifications;
    private FirebaseFirestore firestoreoreupdate;
    private DocumentReference setUPRef;
    private DocumentReference setUPRef2;
    private helperClass x =new helperClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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

        UId =x.get_user_id();
        loginType=x.get_login_type();
        setUpVariables();
        Toast.makeText(Settings.this, "Your " + UId, Toast.LENGTH_SHORT).show();
        Toast.makeText(Settings.this, "Your " + loginType, Toast.LENGTH_SHORT).show();
        firestoreoreupdate = FirebaseFirestore.getInstance();
        setUPRef = firestoreoreupdate.collection(loginType).document(UId);
        //fuck me nothing fucking works
        Toast.makeText(Settings.this, "Your login and id" + loginType + UId, Toast.LENGTH_SHORT).show();
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(false);
                    Toast.makeText(Settings.this, "Your wifi is on" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(true);
                    Toast.makeText(Settings.this, "Your wifi is off" , Toast.LENGTH_SHORT).show();

                }
            }
        });

        nightmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    x.set_lights_on(true);

                }
                else
                {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    x.set_lights_on(false);
                }
            }
        });


        resetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUPRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            databaseName = documentSnapshot.getString("Name");
                            databaseBio = documentSnapshot.getString("bio");
                            databaseGender = documentSnapshot.getString("gender");
                            databaseAge = documentSnapshot.getString("age");
                            databaseHeight = documentSnapshot.getString("height");
                            databaseWeight = documentSnapshot.getString("weight");
                            databasePassword = documentSnapshot.getString("password");
                            Toast.makeText(Settings.this, "Your fucked bitch1" + databaseName, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Your fucked bitch2" + databaseBio, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Your fucked bitch3" + databaseGender, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Your fucked bitch4" + databaseAge, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Your fucked bitch5" + databaseHeight, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Your fucked bitch6" + databaseWeight, Toast.LENGTH_SHORT).show();
                            Toast.makeText(Settings.this, "Your fucked bitch7" + databasePassword, Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            Toast.makeText(Settings.this, "Your fucked", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                resetEmailVal=resetEmailInput.getText().toString();
                resetUserName=createUserId(resetEmailVal);

                Map<String, String> dataToAdd = new HashMap<>();

                dataToAdd.put("email", resetEmailVal);
                dataToAdd.put("password", databasePassword);
                dataToAdd.put("username", resetUserName);
                dataToAdd.put("full name", databaseName);
                dataToAdd.put("bio", databaseBio);
                dataToAdd.put("gender", databaseGender);
                dataToAdd.put("age", databaseAge);
                dataToAdd.put("height", databaseHeight);
                dataToAdd.put("weight", databaseWeight);




                Toast.makeText(Settings.this, "Your fucked" + resetEmailVal, Toast.LENGTH_SHORT).show();

                setUPRef2 = firestoreoreupdate.collection(loginType).document(resetUserName);
                setUPRef2.set(dataToAdd)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                setUPRef2.update("email", resetEmailVal);
                                setUPRef2.update("username", createUserId(resetEmailVal));
                                setUPRef2.update("password", databasePassword);
                                setUPRef2.update("full name", databaseName);
                                setUPRef2.update("bio", databaseBio);
                                setUPRef2.update("gender", databaseGender);
                                setUPRef2.update("age", databaseAge);
                                setUPRef2.update("height", databaseHeight);
                                setUPRef2.update("weight", databaseWeight);



                            }

                        });
                setUPRef.delete();
                setUPRef = setUPRef2;


            }
        });

        resetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassWordVal = resetPassWordInput.getText().toString().trim();
                setUPRef.update("password", resetPassWordVal);

            }
        });


        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount();
                Intent homepage = new Intent(Settings.this, LoginActivity.class);
                startActivity(homepage);

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
        getMenuInflater().inflate(R.menu.settings, menu);
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
            Intent home = new Intent(Settings.this, HomePage.class);
            startActivity(home);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_challenges) {
            Intent challenges = new Intent(Settings.this, Challenges.class);
            startActivity(challenges);
        } else if (id == R.id.nav_teams) {
            Intent teams = new Intent(Settings.this, Registration.class);
            startActivity(teams);
        } else if (id == R.id.nav_settings) {
            Intent settings = new Intent(Settings.this, Settings.class);
            startActivity(settings);
        } else if (id == R.id.nav_profile) {
            Intent profileView = new Intent(Settings.this, SetupProfile.class);
            startActivity(profileView);
        } else if (id == R.id.nav_leaderboards) {
            Intent leaderboards = new Intent(Settings.this, Registration.class);
            startActivity(leaderboards);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setUpVariables() {
        nightmode=(Switch) findViewById(R.id.sw_nightmode);
        wifi=(Switch)findViewById(R.id.wifi_ID);
        notifications=(Switch)findViewById(R.id.notifications_ID);
        resetPassWord = (Button) findViewById(R.id.ResetPassWordID);
        resetEmail = (Button) findViewById(R.id.ResetEmailID);
        deleteAccount = (Button) findViewById(R.id.DeleteID);
        resetPassWordInput = (EditText) findViewById(R.id.resetPassWordInfo);
        resetEmailInput = (EditText) findViewById(R.id.resetEmailInfo);
    }

    public void deleteAccount() {
        setUPRef.update("email", FieldValue.delete());
        setUPRef.update("password", FieldValue.delete());
        setUPRef.update("username", FieldValue.delete());
        setUPRef.update("full name", FieldValue.delete());
        setUPRef.update("bio", FieldValue.delete());
        setUPRef.update("gender", FieldValue.delete());
        setUPRef.update("age", FieldValue.delete());
        setUPRef.update("height", FieldValue.delete());
        setUPRef.update("weight", FieldValue.delete());
        setUPRef.delete();
    }

    private String createUserId(String email) {
        String UId;
        UId = email.substring(0, email.indexOf("@")).toLowerCase();  //trims part of email
        int digit = email.substring(email.indexOf("@") + 1, email.length()).length(); //number of chars after the @
        UId = UId + digit;     //adds two parts together

        return UId;


    }

}
