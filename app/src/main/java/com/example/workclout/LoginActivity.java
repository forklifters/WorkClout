package com.example.workclout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;






public class LoginActivity extends AppCompatActivity{
    private EditText userName, passWord;
    private Button register, login;
    private String userNameInput, passWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName=(EditText)findViewById(R.id.UserNameID);
        passWord=(EditText)findViewById(R.id.PassWordID);
        register=(Button)findViewById(R.id.RegisterID);
        login=(Button)findViewById(R.id.LogID);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                //userNameInput=userName.getText().toString();// takes input
                //passWordInput=passWord.getText().toString();// takes input

               // add firebase rehgistration 
               if((userName.getText().toString().equals("John")&& passWordInput.equals("Fuck")))
               {
                   Intent homepage = new Intent(LoginActivity.this, HomePage.class);
                   startActivity(homepage);
                   Toast.makeText(LoginActivity.this, "You're login works", Toast.LENGTH_SHORT).show();


               }
               else
               {
                   Toast.makeText(LoginActivity.this, "You're login infromation does not match", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }



}

