package com.example.reddyz.travistutorials1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Reddyz on 09-10-2016.
 */
public class Splash extends AppCompatActivity {

    private MediaPlayer splashSong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        splashSong = MediaPlayer.create(this, R.raw.tokyodrift);

        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean musicPreferred = getPrefs.getBoolean("checkboxMusic",true);
        if(musicPreferred) {
            splashSong.start();
        }

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Intent openMainActivity = new Intent("com.example.reddyz.travistutorials1.MAINACTIVITY");
                    Intent openMainActivity = new Intent(Splash.this, Menu.class);
                    startActivity(openMainActivity);
                }
            }
        };
        splashTread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashSong.release();
        finish();
    }
}
