package com.minetoblend.gui.render;

import org.joml.Vector4f;

public class Color extends Vector4f {

    public Color() {
        super(0, 0, 0, 0);
    }

    public Color(float brightness) {
        super(brightness, brightness, brightness, brightness);
    }

    public Color(float r, float g, float b) {
        super(r, g, b, 1);
    }

    public Color(float r, float g, float b, float a) {
        super(r, g, b, a);
    }


}
