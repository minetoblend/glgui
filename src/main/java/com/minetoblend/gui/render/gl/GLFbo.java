package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.types.ObjectProperty;
import org.joml.Vector2i;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.GL11C.*;

public class GLFbo {

    final ObjectProperty<Vector2i> size;
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

        size = new ObjectProperty<>(new Vector2i(width, height));
        createFbo();

        size.subscribe((newValue, oldValue, property) -> {
            glDeleteFramebuffersEXT(fbo);
            glDeleteTextures(textures);
            createFbo();
        });
    }

    private void createFbo() {
        fbo = glGenFramebuffersEXT();
        glGenTextures(textures);
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, fbo);


        int width = size.get().x;
        int height = size.get().y;

        for (int i = 0; i < textures.length; i++) {

            glBindTexture(GL_TEXTURE_2D, textures[i]);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer) null);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

            glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT, i, GL_TEXTURE_2D, textures[i], 0);
        }

        glBindTexture(GL_TEXTURE_2D, 0);

        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, 0);

    }

    public void resize(int x, int y) {
        this.size.set(new Vector2i(x, y));
    }
}
