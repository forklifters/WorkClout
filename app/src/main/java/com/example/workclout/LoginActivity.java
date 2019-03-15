package com.example.workclout;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity{

    //temp change 1
    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    //

    private EditText userName, passWord;
    private Button register, login;
    private String userNameInput, passWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen); //temp change 3

        //temp change 2
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 1800); //1800 is the timeout for the splash
        //

        userName=(EditText)findViewById(R.id.et_username);
        passWord=(EditText)findViewById(R.id.et_password);
        register=(Button)findViewById(R.id.btn_register);
        login=(Button)findViewById(R.id.btn_login);


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

                //userNameInput=userName.getText().toString();// takes input
                //passWordInput=passWord.getText().toString();// takes input

               // add firebase registration
               if((userName.getText().toString().equals("John")&& passWordInput.equals("Fuck"))) //watch your profanity
               {
                   Intent homepage = new Intent(LoginActivity.this, HomePage.class);
                   startActivity(homepage);
                   Toast.makeText(LoginActivity.this, "Your login works", Toast.LENGTH_SHORT).show();


               }
               else
               {
                   Toast.makeText(LoginActivity.this, "Your login information does not match", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }



}

