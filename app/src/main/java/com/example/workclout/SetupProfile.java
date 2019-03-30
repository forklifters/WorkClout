package com.example.workclout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class SetupProfile extends AppCompatActivity {
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

        setContentView(R.layout.activity_setup_profile);
        firestoreoreupdate = FirebaseFirestore.getInstance();



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

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameInput=fullName.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                ageInput=age.getText().toString().trim();
                heightInput=height.getText().toString().trim();
                weightInput =weight.getText().toString().trim();
                genderInput=gender.getText().toString().trim();
                setUPRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        databaseName = documentSnapshot.getString("Name");
                        databaseBio = documentSnapshot.getString("bio");
                        databaseGender = documentSnapshot.getString("gender");
                        databaseAge = documentSnapshot.getString("age");
                        databaseHeight = documentSnapshot.getString("height");
                        databaseWeight = documentSnapshot.getString("weight");
                        fullName.setHint(databaseName);
                        bio.setHint(databaseBio);
                        gender.setHint(databaseGender);
                        age.setHint(databaseAge);
                        height.setHint(databaseName);
                        weight.setHint(databaseName);

                    }
                });

                update();
                Intent loginSuccess = new Intent(SetupProfile.this, Settings.class);
                startActivity(loginSuccess);


            }
        });

    }
                public void update() {
                    setUPRef.update("Name", fullNameInput);
                    setUPRef.update("bio", bioInput);
                    setUPRef.update("gender", genderInput);
                    setUPRef.update("age", ageInput);
                    setUPRef.update("height", heightInput);
                    setUPRef.update("weight", weightInput);
                    Toast.makeText(SetupProfile.this, "Your profile is updated",Toast.LENGTH_SHORT).show();
                }
}





