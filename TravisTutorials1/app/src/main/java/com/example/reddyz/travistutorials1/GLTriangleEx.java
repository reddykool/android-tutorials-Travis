package com.example.reddyz.travistutorials1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Reddyz on 03-11-2016.
 */

public class GLTriangleEx {

    private float vertices[] = {
      0f, 4f,   //p0
      1f, 2f,   //p1
     -1f, 2f    //p2
    };

    private float rgbaVals[] = {
            1, 1, 0, .5f,     //p0
            .25f, 0, .85f, 1,  //p1
            0, 1, 1, 1        //p2
    };

    private FloatBuffer vertBuffer, colorBuffer;

    private short[] pIndex = {0,1,2};
    private ShortBuffer indexBuffer;

    public GLTriangleEx() {
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
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuffer);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT,indexBuffer);

        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
