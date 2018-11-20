package com.minetoblend.gui.render;

import com.minetoblend.gui.Window;

public abstract class Renderer {

    protected Window window;

    public Renderer(Window window) {
        this.window = window;
    }

    public abstract void init();

    public abstract void render();

    public boolean shouldRender() {
        return true;
    }
}
