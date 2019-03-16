package com.example.workclout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
        userName2 = (EditText) findViewById(R.id.UserNameID2);
        passWord2 = (EditText) findViewById(R.id.PassWordID2);
        //rePassWord=(EditText)findViewById(R.id.RePassWordID);
        register2 = (Button) findViewById(R.id.RegisterID2);

        firebaseAuth = FirebaseAuth.getInstance();


        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameInput2 = userName2.getText().toString().trim();// takes input
                passWordInput2 = passWord2.getText().toString().trim();// takes input
                boolean valid = true;

                if (passWordInput2.length() < 8) {
                    valid = false;
                    Toast.makeText(Registration.this, "Password must be 8 characters or more", Toast.LENGTH_SHORT).show();
                }

                boolean containsCapital = false;
                for (int i = 0; i < passWordInput2.length(); i++) {
                    if (Character.isUpperCase(passWordInput2.charAt(i))) {
                        containsCapital = true;
                    }
                }

                if (!containsCapital) {
                    valid = false;
                    Toast.makeText(Registration.this, "Password must contain a capital letter", Toast.LENGTH_SHORT).show();
                }

                boolean containsInt = false;
                for (int i = 0; i < passWordInput2.length(); i++) {
                    if (passWordInput2.substring(i, i + 1).matches("[0-9]")) {
                        containsInt = true;
                    }
                }

                if (!containsInt) {
                    valid = false;
                    Toast.makeText(Registration.this, "Password must contain a number", Toast.LENGTH_SHORT).show();
                }

                if (valid) {
                    firebaseAuth.createUserWithEmailAndPassword(userNameInput2, passWordInput2)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // firebaseAuth.signInWithEmailAndPassword(userNameInput2,passWordInput2);
                                        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser("Athletes");

                                        // DatabaseReference mdatabase =FirebaseDatabase.getInstance().getReference().child("Athletes");
                                        // DatabaseReference cdatabase = mdatabase.child(firebaseAuth.getCurrentUser().getUid());
                                        //FirebaseDatabase.getInstance().getReference("workclout-2aebd");


                                        Intent setUpProfile = new Intent(Registration.this, SetupProfile.class);
                                        startActivity(setUpProfile);
                                        Toast.makeText(Registration.this, "You're Registerd", Toast.LENGTH_SHORT).show();

                                    } else {
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Toast.makeText(Registration.this, "You're  not Registerd " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });
                }


            }
        });

    }
}
