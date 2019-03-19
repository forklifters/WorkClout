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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;


public class LoginActivity extends AppCompatActivity{
    private EditText userName, passWord;
    private Button register, login;
    private String userNameInput, passWordInput;
    private FirebaseFirestore firestore;
    private CheckBox coachregister;
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

        userName=(EditText)findViewById(R.id.et_username);
        passWord=(EditText)findViewById(R.id.et_password);
        register=(Button)findViewById(R.id.btn_register);
        login=(Button)findViewById(R.id.btn_login);
        coachregister=(CheckBox) findViewById(R.id.checkID);

         firestore= FirebaseFirestore.getInstance();
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

               String choice = "athletes";
               if (coachregister.isChecked()) {
                   choice = "coaches";
               }
               firestore.collection(choice).document("login").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                       if (task.isSuccessful()) {
                           DocumentSnapshot documentSnapshot = task.getResult();
                           String databaseUserName = documentSnapshot.getString("username");
                           String databasePassWord = documentSnapshot.getString("password");
                           userNameInput = userName.getText().toString();// takes input
                           passWordInput = passWord.getText().toString();// takes input

                           if (userNameInput.equals(databaseUserName) && passWordInput.equals(databasePassWord)) {

                               Toast.makeText(LoginActivity.this, "You're Logged In", Toast.LENGTH_SHORT).show();
                               //loginSuccess = true;
                               move();
                           } else {
                               Toast.makeText(LoginActivity.this, "Failed to Connect Login", Toast.LENGTH_SHORT).show();

                           }
                       } else {
                           Toast.makeText(LoginActivity.this, "Failed to Connect Database", Toast.LENGTH_SHORT).show();

                       }


                   }
               });
           }
       });
    }
    public void move()
    {
        Intent homepage = new Intent(LoginActivity.this, SetupProfile.class);
        startActivity(homepage);
    }


}
