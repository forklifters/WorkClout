package com.example.workclout;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {
   // private FirebaseAuthException firebase;
    private EditText userName, passWord, RePassWord;
    private String userNameInput, passWordInput, rePassWordInput;
    private Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName=(EditText)findViewById(R.id.UserNameID);
        passWord=(EditText)findViewById(R.id.PassWordID);
        passWord=(EditText)findViewById(R.id.PassWordID);
        register=(Button)findViewById(R.id.RegisterID);


        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameInput=userName.getText().toString();// takes input
                passWordInput=passWord.getText().toString();// takes input

                firebaseAuth.createUserWithEmailAndPassword(userNameInput,passWordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Registration.this, "You're login works", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

    }
}
