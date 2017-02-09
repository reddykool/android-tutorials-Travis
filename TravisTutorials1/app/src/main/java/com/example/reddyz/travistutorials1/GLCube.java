package com.example.reddyz.travistutorials1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Reddyz on 03-11-2016.
 */

public class GLCube {

    private float vertices[] = {
            1f,  1f, -1f,   //p0 - Top Right Front
            1f, -1f, -1f,   //p1 - Bottom Right Front
           -1f, -1f, -1f,   //p2 - Bottom Left Front
           -1f,  1f, -1f,   //p3 - Top Left Front
            1f,  1f,  1f,   //p4 - Top Right Back
            1f, -1f,  1f,   //p5 - Bottom Right Back
           -1f, -1f,  1f,   //p6 - Bottom Left Back
           -1f,  1f,  1f,   //p7 - Top Left Back
    };

    private float rgbaVals[] = {
            0, 1, 0, .5f,     //p0
            .5f, 1, .85f, 1,  //p1
            1, 0, 1, 1,        //p2
            .25f, .5f, 1, .7f,
            1, .25f, .75f, .3f,
            .75f, .25f, .5f, 1,
            .6f, 1,1,1,
            0, .9f, .65f, .6f
    };

    private FloatBuffer vertBuffer, colorBuffer;

    private short[] pIndex = {
            3,4,0,  0,4,1,  3,0,1,
            3,7,4,  7,3,6,  7,6,4,
            4,5,1,  4,6,5,  5,6,1,
            3,1,2,  3,2,6,  2,1,6
        };
    private ShortBuffer indexBuffer;

    public GLCube() {
        ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length*4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuffer = bBuff.asFloatBuffer();
        vertBuffer.put(vertices);
        vertBuffer.position(0);

        ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length*2);
        pbBuff.order(ByteOrder.nativeOrder());
        indexBuffer = pbBuff.asShortBuffer();
        indexBuffer.put(pIndex);
        indexBuffer.position(0);

        ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length*4);
        cBuff.order(ByteOrder.nativeOrder());
        colorBuffer = cBuff.asFloatBuffer();
        colorBuffer.put(rgbaVals);
        colorBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CW);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuffer);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        gl.glDisable(GL10.GL_CULL_FACE);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
