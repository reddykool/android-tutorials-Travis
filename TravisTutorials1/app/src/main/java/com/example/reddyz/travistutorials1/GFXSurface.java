package com.example.reddyz.travistutorials1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Reddyz on 11-10-2016.
 */
public class GFXSurface extends AppCompatActivity implements View.OnTouchListener {

    MySurfaceView mySurfaceView;
    float x,y, sX, sY, fX, fY, dX, dY, animateX, animateY, scaledX, scaledY;
    Bitmap bmpMove, bmpFixed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySurfaceView = new MySurfaceView(this);
        mySurfaceView.setOnTouchListener(this);
        x = y = 0;
        sX = sY = fX = fY = 0;
        dX = dY = animateX = animateY = scaledX = scaledY = 0;
        bmpMove = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
        bmpFixed = BitmapFactory.decodeResource(getResources(), R.drawable.pic3);

        setContentView(mySurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mySurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mySurfaceView.Resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                sX = event.getX();
                sY = event.getY();
                fX = fY = dX = dY = animateX = animateY = scaledX = scaledY = 0;
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX ;
                dY = fY - sY ;
                scaledX = dX/30;
                scaledY = dY/30;
                x = y = 0;
                break;
        }
        //sX = sY = fX = fY = dX = dY = animateX = animateY = scaledX = scaledY = 0;
        return true;
    }

    public class MySurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

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
                //myCanvas.drawColor(Color.BLUE);
                if(x != 0 && y != 0) {
                    myCanvas.drawBitmap(bmpMove, x - (bmpMove.getWidth()/2), y - (bmpMove.getHeight()/2), null);
                }
                if(sX != 0 && sY != 0) {
                    myCanvas.drawBitmap(bmpFixed, sX - (bmpFixed.getWidth()/2), sY - (bmpFixed.getHeight()/2), null);
                }
                if(fX != 0 && fY != 0) {
                    myCanvas.drawBitmap(bmpMove, fX - (bmpMove.getWidth()/2) - animateX, fY - (bmpMove.getHeight()/2) - animateY, null);
                    myCanvas.drawBitmap(bmpFixed, fX - (bmpFixed.getWidth()/2), fY - (bmpFixed.getHeight()/2), null);
                }
                animateX += scaledX;
                animateY += scaledY;
                myHolder.unlockCanvasAndPost(myCanvas);
            }
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if(ready) {
                isRunning = true;
                myThread = new Thread(this);
                myThread.start();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (ready) {
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
}

/*
// Without Surface holder callback
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
        isRunning =true;
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
            //myCanvas.drawColor(Color.BLUE);
            if(x != 0 && y != 0) {
                myCanvas.drawBitmap(bmpMove, x - (bmpMove.getWidth()/2), y - (bmpMove.getHeight()/2), null);
            }
            if(sX != 0 && sY != 0) {
                myCanvas.drawBitmap(bmpFixed, sX - (bmpFixed.getWidth()/2), sY - (bmpFixed.getHeight()/2), null);
            }
            if(fX != 0 && fY != 0) {
                myCanvas.drawBitmap(bmpMove, fX - (bmpMove.getWidth()/2) - animateX, fY - (bmpMove.getHeight()/2) - animateY, null);
                myCanvas.drawBitmap(bmpFixed, fX - (bmpFixed.getWidth()/2), fY - (bmpFixed.getHeight()/2), null);
            }
            animateX += scaledX;
            animateY += scaledY;
            myHolder.unlockCanvasAndPost(myCanvas);
        }
    }
}
*/
