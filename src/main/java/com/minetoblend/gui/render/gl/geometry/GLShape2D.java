package com.minetoblend.gui.render.gl.geometry;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.*;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GLShape2D extends GLShape {


    int vboID;
    int vaoID;
    int mode;
    int vertexCount;

    public GLShape2D(float[] vertices) {

        vaoID = glGenVertexArrays();

        mode = GL_POLYGON_MODE;

        glBindVertexArray(vaoID);
        {
            int vboID = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboID);
            {
                FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
                buffer.put(vertices);
                buffer.flip();
                glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
                glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
            }

            glBindBuffer(GL_ARRAY_BUFFER, 0);

        }
        glBindVertexArray(0);

        vertexCount = vertices.length / 2;

        return;
    }


    @Override
    public void draw() {
        GL30.glBindVertexArray(vaoID);

        glEnableVertexAttribArray(0);
        GL11.glDrawArrays(mode, 0, vertexCount);
        glDisableVertexAttribArray(0);

        GL30.glBindVertexArray(0);
    }
}
