package com.example.workclout;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity {
    // private FirebaseAuthException firebase;

    private static final String TAG = "Registration";
    private EditText userName2, passWord2, rePassWord;
    private String userNameInput2, passWordInput2, rePassWordInput;
    private Button register2;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName2 = (EditText) findViewById(R.id.UserNameID2);
        passWord2 = (EditText) findViewById(R.id.PassWordID2);
        //rePassWord=(EditText)findViewById(R.id.RePassWordID);
        register2 = (Button) findViewById(R.id.RegisterID2);

        firebaseAuth = FirebaseAuth.getInstance();

        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameInput2 = userName2.getText().toString().trim();// takes input
                passWordInput2 = passWord2.getText().toString().trim();// takes input


                firebaseAuth.createUserWithEmailAndPassword(userNameInput2, passWordInput2)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Athletes");
                                DatabaseReference ref;
                                FirebaseUser mCurrentUser;
                                if (task.isSuccessful()) {
                                    mCurrentUser= task.getResult().getUser();
                                    ref=mDatabase.child(mCurrentUser.getUid());
                                    ref.child("email").setValue(userNameInput2);
                                    ref.child("name").setValue(passWordInput2);
                                    Intent setUpProfile = new Intent(Registration.this, SetupProfile.class);
                                    startActivity(setUpProfile);
                                    Toast.makeText(Registration.this, "You're Registered", Toast.LENGTH_SHORT).show();

                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(Registration.this, "You're Already Registered", Toast.LENGTH_SHORT).show();

                                    } else {


                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Toast.makeText(Registration.this, "You're  not Registered " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }


                            }
                        });


            }
        });

    }
}
