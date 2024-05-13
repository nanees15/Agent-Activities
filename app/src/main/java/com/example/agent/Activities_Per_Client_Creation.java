package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;


public class Activities_Per_Client_Creation extends AppCompatActivity {
    ImageButton PreviousPage;
    TextView ShowDate;
    Button DateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_per_client_creation);

        PreviousPage = findViewById(R.id.previouspage);
        DateBtn = findViewById(R.id.DateBtn);
        ShowDate = findViewById(R.id.ShowText);

        DateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });

        PreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activities_Per_Client_Creation.this, Activities_Per_Client.class));
                finish();
            }
        });

    }
    private void OpenDialog(){
        DatePickerDialog dialog  = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ShowDate.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
            }
        }, 2024, 0, 15);
    }
}