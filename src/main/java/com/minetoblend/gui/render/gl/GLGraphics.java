package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.element.Element;
import com.minetoblend.gui.render.Color;
import com.minetoblend.gui.render.Graphics;
import com.minetoblend.gui.render.Shapes;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class GLGraphics extends Graphics {

    GLRenderer renderer;
    Element element;
    Shapes shapes;
    GLFbo fbo;
    Color color;

    public GLGraphics(Element element, GLRenderer renderer) {
        this.renderer = renderer;
        fbo = new GLFbo((int) (element.getSize().x), (int) (element.getSize().y));
        element.getSizeObervable().subscribe((newValue, oldValue, property) -> {
            fbo.resize((int) newValue.x, (int) newValue.y);
        });
        shapes = renderer.getShapes();
    }

    @Override
    public void bind() {
        fbo.bind();
    }

    @Override
    public void unbind() {
        fbo.unbind();
    }

    @Override
    public void drawRect(float x, float y, float width, float height) {
        renderer.getShader().setModelMatrix(new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1));
        shapes.quadWire.draw();
    }

    @Override
    public void drawCenteredRect(float x, float y, float width, float height) {

    }

    @Override
    public void fillRect(float x, float y, float width, float height) {
        renderer.getShader().setModelMatrix(new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1));
        shapes.quad.draw();
    }

    @Override
    public void fillCenteredRect(float x, float y, float width, float height) {
        renderer.getShader().setModelMatrix(new Matrix4f().identity().translate(x, y, 0).scale(width, height, 1));
        shapes.centeredQuad.draw();
    }

    @Override
    public void setColor(Color color) {
        float[] col = {color.x, color.y, color.z, color.w};

        renderer.getShader().setColor(col);
    }
}
