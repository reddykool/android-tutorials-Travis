package com.example.reddyz.travistutorials1;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Reddyz on 03-11-2016.
 */

public class GLExample extends AppCompatActivity{

    GLSurfaceView glSurface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurface = new GLSurfaceView(this);
        glSurface.setRenderer(new GLRendererEx());
        setContentView(glSurface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurface.onResume();
    }
}

