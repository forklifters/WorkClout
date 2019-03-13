package com.example.workclout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Registration extends AppCompatActivity {
    private EditText et_username, et_email, et_password;
    private String username, email, password;
    Button regbtn;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);
        et_username = (EditText) findViewById(R.id.UsernameID);
        et_email = (EditText) findViewById(R.id.EmailID);
        et_password = (EditText) findViewById(R.id.PasswordID);
        regbtn = (Button) findViewById(R.id.RegisterButton);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        String username = et_username.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT);
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT);
            return;
        }
        progressDialog.setMessage("Registering...");
        progressDialog.show();
    }

}
