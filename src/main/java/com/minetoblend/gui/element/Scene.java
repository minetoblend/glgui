package com.minetoblend.gui.element;

import com.minetoblend.gui.Window;
import com.minetoblend.gui.render.Renderer;

public class Scene extends Element {

    private Element root;

    public Scene() {

    }

    public Scene(BasicElement root) {
        this.root = root;

    }

    public Element getRoot() {
        return root;
    }

    public void setRoot(Element root) {
        this.root = root;
        if (root != null) {
            root.window = window;
        }
    }

    @Override
    public void draw() {
        root.draw();
    }


    public void setWindow(Window window) {
        this.window = window;
        var root = this.root;
        if (root != null) {
            root.window = window;
        }
    }
}
