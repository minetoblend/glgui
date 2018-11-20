package com.minetoblend.gui.element;

import com.minetoblend.gui.types.ObservableProperty;
import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Element {

    private final ObservableProperty<Vector2f> position;
    private final ObservableProperty<Vector2f> size;

    Matrix4f elementMatrix = new Matrix4f();

    public Element() {
        this.position = new ObservableProperty<>(new Vector2f());
        this.size = new ObservableProperty<>(new Vector2f());
    }
}
