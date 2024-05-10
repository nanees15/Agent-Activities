package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Client_List extends AppCompatActivity {

    private List<MyItem> clientList;
    RecyclerView clients_Recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        clients_Recyclerview = findViewById(R.id.clients_recyclerView);

        // Create a list of items
        clientList = new ArrayList<>();

// Add items manually
        clientList.add(new MyItem("9:00 صباحا", "الاهلي ممكن"));
        clientList.add(new MyItem("10:30 صباحا", "البنك الاهلي"));
// Add more items as needed

// Create an adapter with the list of items
        Clients_Adapter adapter = new Clients_Adapter(this, clientList);

// Set the adapter to the RecyclerView

        clients_Recyclerview.setAdapter(adapter);

// Optionally, set a layout manager to the RecyclerView
        clients_Recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}