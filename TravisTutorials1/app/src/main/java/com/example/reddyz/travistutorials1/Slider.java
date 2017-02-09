package com.example.reddyz.travistutorials1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

/**
 * Created by Reddyz on 12-10-2016.
 */
public class Slider extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {
    SlidingDrawer slideDraw;
    MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sliding);

        Button handle1, handle2, handle3, handle4;
        CheckBox checkBox;

        handle1 = (Button) findViewById(R.id.handle1);
        handle2 = (Button) findViewById(R.id.handle2);
        handle3 = (Button) findViewById(R.id.handle3);
        handle4 = (Button) findViewById(R.id.handle4);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        handle1.setOnClickListener(this);
        handle2.setOnClickListener(this);
        handle3.setOnClickListener(this);
        handle4.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);

        slideDraw = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        slideDraw.setOnDrawerOpenListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.handle1 :
                slideDraw.open();
                break;
            case R.id.handle2 :
                break;
            case R.id.handle3 :
                slideDraw.toggle();
                break;
            case R.id.handle4 :
                slideDraw.close();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            slideDraw.lock();
        } else {
            slideDraw.unlock();
        }
    }

    @Override
    public void onDrawerOpened() {
        player = MediaPlayer.create(this, R.raw.rhtdm);
        player.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.release();
    }
}
