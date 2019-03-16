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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;


public class Registration extends AppCompatActivity {
   // private FirebaseAuthException firebase;
    private EditText userName2, passWord2, rePassWord;
    private String userNameInput2, passWordInput2, rePassWordInput;
    private Button register2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName2=(EditText)findViewById(R.id.UserNameID2);
        passWord2=(EditText)findViewById(R.id.PassWordID2);
        //rePassWord=(EditText)findViewById(R.id.RePassWordID);
        register2=(Button)findViewById(R.id.RegisterID2);

        firebaseAuth = FirebaseAuth.getInstance();


        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameInput2=userName2.getText().toString().trim();// takes input
                passWordInput2=passWord2.getText().toString().trim();// takes input

                firebaseAuth.createUserWithEmailAndPassword(userNameInput2,passWordInput2)
                        .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {


                            Intent homepage = new Intent(Registration.this, HomePage.class);
                            startActivity(homepage);
                            Toast.makeText(Registration.this, "You're registered", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Toast.makeText(Registration.this, "You're not registered " +e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });

            }
        });

    }
}
