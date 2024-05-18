package com.example.agent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    Button login_btn;
    EditText phoneEditText;
    EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_btn = findViewById(R.id.login_btn);
        phoneEditText = findViewById(R.id.phone_inputField);
        Password = findViewById(R.id.password_inputField);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PhoneNumber = phoneEditText.getText().toString().trim();
                String PasswordText = Password.getText().toString().trim();
                if (PhoneNumber.isEmpty() || PasswordText.isEmpty()) {
                    // Show a message indicating invalid login
                    Toast.makeText(Login_Activity.this, "Invalid login. Please enter both phone number and password.", Toast.LENGTH_SHORT).show();
                }
                else {

                    startActivity(new Intent(Login_Activity.this, Activities_Calendar.class));
                    // Finish the current activity
                    finish();
                }


            }
        });
    }
}