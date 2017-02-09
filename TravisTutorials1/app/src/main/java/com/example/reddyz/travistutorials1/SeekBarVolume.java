package com.example.reddyz.travistutorials1;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

/**
 * Created by Reddyz on 04-11-2016.
 */

public class SeekBarVolume extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar sb;
    MediaPlayer mp;
    AudioManager am;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seekbarvolume);

        sb = (SeekBar) findViewById(R.id.sbSeekBarVol);
        mp = MediaPlayer.create(this, R.raw.roja);
        mp.start();

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(maxVol);
        sb.setProgress(curVol);
        sb.setOnSeekBarChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
