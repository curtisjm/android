package com.comp232.myserviceapp;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;


public class MyService extends Service {

    private final String URL = "https://www.myinstants.com/instant/windows-xp-error-music/";
    private MediaPlayer mediaPlayer;
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        mediaPlayer = MediaPlayer.create(this, R.raw.rick);
//        Toast.makeText(this, "Playing song", Toast.LENGTH_LONG).show();
//        mediaPlayer.start();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(URL);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        Toast.makeText(this, "Paused song", Toast.LENGTH_LONG).show();
    }
}