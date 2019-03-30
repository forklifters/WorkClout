package com.example.workclout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Invitetochallenge extends AppCompatActivity {
    private EditText person1,person2,person3,person4,person5,person6,person7,person8,person9,person10;
    private Button invite;
    private FirebaseFirestore firestore;
    private DocumentReference inviteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitetochallenge);

    }

    public void set_up_variables()
    {
        firestore = FirebaseFirestore.getInstance();
        inviteRef=firestore.collection("Challenges").document()
        person1=(EditText) findViewById(R.id.Player_1ID);
        person2=(EditText) findViewById(R.id.Player_2ID);
        person3=(EditText) findViewById(R.id.Player_3ID);
        person4=(EditText) findViewById(R.id.Player_4ID);
        person5=(EditText) findViewById(R.id.Player_5ID);
        person6=(EditText) findViewById(R.id.Player_6ID);
        person7=(EditText) findViewById(R.id.Player_7ID);
        person8=(EditText) findViewById(R.id.Player_8ID);
        person9=(EditText) findViewById(R.id.Player_9ID);
        person10=(EditText) findViewById(R.id.Player_10ID);
        invite=(Button)findViewById(R.id.Invite_ID);

    }
}
