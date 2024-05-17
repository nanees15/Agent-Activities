package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activities_Per_Client extends AppCompatActivity {

    RecyclerView activities_List;
    ImageButton back_btn;
    TextView activities_for_client;
    Button AddActivityBtn;
    Intent intent;
    String client_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_per_client);

        activities_List = findViewById(R.id.activities_recyclerInclient);
        back_btn = findViewById(R.id.back);
        activities_for_client = findViewById(R.id.client_name5);
        AddActivityBtn = findViewById(R.id.add_activity_btn);

        AddActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activities_Per_Client.this, Activities_Per_Client_Creation.class));
                finish();
            }
        });



        intent = getIntent();
        if (intent != null) {
            client_name = intent.getStringExtra("clientName");

            activities_for_client.setText("أنشطة " + client_name);
        }

        // Create a list of items
        List<MyItem> itemList = new ArrayList<>();

// Add items manually
        itemList.add(new MyItem("9:00 صباحا", "اجتماع","الاهلي ممكن" ,"مجدولة"));
        itemList.add(new MyItem("10:30 صباحا", "تفتيش","البنك الاهلي" ,"مجدولة"));
// Add more items as needed

// Create an adapter with the list of items
        Activities_Adapter adapter = new Activities_Adapter(this, itemList);

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