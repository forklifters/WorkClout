package com.example.workclout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SetupProfile extends AppCompatActivity {
    private EditText bio, weight, height, gender, fullName, age;
    private Button setup;
    private String bioInput, fullNameInput, ageInput, heightInput, weightInput;
    private FirebaseFirestore firestoreoreupdate;

    //private DatabaseReference profileUserID;


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
        setup = (Button) findViewById(R.id.ProfileID);

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameInput=fullName.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                ageInput=age.getText().toString().trim();
                heightInput=height.getText().toString().trim();
                weightInput =weight.getText().toString().trim();



                Map<String, String> dataToUpdate = new HashMap<>();
                dataToUpdate.put("full name",fullNameInput);
                dataToUpdate.put("bio",bioInput);
                dataToUpdate.put("age" ,ageInput);
                dataToUpdate.put("height" ,heightInput);
                dataToUpdate.put("weight" ,weightInput);


                firestoreoreupdate.collection("athletes").document("login").collection("settings").document("bio")
                        .set(dataToUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent homepage = new Intent(SetupProfile.this, LoginActivity.class);
                        startActivity(homepage);

                    }
                });


            }
        });

    }
}





