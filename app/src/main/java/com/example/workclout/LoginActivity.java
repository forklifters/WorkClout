package com.example.workclout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;


public class LoginActivity extends AppCompatActivity {
    private EditText userName, passWord;
    private Button register, login, forgotPass;
    private String emailInput, passWordInput, userID, databaseEmail, databasePassWord;
    private FirebaseFirestore firestore;
    private DocumentReference loginRef;
    //private CheckBox coachregister;
    private boolean loginSuccess = false;

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };


    private String getUserId(String email) {
        String UId;
        UId = email.substring(0, email.indexOf("@")).toLowerCase();  //trims part of email
        int digit = email.substring(email.indexOf("@") + 1, email.length()).length(); //number of chars after the @
        UId = UId + digit;     //adds two parts together

        return UId;

        //TODO: Error handle for when userID already exists and incriment by one
    }

    /********************************************
     * Nathan Lieu
     * Function:
     * assign all varaibles a value refering to their Id's
     * create an object of firebase database
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 1800); //1800 is the timeout for the splash

        userName = (EditText) findViewById(R.id.et_username);
        passWord = (EditText) findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.btn_register);
        login = (Button) findViewById(R.id.btn_login);
        forgotPass = (Button) findViewById(R.id.btn_resetPassword);
        //coachregister=(CheckBox) findViewById(R.id.checkID);

        firestore = FirebaseFirestore.getInstance();
        /********************************************
         * Nathan Lieu
         * Function:
         * If register button clicked switch to registration class
         *****************************************/

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(LoginActivity.this, Registration.class);
                startActivity(registration);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(forgotPassword);
            }
        });


        /********************************************
         * Nathan Lieu
         * Function:
         * If login button clicked take in Username and Password inputs
         * firebase object connects to database
         * If the login is succesful switch pages to Homepage
         * Prompt for successful Login
         * else
         * Prompt for unsuccessful
         * page does not switch
         */


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String accountType = "athletes";
//               if (coachregister.isChecked()){
//                   accountType="coaches";
//               }
                emailInput = userName.getText().toString();// takes input
                passWordInput = passWord.getText().toString();// takes input
                userID = getUserId(emailInput);

                //TODO search through collection for repetition of login
                loginRef = firestore.collection(accountType).document(userID);

                loginRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Toast.makeText(LoginActivity.this, "Database exist", Toast.LENGTH_SHORT).show();
                            databaseEmail = documentSnapshot.getString("email");
                            databasePassWord = documentSnapshot.getString("password");

                            if (emailInput.equals(databaseEmail) && passWordInput.equals(databasePassWord)) {
                                Toast.makeText(LoginActivity.this, "You're Logged In", Toast.LENGTH_SHORT).show();

                                Intent homePageSwitch = new Intent(LoginActivity.this, HomePage.class);
                                startActivity(homePageSwitch);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Failed to Connect Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Database not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });
    }
}
