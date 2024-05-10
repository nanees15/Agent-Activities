package com.example.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.Locale;

public class Activities_Calendar extends AppCompatActivity {


    CalendarView calendar;
    ImageButton goto_clients_Btn;
    ImageButton goto_history_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_calendar);

        calendar = findViewById(R.id.Calendar);
        goto_clients_Btn = findViewById(R.id.goto_clientsBtn1);
        goto_history_Btn = findViewById(R.id.goto_historyBtn);

        Locale arabicLocale = new Locale("ar");
        Locale.setDefault(arabicLocale);

        Configuration config = new Configuration();
        Locale.Builder localBuilder = new Locale.Builder();
        config.setLocale(localBuilder.setLocale(arabicLocale).build());

        // Update the configuration for the application context
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Create a Calendar instance and set the selected date
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                // Create an intent to navigate to the next activity
                Intent intent = new Intent(Activities_Calendar.this, Activities_Per_Day.class);

                // Pass the selected date as an extra to the intent
                intent.putExtra("selectedDate", selectedDate.getTimeInMillis());

                // Start the next activity
                startActivity(intent);
            }
        });

        goto_clients_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activities_Calendar.this, Client_List.class);

                startActivity(intent);
                finish();
            }
        });

        goto_history_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activities_Calendar.this, Activities_History.class);
                startActivity(intent);
                finish();
            }
        });

    }
}