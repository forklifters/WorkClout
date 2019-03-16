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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity {
    private EditText userName2, passWord2, rePassWord;
    private String userNameInput2, passWordInput2, rePassWordInput;
    private Button register2;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName2=findViewById(R.id.UserNameID2);
        passWord2=findViewById(R.id.PassWordID2);
        register2=findViewById(R.id.RegisterID2);

        mFirestore = FirebaseFirestore.getInstance();


        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameInput2 = userName2.getText().toString();// takes input
                passWordInput2 = passWord2.getText().toString();// takes input

                Map<String, String> userMap = new HashMap<>();

                userMap.put("username", userNameInput2);
                userMap.put("password", passWordInput2);

                mFirestore.collection("users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(Registration.this, "Username added", Toast.LENGTH_SHORT).show();
                        Intent loginSuccess = new Intent(Registration.this, HomePage.class);
                        startActivity(loginSuccess);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        String error = e.getMessage();

                        Toast.makeText(Registration.this, "Error : " + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
