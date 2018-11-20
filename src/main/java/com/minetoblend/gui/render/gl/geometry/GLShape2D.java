package com.minetoblend.gui.render.gl.geometry;


import com.minetoblend.gui.render.gl.Shape;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glDrawElements;
import static org.lwjgl.opengl.GL11C.GL_FLOAT;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.opengl.GL30C.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GLShape2D extends Shape {


    int vboID;
    int vaoID;
    int indicesID = -1;
    int mode;
    int elementCount;

    public GLShape2D(float[] vertices) {

        vaoID = glGenVertexArrays();

        mode = GL_POLYGON;

        glBindVertexArray(vaoID);
        {
            vboID = glGenBuffers();

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

        elementCount = vertices.length / 2;

        return;
    }

    public GLShape2D(float[] vertices, int[] indices) {
        vaoID = glGenVertexArrays();

        mode = GL_TRIANGLES;

        glBindVertexArray(vaoID);
        {

            indicesID = glGenBuffers();

            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
            {
                IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
                buffer.put(indices);
                buffer.flip();
                glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);

            }
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);


            vboID = glGenBuffers();

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

        System.out.println(indices.length);

        elementCount = indices.length;
    }


    @Override
    public void draw() {
        if (indicesID < 0) {
            glBindVertexArray(vaoID);
            glEnableVertexAttribArray(0);
            GL11.glDrawArrays(mode, 0, elementCount);
            glDisableVertexAttribArray(0);
            glBindVertexArray(0);
        } else {

            glBindVertexArray(vaoID);
            {
                glEnableVertexAttribArray(0);

                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
                {
                    glDrawElements(mode, elementCount, GL11.GL_UNSIGNED_INT, 0);
                }
                glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

                glDisableVertexAttribArray(0);
            }
            glBindVertexArray(0);
        }

    }
}
