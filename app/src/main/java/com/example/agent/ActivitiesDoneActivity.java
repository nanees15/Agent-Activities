package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivitiesDoneActivity extends AppCompatActivity {

    Button BackToActivitiesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_done);


        BackToActivitiesBtn = findViewById(R.id.BackToActivitiesBtn);

        BackToActivitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackToActivitiesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(ActivitiesDoneActivity.this,Activities_Per_Day.class));
                        finish();
                    }
                });

            }
        });

    }
}