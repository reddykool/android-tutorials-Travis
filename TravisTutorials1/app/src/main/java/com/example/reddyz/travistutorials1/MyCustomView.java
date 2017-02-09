package com.example.reddyz.travistutorials1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by Reddyz on 11-10-2016.
 */
public class MyCustomView extends View {

    Bitmap myIcon;
    float changingY;
    Typeface myFont;

    public MyCustomView(Context context) {
        super(context);

        myIcon = BitmapFactory.decodeResource(getResources(),R.drawable.pic4);
        changingY = 0;
        myFont = Typeface.createFromAsset(context.getAssets(), "indieflower.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.CYAN);

        Paint textPaint = new Paint();
        //textPaint.setARGB(80, 255, 10, 5);
        textPaint.setColor(Color.RED);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(200);;
        textPaint.setTypeface(myFont);
        canvas.drawText("Millionaire", canvas.getWidth()/3, 200, textPaint);

        Rect bottomRect = new Rect(0, 800, canvas.getWidth(), 950);
        Paint myBlue = new Paint();
        myBlue.setColor(Color.BLUE);
        canvas.drawRect(bottomRect, myBlue);

        canvas.drawBitmap(myIcon, (canvas.getWidth()/2),changingY, null);
        if(changingY < canvas.getHeight()) {
            changingY += 10;
        } else {
            changingY = 0;
        }

        Rect midRect = new Rect(0, 400, canvas.getWidth(), 550);
        canvas.drawRect(midRect, myBlue);

        invalidate();
    }
}
