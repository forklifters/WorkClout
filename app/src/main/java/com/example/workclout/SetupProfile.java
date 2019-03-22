package com.example.workclout;

import android.content.Intent;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class SetupProfile extends AppCompatActivity {
    private EditText bio, weight, height, gender, fullName, age;
    private Button setup;
    private String bioInput, fullNameInput, ageInput, heightInput, weightInput, loginType, UID,genderInput;
    private FirebaseFirestore firestoreoreupdate;
    private DocumentReference setUPRef;

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
        gender=(EditText) findViewById(R.id.GenderID);
        setup = (Button) findViewById(R.id.ProfileID);
        Bundle getBundle= new Bundle();
        getBundle=getIntent().getExtras();
        UID =getBundle.getString("userID");
        loginType=getBundle.getString("accountType");

        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullNameInput=fullName.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                ageInput=age.getText().toString().trim();
                heightInput=height.getText().toString().trim();
                weightInput =weight.getText().toString().trim();
                genderInput=gender.getText().toString().trim();



                Map<String, String> dataToUpdate = new HashMap<>();
                dataToUpdate.put("full name",fullNameInput);
                dataToUpdate.put("bio",bioInput);
                dataToUpdate.put("gender", genderInput);
                dataToUpdate.put("age" ,ageInput);
                dataToUpdate.put("height" ,heightInput);
                dataToUpdate.put("weight" ,weightInput);

                update();




            }
        });

    }
                public void update() {
                    setUPRef = firestoreoreupdate.collection(loginType).document(UID);
                    setUPRef.update("Name", fullNameInput);
                    setUPRef.update("bio", bioInput);
                    setUPRef.update("gender", genderInput);
                    setUPRef.update("age", ageInput);
                    setUPRef.update("height", heightInput);
                    setUPRef.update("weight", weightInput);
                    Toast.makeText(SetupProfile.this, "Your profile is updated",Toast.LENGTH_SHORT).show();


                }
}





