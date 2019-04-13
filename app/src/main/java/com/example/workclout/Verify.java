package com.example.workclout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Verify extends AppCompatActivity {

    private EditText code;
    private Button verify, resend;
    public helperClass x = new helperClass();
    private String verifyCode;

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
                String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                Random random = new Random();
                StringBuilder builder = new StringBuilder(6);
                for (int i = 0; i < 6; i++) {
                    builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
                }

                verifyCode = builder.toString();
                Toast.makeText(Verify.this, verifyCode, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{x.getEmail()});
                i.putExtra(Intent.EXTRA_SUBJECT, "Welcome to WorkClout");
                i.putExtra(Intent.EXTRA_TEXT   , "Your verification code is:" + "\n" + "\n" + verifyCode);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Verify.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.getText().toString().equals(verifyCode)) {
                    Intent loginSuccess = new Intent(Verify.this, SetupProfile.class);
                    startActivity(loginSuccess);
                }
                else {
                    Toast.makeText(Verify.this, "Entered code is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
