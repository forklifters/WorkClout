package com.example.workclout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 1800); //1800 is the timeout for the splash

        userName=(EditText)findViewById(R.id.et_username);
        passWord=(EditText)findViewById(R.id.et_password);
        register=(Button)findViewById(R.id.btn_register);
        login=(Button)findViewById(R.id.btn_login);

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

               // add firebase registration
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
