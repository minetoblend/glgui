package com.minetoblend.gui.render;

import com.minetoblend.gui.GuiConfig;
import com.minetoblend.gui.Window;
import com.minetoblend.gui.element.Element;
import com.minetoblend.gui.render.gl.GLGraphics;

public abstract class Renderer {

    protected Window window;

    public Renderer(Window window) {
        this.window = window;
    }

    public Graphics createGraphics(Element element) {
        if (GuiConfig.renderMode == GuiConfig.OPENGL) {
            return new GLGraphics(element, this);
        }
        return null;
    }

    public abstract void init();

    public abstract void render();

    public boolean shouldRender() {
        return true;
    }
}
