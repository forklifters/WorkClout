package com.example.workclout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;




public class LoginActivity extends AppCompatActivity {
    private EditText userName, passWord;
    private Button register, login, forgotPass;

    private String emailInput, passWordInput, userID, databaseEmail, databasePassWord, accountType;
    private CheckBox coachLogin, coachRegister;
    private FirebaseFirestore firestore;
    private DocumentReference loginRef;
    private boolean loginSuccess = false;
    public helperClass x = new helperClass();

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

        //TODO: Error handle for when userID already exists and increment by one
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
        variables();
        turn_on_night_mode();


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

        /********************************************
         * Benjamin Napier
         * Function:
         * If forgot password button clicked switch to forgot password class
         *****************************************/
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
         * If the login is successful switch pages to Homepage
         * Prompt for successful Login
         * else
         * Prompt for unsuccessful
         * page does not switch
         */
        coachLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountType="coaches";

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                emailInput = userName.getText().toString();// takes input
                passWordInput = passWord.getText().toString();// takes input
                userID = getUserId(emailInput);
                x.set_user_id(userID);
                x.set_login_type(accountType);
                loginRef = firestore.collection(accountType).document(userID);

                loginRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            databaseEmail = documentSnapshot.getString("email");
                            databasePassWord = documentSnapshot.getString("password");


                            if (emailInput.equals(databaseEmail) && passWordInput.equals(databasePassWord)) {
                                Toast.makeText(LoginActivity.this, "You're Logged In", Toast.LENGTH_SHORT).show();

                                Intent homePage = new Intent(LoginActivity.this, HomePage.class);
                                homePage.putExtra("userID", userID);
                                homePage.putExtra("accountType", accountType);
                                startActivity(homePage);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Failed to Connect Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login failed renter username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        });
    }

    public void variables()
    {
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 1800); //1800 is the timeout for the splash
        userName = (EditText) findViewById(R.id.et_username);
        passWord = (EditText) findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.btn_register);
        login = (Button) findViewById(R.id.btn_login);
        forgotPass = (Button) findViewById(R.id.btn_resetPassword);
        coachLogin=(CheckBox) findViewById(R.id.cb_coachLogin);
        accountType = "athletes";
        firestore = FirebaseFirestore.getInstance();

    }

    public void turn_on_night_mode()
    {
        if(x.get_lights_on()==true)
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }
        else
        {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }
}
