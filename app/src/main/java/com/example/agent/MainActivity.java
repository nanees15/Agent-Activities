package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay for 5 seconds
        int delayMillis = 5000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the other activity
                startActivity(new Intent(MainActivity.this, Login_Activity.class));
                // Finish the current activity
                finish();
            }
        }, delayMillis);
    }
}