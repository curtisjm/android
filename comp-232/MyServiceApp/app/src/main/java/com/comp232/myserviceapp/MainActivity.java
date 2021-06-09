package com.comp232.myserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playSong(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    public void pauseSong(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }
}