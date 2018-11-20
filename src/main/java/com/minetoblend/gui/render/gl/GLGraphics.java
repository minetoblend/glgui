package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.element.Element;
import com.minetoblend.gui.render.Graphics;
import com.minetoblend.gui.render.Renderer;

public class GLGraphics extends Graphics {

    Renderer renderer;
    Element element;

    GLFbo fbo;

    public GLGraphics(Element element, Renderer renderer) {
        this.renderer = renderer;
        fbo = new GLFbo((int) (element.getSize().x), (int) (element.getSize().y));
        element.getSizeObervable().subscribe((newValue, oldValue, property) -> {
            fbo.resize((int) newValue.x, (int) newValue.y);
        });
    }

    @Override
    public void bind() {

    }

    @Override
    public void unbind() {

    }

    @Override
    public void drawRect(float x, float y, float width, float height) {

    }

    @Override
    public void drawCenteredRect(float x, float y, float width, float height) {

    }

    @Override
    public void fillRect(float x, float y, float width, float height) {

    }

    @Override
    public void fillCenteredRect(float x, float y, float width, float height) {

    }
}
