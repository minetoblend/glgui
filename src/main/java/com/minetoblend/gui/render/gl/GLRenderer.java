package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.render.Renderer;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11C.*;

public class GLRenderer extends Renderer {


    public GLRenderer() {

    }

    @Override
    public void init() {
        GL.createCapabilities();
    }

    @Override
    public void render() {
        glClearColor(1, 1, 1, 1);
        glClear(GL_COLOR_BUFFER_BIT);

    }

}
