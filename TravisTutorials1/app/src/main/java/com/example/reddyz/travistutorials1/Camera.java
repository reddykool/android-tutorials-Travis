package com.example.reddyz.travistutorials1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Reddyz on 10-10-2016.
 */
public class Camera extends AppCompatActivity implements View.OnClickListener{
    ImageView iv;
    ImageButton ib;
    Button b;
    Intent i;
    final static int cameraData = 0;
    Bitmap capturedBmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        initializeVars();
    }

    private void initializeVars() {
        iv = (ImageView) findViewById(R.id.ivCapturedPic);
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bWallpaper);

        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibTakePic :
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;

            case R.id.bWallpaper :
                try {
                    if(capturedBmp != null) {
                        getApplicationContext().setWallpaper(capturedBmp);
                        Toast.makeText(this,"Wallpaper Set Successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this,"No image to set wallpaper. Capture Image pls", Toast.LENGTH_LONG).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            Bundle cameraExtras = data.getExtras();
            capturedBmp = (Bitmap) cameraExtras.get("data");
            iv.setImageBitmap(capturedBmp);
        } else {
            //set local image itself as captured image - useful for Emulator testing
            //iv.setImageResource(R.mipmap.yomaamz_logo);
            Toast.makeText(this," Camera not working", Toast.LENGTH_LONG).show();
            Drawable myLogo = getResources().getDrawable(R.mipmap.mybikelogo);
            capturedBmp = ((BitmapDrawable) myLogo).getBitmap();
            iv.setImageBitmap(capturedBmp);
        }
    }
}
