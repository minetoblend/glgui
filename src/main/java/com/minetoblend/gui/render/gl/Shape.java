package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.GuiConfig;
import com.minetoblend.gui.render.gl.geometry.GLShape2D;

import static com.minetoblend.gui.GuiConfig.OPENGL;

public abstract class Shape {

    public static Shape create2DShape(float[] vertices) {
        if (GuiConfig.renderMode == OPENGL)
            return new GLShape2D(vertices);
        return null;
    }

    public static Shape create2DShape(float[] vertices, int[] indices) {
        if (GuiConfig.renderMode == OPENGL)
            return new GLShape2D(vertices, indices);
        return null;
    }

    public static Shape create2DShapeWireframe(float[] vertices, int[] indices) {
        if (GuiConfig.renderMode == OPENGL)
            return new GLShape2D(vertices, indices, true);
        return null;
    }

    public abstract void draw();
}
