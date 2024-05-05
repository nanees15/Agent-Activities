package com.example.agent;
import android.Manifest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Activity_Report_Details extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private MediaRecorder mediaRecorder ;
    private boolean isRecording = false;

    private MediaPlayer mediaPlayer;

    private  String AudioSavedPath = null;
    ImageButton VoiceBtn;
    Button Forward;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        VoiceBtn = findViewById(R.id.voice_btn);
        test = findViewById(R.id.textView4);
        Forward = findViewById(R.id.RegisterLeavingBtn);

        mediaRecorder =  new MediaRecorder();
        Forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Report_Details.this, ActivitiesDoneActivity.class));
                // Finish the current activity
                finish();
            }
        });

        VoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                speak();


               if(!isRecording)
               {
                   startRecording();
               }
               else {
                   stopRecording();
               }

            }
        });


    }

    private boolean CheckPermissions(){

        int first = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.RECORD_AUDIO);

        int second = ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return (first == PackageManager.PERMISSION_GRANTED && second == PackageManager.PERMISSION_GRANTED) ;
    }




    private void startRecording()
    {
        if(CheckPermissions() == true)
        {
            AudioSavedPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    +"/"+"recordingAudio.mp3";

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(AudioSavedPath);

            try {
                mediaRecorder.prepare();
                mediaRecorder.start();
                Toast.makeText(Activity_Report_Details.this,"Recording Started",Toast.LENGTH_LONG).show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        else {
            ActivityCompat.requestPermissions(Activity_Report_Details.this , new String[]{
                    Manifest.permission.RECORD_AUDIO , Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }
    }

    private void stopRecording() {
     mediaRecorder.stop();
     mediaRecorder.release();
     Toast.makeText(Activity_Report_Details.this,"Recording Stopped",Toast.LENGTH_SHORT).show();


    }






    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
}