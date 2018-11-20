package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.types.ObservableProperty;
import org.joml.Vector2i;

import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL11C.*;

public class GLFbo {

    final ObservableProperty<Vector2i> size;
    final int[] textures;
    int fbo;

    public GLFbo(int width, int height) {
        this(width, height, 1);
    }

    public GLFbo(int width, int height, int textureCount) {

        if (width <= 0)
            throw new IllegalArgumentException(String.format("width %d <= 0", width));
        if (height <= 0)
            throw new IllegalArgumentException(String.format("height %d <= 0", width));
        if (textureCount <= 0)
            throw new IllegalArgumentException(String.format("texture count %d <= 0", width));

        textures = new int[textureCount];

        size = new ObservableProperty<>(new Vector2i(width, height));
        createFbo();
    }

    private void createFbo() {
        fbo = glGenFramebuffersEXT();
        glGenTextures(textures);


    }

}
