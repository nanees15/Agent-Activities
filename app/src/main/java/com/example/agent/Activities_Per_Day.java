package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Activities_Per_Day extends AppCompatActivity {

    TextView date;
    RecyclerView activities_List;
    ImageButton back_btn;
    String formattedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_per_day);

        date = findViewById(R.id.date);
        activities_List = findViewById(R.id.activities_recyclerview);
        back_btn = findViewById(R.id.back);

        Locale arabicLocale = new Locale("ar");
        Locale.setDefault(arabicLocale);

        Configuration config = new Configuration();
        Locale.Builder localBuilder = new Locale.Builder();
        config.setLocale(localBuilder.setLocale(arabicLocale).build());

        // Update the configuration for the application context
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());



        long selectedDateInMillis = getIntent().getLongExtra("selectedDate", 0);
        if (selectedDateInMillis != 0) {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.setTimeInMillis(selectedDateInMillis);

            Log.d("SelectedDate", "Selected Date (milliseconds): " + selectedDateInMillis);

            // Format the selected date string
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            formattedDate = sdf.format(selectedDate.getTime());

            Log.d("SelectedDate", "Formatted Date: " + formattedDate);

            // Set the formatted date string to the TextView
            date.setText(formattedDate);
        } else {
            date.setText("No date selected");
        }

        // Create a list of items
        List<MyItem> itemList = new ArrayList<>();

// Add items manually
        itemList.add(new MyItem("9:00 صباحا", "اجتماع","الاهلي ممكن" ,"مجدول"));
        itemList.add(new MyItem("10:30 صباحا", "تفتيش","البنك الاهلي" ,"مجدول"));
// Add more items as needed

// Create an adapter with the list of items
        Activities_Adapter adapter = new Activities_Adapter(this, itemList, formattedDate);

// Set the adapter to the RecyclerView

        activities_List.setAdapter(adapter);

// Optionally, set a layout manager to the RecyclerView
        activities_List.setLayoutManager(new LinearLayoutManager(this));


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}