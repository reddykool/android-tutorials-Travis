package com.example.reddyz.travistutorials1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by Reddyz on 13-10-2016.
 */
public class Flipper extends AppCompatActivity implements View.OnClickListener{

    ViewFlipper vFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        vFlipper.setOnClickListener(this);
        vFlipper.setFlipInterval(1000);
        vFlipper.startFlipping();
    }

    @Override
    public void onClick(View v) {
        vFlipper.showNext();
        vFlipper.stopFlipping();
    }
}
