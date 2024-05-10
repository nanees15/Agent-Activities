package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity_Details extends AppCompatActivity {

    Intent intent;
    TextView date_textView;
    TextView client_textView;
    TextView activity_textView;
    TextView activity_status;
    TextView time_textview;
    ImageButton back_btn;
    String client_name;
    String time;
    String date;
    String activity_name;
    String activity_status_text;

    Button check_inBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        date_textView = findViewById(R.id.date);
        client_textView = findViewById(R.id.clientName_ID);
        back_btn = findViewById(R.id.back);
        activity_textView = findViewById(R.id.ActivityName_ID);
        time_textview = findViewById(R.id.timeIDTextview);
        activity_status = findViewById(R.id.statusId);
        check_inBtn = findViewById(R.id.checkIn_btn);
        intent = getIntent();
        if (intent != null) {
            client_name = intent.getStringExtra("clientName");
            activity_name = intent.getStringExtra("activityName");
            activity_status_text = intent.getStringExtra("activityStatus");
            time = intent.getStringExtra("time");
            date = intent.getStringExtra("date");
            // Use the data as needed

            date_textView.setText(date);
            client_textView.setText(client_name);
            time_textview.setText(time);
            activity_textView.setText(activity_name);
            activity_status.setText((activity_status_text));
        }


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        check_inBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Activity_Report_Details.class);
                startActivity(i);
            }
        });
    }
}