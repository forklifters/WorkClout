package com.example.workclout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Verify extends AppCompatActivity {

    private EditText code;
    private Button verify, resend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        code = (EditText) findViewById(R.id.et_verify);
        verify = (Button) findViewById(R.id.btn_verify);
        resend = (Button) findViewById(R.id.btn_resend);

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: logic for resending verify code here
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: logic for checking verify code here

                Intent loginSuccess = new Intent(Verify.this, SetupProfile.class);
                startActivity(loginSuccess);
            }
        });
    }

}
