package com.example.reddyz.travistutorials1;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Reddyz on 11-10-2016.
 */
public class GFX extends AppCompatActivity {

    MyCustomView myView;
    PowerManager.WakeLock wkLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //wake-lock
        PowerManager powerMgr = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wkLock = powerMgr.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");

        super.onCreate(savedInstanceState);
        myView = new MyCustomView(this);
        setContentView(myView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wkLock.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wkLock.acquire();
    }
}
