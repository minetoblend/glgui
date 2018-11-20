package com.minetoblend.gui.element;

import com.minetoblend.gui.Window;
import com.minetoblend.gui.render.Graphics;
import com.minetoblend.gui.render.Renderer;
import com.minetoblend.gui.types.ObjectProperty;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Element {

    private final ObjectProperty<Vector2f> position;
    private final ObjectProperty<Vector2f> size;

    Window window;

    Graphics graphics;
    Matrix4f elementMatrix = new Matrix4f();

    public Element() {
        this.position = new ObjectProperty<>(new Vector2f(100,100));
        this.size = new ObjectProperty<>(new Vector2f(100, 100));
    }

    public Graphics getGraphics() {
        if (graphics == null && window != null) {
            graphics = window.getRenderer().createGraphics(this);
        }
        return graphics;
    }

    public void draw() {

        Graphics g = getGraphics();

        g.bind();

        paint(g);

        g.unbind();

    }

    void paint(Graphics g) {

    }

    public Vector2f getSize() {
        return size.get();


    }

    public ObjectProperty<Vector2f> getSizeObervable() {
        return size;
    }

    public Renderer getRenderer() {
        return this.window.getRenderer();
    }

    public float getWidth() {
        return size.get().x;
    }

    public float getHeight() {
        return size.get().y;
    }
}
