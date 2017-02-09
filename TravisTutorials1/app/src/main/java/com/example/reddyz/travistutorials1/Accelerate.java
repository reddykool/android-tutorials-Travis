package com.example.reddyz.travistutorials1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Reddyz on 25-10-2016.
 */
public class Accelerate extends AppCompatActivity implements SensorEventListener{

    float x,y, sensorX, sensorY;
    Bitmap ball;
    SensorManager sm;
    MySurfaceView mySurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.smilly);
        x = y = sensorX = sensorY = 0;

        mySurfaceView = new MySurfaceView(this);
        //mySurfaceView.Resume();
        setContentView(mySurfaceView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
        mySurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySurfaceView.Resume();
    }

    public class MySurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback{

        SurfaceHolder myHolder;
        Thread myThread = null;
        protected boolean isRunning = false;
        boolean ready = false;

        public MySurfaceView(Context context) {
            super(context);
            myHolder = getHolder();
            myHolder.addCallback(this);
        }

        public void pause() {
            ready = false;
            isRunning = false;
            while (true) {
                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            myThread = null;
        }

        public void Resume() {
            ready = true;
        }

        @Override
        public void run() {
            while(isRunning) {
                if(!myHolder.getSurface().isValid())
                    continue;

                Canvas myCanvas = myHolder.lockCanvas();
                myCanvas.drawRGB(10, 150, 25);
                float centreX = myCanvas.getWidth()/2;
                float centreY = myCanvas.getHeight()/2;
                myCanvas.drawBitmap(ball, centreX + sensorX*25, centreY + sensorY*25, null);

                myHolder.unlockCanvasAndPost(myCanvas);
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            isRunning = true;
            myThread = new Thread(this);
            myThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            isRunning = false;
            while (true) {
                try {
                    myThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            myThread = null;

        }
    }
}

/*
public class MySurfaceView extends SurfaceView implements Runnable {

    SurfaceHolder myHolder;
    Thread myThread = null;
    protected boolean isRunning = false;

    public MySurfaceView(Context context) {
        super(context);
        myHolder = getHolder();
    }

    public void pause() {
        isRunning = false;
        while (true) {
            try {
                myThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        myThread = null;
    }

    public void Resume() {
        isRunning = true;
        myThread = new Thread(this);
        myThread.start();
    }

    @Override
    public void run() {
        while(isRunning) {
            if(!myHolder.getSurface().isValid())
                continue;

            Canvas myCanvas = myHolder.lockCanvas();
            myCanvas.drawRGB(10, 150, 25);
            float centreX = myCanvas.getWidth()/2;
            float centreY = myCanvas.getHeight()/2;
            myCanvas.drawBitmap(ball, centreX + sensorX*25, centreY + sensorY*25, null);

            myHolder.unlockCanvasAndPost(myCanvas);
        }
    }
}
*/
