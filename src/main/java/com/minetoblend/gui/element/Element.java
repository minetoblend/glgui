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
        this.position = new ObjectProperty<>(new Vector2f());
        this.size = new ObjectProperty<>(new Vector2f());
    }

    public Graphics getGraphics() {
        if (graphics == null) {
            graphics = window.getRenderer().createGraphics(this);
        }
        return graphics;
    }

    public void draw() {

        

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
}
