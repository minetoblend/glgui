package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.render.Renderer;
import com.minetoblend.gui.render.gl.geometry.GLShape;
import com.minetoblend.gui.render.gl.geometry.GLShape2D;
import org.lwjgl.opengl.GL;


import static org.lwjgl.opengl.GL11C.*;

public class GLRenderer extends Renderer {


    private GLShape2D shape;

    public GLRenderer() {

    }

    @Override
    public void init() {
        GL.createCapabilities();
        new GLFbo(40, 40, 1);

        shape = new GLShape2D(new float[]{0, 0, 1, 0, 1, 1, 0, 1});

    }

    @Override
    public void render() {
        glClearColor(0,0,0,0);
        glClear(GL_COLOR_BUFFER_BIT);
        shape.draw();
    }

}
