package com.example.reddyz.travistutorials1;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.SystemClock;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Reddyz on 03-11-2016.
 */

public class GLRendererEx implements GLSurfaceView.Renderer{

    private GLTriangleEx triangle;
    private GLCube cube;

    public GLRendererEx() {
        triangle = new GLTriangleEx();
        cube = new GLCube();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        //gl.glClearColor(.8f,.2f,.0f,1);
        //gl.glClearDepthf(1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0,0,width,height);
        float ratio = (float) width/height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 20);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl,0,0,-5,0,0,0,0,2,0);
        triangle.draw(gl);

        long time = SystemClock.uptimeMillis()%4000L;
        float angle = 0.090f *((int)time);
        gl.glRotatef(angle, 1, 0, 0);
        gl.glRotatef(angle, 0, 0, 1);
        //triangle.draw(gl);
        cube.draw(gl);
    }
}
