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


public class LoginActivity extends AppCompatActivity{
    private EditText userName, passWord;
    private Button register, login;
    private String userNameInput, passWordInput;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName=(EditText)findViewById(R.id.UserNameID);
        passWord=(EditText)findViewById(R.id.PassWordID);
        register=(Button)findViewById(R.id.RegisterID);
        login=(Button)findViewById(R.id.LogID);

        Auth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(LoginActivity.this, Registration.class);
                startActivity(registration);
            }
        });


       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                userNameInput=userName.getText().toString();// takes input
                passWordInput=passWord.getText().toString();// takes input

               // add firebase rehgistration
               Auth.signInWithEmailAndPassword(userNameInput,passWordInput)
                       .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful())
                               {
                                   Intent homepage = new Intent(LoginActivity.this, HomePage.class);
                                   startActivity(homepage);
                                   Toast.makeText(LoginActivity.this, "You're Logged In", Toast.LENGTH_SHORT).show();

                               }
                               else
                               {
                                   Toast.makeText(LoginActivity.this, "Failled to login In", Toast.LENGTH_SHORT).show();

                               }

                           }
                       });

           }
       });
    }



}

