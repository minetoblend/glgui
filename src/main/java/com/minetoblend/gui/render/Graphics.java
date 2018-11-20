package com.minetoblend.gui.render;

import com.minetoblend.gui.GuiConfig;
import com.minetoblend.gui.element.Element;
import com.minetoblend.gui.render.gl.GLGraphics;

public abstract class Graphics {




    public abstract void bind();

    public abstract void unbind();

    public abstract void drawRect(float x, float y, float width, float height);

    public abstract void drawCenteredRect(float x, float y, float width, float height);

    public abstract void fillRect(float x, float y, float width, float height);

    public abstract void fillCenteredRect(float x, float y, float width, float height);

    public abstract void setColor(Color color);
}
