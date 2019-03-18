package com.example.workclout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SetupProfile extends AppCompatActivity {
    private EditText bio, weight, height, gender,fullName, age;
    private Button setup;
    private String bioInput, fullNameInput, genderInput, currentUserID;
    private double weightInput, heightInput;
    private int ageInput;
    private FirebaseAuth update;
    //private DatabaseReference profileUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_profile);
        update = FirebaseAuth.getInstance();
        bio= (EditText) findViewById(R.id.BioId);
        age =(EditText) findViewById(R.id.AgeID);
        height= (EditText) findViewById(R.id.HeightId);
        weight= (EditText) findViewById(R.id.WeightID);
        fullName= (EditText) findViewById(R.id.FullNameID);
        setup=(Button) findViewById(R.id.ProfileID);
        //currentUserID= update.getCurrentUser().getUid();
        //profileUserID= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);


        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bioInput=bio.getText().toString().trim();
                //ageInput=bio.getText().toString().trim();
                fullNameInput=fullName.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                bioInput=bio.getText().toString().trim();
                FirebaseUser account = update.getCurrentUser();
                if(account!=null)
                {
                    UserProfileChangeRequest profile =  new UserProfileChangeRequest.Builder()
                            .setDisplayName(fullNameInput)
                            .build();
                    account.updateProfile(profile)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SetupProfile.this, "Your profile is updated" , Toast.LENGTH_SHORT).show();
                                        Intent saveSuccess = new Intent(SetupProfile.this, HomePage.class);
                                        startActivity(saveSuccess);
                                    }
                                    else
                                    {
                                        FirebaseAuthException e = (FirebaseAuthException )task.getException();
                                        Toast.makeText(SetupProfile.this, "Your changes could not be saved" +e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });

                }



            }
        });

    }


}
