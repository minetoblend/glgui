package com.minetoblend.gui.render;

public abstract class Renderer {
    public abstract void init();

    public abstract void render();

    public boolean shouldRender() {
        return true;
    }
}
