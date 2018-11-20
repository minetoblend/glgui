package com.minetoblend.gui.render;

import com.minetoblend.gui.GuiConfig;
import com.minetoblend.gui.Window;
import com.minetoblend.gui.element.Element;
import com.minetoblend.gui.render.gl.GLGraphics;
import com.minetoblend.gui.render.gl.Shape;

public abstract class Renderer {

    protected Window window;

    protected Shapes shapes;


    public Renderer(Window window) {
        this.window = window;
    }

    public abstract Graphics createGraphics(Element element) ;

    public abstract void init();

    public abstract void render();

    public boolean shouldRender() {
        return true;
    }

    public Shapes getShapes() {
        return shapes;
    }
}
