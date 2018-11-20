package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.render.Renderer;
import org.lwjgl.opengl.GL;

public class GLRenderer extends Renderer {

    public GLRenderer() {

    }

    @Override
    public void init() {
        GL.createCapabilities();
    }

}
