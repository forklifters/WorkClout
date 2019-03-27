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

    private EditText challengeT, challengeDif, challengeL, challengeDes;
    private String challengeTitle, challengeDescription, challengeID;
    private int challengeDifficulty, challengeLength;
    private Button createChallenge;
    private DocumentReference challengeRef;
    private FirebaseFirestore mFirestore;



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

        mFirestore = FirebaseFirestore.getInstance();

        createChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                challengeTitle = challengeT.getText().toString();
                challengeDifficulty = Integer.parseInt(challengeDif.getText().toString());
                challengeLength = Integer.parseInt(challengeL.getText().toString());
                challengeDescription = challengeDes.getText().toString();
                challengeID = createChallengeID(challengeTitle, challengeDifficulty, challengeLength);

                Map<String, Object> dataToAdd = new HashMap<>();

                dataToAdd.put("title", challengeTitle);
                dataToAdd.put("difficulty", challengeLength);
                dataToAdd.put("length", challengeLength);
                dataToAdd.put("description", challengeDescription);
                dataToAdd.put("challengeID", challengeID);

                mFirestore.collection("challenges").document(challengeID).set(dataToAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CreateChallenge.this, "Username added", Toast.LENGTH_SHORT).show();

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
