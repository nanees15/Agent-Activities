package com.example.agent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Activity_Report_Details extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    ImageButton VoiceBtn;
    ImageView Photo;
    FloatingActionButton CameraButton ;

    EditText NotesEditText;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        VoiceBtn = findViewById(R.id.voice_btn);
        test = findViewById(R.id.textView4);
        NotesEditText= findViewById(R.id.NotesEditText);
        Photo = findViewById(R.id.photoImageView);
        CameraButton = findViewById(R.id.floatingActionButton);
//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.white)));

        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(Activity_Report_Details.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });





        VoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        Photo.setImageURI(uri);
        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && null!=data){
                    //Get Text Array From voice intent
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    test.setText(result.get(0));
                }
            }
        }
    }

    private void speak() {

        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT," تحدث");

        try {
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);


        }catch (Exception e)
        {
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    //Receive Voice Input & Handle It


}