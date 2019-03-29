package com.example.workclout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CoachTab extends AppCompatActivity {
    private Button viewMyTeamButton,viewMyChallengesButton,createYourTeamButton,createYourChallengesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_tab);


        viewMyChallengesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent mychallenges= new Intent(CoachTab.this,MyChallenges.class);
                //startActivity(mychallenges);

            }
        });
        viewMyTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent myTeam= new Intent(CoachTab.this,MyTeams.class);
                //startActivity(myTeam);

            }
        });
        createYourTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent createYourTeam= new Intent(CoachTab.this,CreateYourTeam.class);
                //startActivity(createYourTeam);

            }
        });
        createYourChallengesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createYourChallenges= new Intent(CoachTab.this,CreateChallenge.class);
                startActivity(createYourChallenges);

            }
        });
    }


    public void setUpVariables()
    {
        viewMyTeamButton=(Button)findViewById(R.id.ViewMyTeamID);
        viewMyChallengesButton=(Button)findViewById(R.id.ViewMyChallengesID);
        createYourTeamButton=(Button)findViewById(R.id.CreateYourTeamID);
        createYourChallengesButton=(Button)findViewById(R.id.CreateYourChallengesID);
    }
}
