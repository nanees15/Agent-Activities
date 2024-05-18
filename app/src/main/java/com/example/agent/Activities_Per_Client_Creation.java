package com.example.agent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;


public class Activities_Per_Client_Creation extends AppCompatActivity {
    ImageButton PreviousPage;
    CalendarView CalenderCreation;
    Button Finish;
    Button SelectTime;
    public Calendar selectedDate;
    public Calendar now ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_per_client_creation);

        PreviousPage = findViewById(R.id.previouspage);
        CalenderCreation = findViewById(R.id.CalendarInActivitiesCreation);
        Finish = findViewById(R.id.FinishingBtn);
        SelectTime =findViewById(R.id.TimeButton);


        Locale arabicLocale = new Locale("ar");
        Locale.setDefault(arabicLocale);
        Configuration config = new Configuration();
        Locale.Builder localBuilder = new Locale.Builder();
        config.setLocale(localBuilder.setLocale(arabicLocale).build());

        // Update the configuration for the application context
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        selectedDate = Calendar.getInstance();

        CalenderCreation.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                // Create a Calendar instance and set the selected date
                selectedDate.set(year, month, dayOfMonth);



            }
        });


        SelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = selectedDate.get(Calendar.HOUR_OF_DAY);
                int minute = selectedDate.get(Calendar.MINUTE);
                new TimePickerDialog(Activities_Per_Client_Creation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Set the selected time
                        selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDate.set(Calendar.MINUTE, minute);
                    }
                }, hour, minute, true).show();
            }
        });



        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            now = Calendar.getInstance();
                if(selectedDate.get(Calendar.YEAR) == now.get(Calendar.YEAR) &&
                        selectedDate.get(Calendar.MONTH) == now.get(Calendar.MONTH) &&
                        selectedDate.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH))
                {
                    // Create an intent to navigate to the next activity
                    Intent intent = new Intent(Activities_Per_Client_Creation.this, Activity_Details.class);

                    // Pass the selected date as an extra to the intent
                    intent.putExtra("selectedDate", selectedDate.getTimeInMillis());

                    // Start the next activity
                    startActivity(intent);

                }
                else
                {
                    // Hro7 Feen ?
                    Intent intent = new Intent(Activities_Per_Client_Creation.this, Activities_Per_Client.class);

                    // Pass the selected date as an extra to the intent
                    intent.putExtra("selectedDate", selectedDate.getTimeInMillis());

                    // Start the next activity
                    startActivity(intent);






                }



            }
        });









        }

}