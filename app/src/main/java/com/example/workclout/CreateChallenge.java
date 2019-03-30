package com.example.workclout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateChallenge extends AppCompatActivity {

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
        challengeT=findViewById(R.id.challengeTitle);
        challengeDif=findViewById(R.id.challengeDifficulty);
        challengeL=findViewById(R.id.challengeLength);
        challengeDes=findViewById(R.id.challengeDescription);
        createChallenge=findViewById(R.id.createChallengeButton);
        activity_1=findViewById(R.id.Activity_1ID);
        activity_2=findViewById(R.id.Activity_2ID);
        activity_3=findViewById(R.id.Activity_3ID);

        mFirestore = FirebaseFirestore.getInstance();
        UID=x.get_user_id();

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
                        Toast.makeText(CreateChallenge.this, "Username added", Toast.LENGTH_SHORT).show();
                        mFirestore.collection("CoachesChallenges").document(UID).set(challengeID);
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


}
