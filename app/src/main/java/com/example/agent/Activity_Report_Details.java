package com.example.agent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    private static final String AUDIO_FILE_PATH = Environment.getExternalStorageDirectory() + "/recorded_audio.mp3";
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

                startRecording();
            }
        });
    }

    private boolean isRecording = false;

    private void startRecording() {
        if (isRecording) {
            stopRecording();
        }

        mediaRecorder = new MediaRecorder();

        try {
            // Set audio source and output format
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(AUDIO_FILE_PATH);

            // Prepare and start recording
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to start recording", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (isRecording) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
            Toast.makeText(this, "Recording saved to " + AUDIO_FILE_PATH, Toast.LENGTH_SHORT).show();
        }
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