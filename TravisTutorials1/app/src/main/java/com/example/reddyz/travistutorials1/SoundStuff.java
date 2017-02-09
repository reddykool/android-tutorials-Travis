package com.example.reddyz.travistutorials1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Reddyz on 12-10-2016.
 */
public class SoundStuff extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    SoundPool sndPool;
    int explosion = 0;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);
        sndPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sndPool.load(this, R.raw.businessman, 1);
        mPlayer = MediaPlayer.create(this, R.raw.roja);
    }

    @Override
    public void onClick(View v) {
        if( explosion != 0 )
            sndPool.play(explosion, 1, 1, 0, 0, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sndPool.release();
        mPlayer.release();
    }

    @Override
    public boolean onLongClick(View v) {
        mPlayer.start();
        return false;
    }
}
