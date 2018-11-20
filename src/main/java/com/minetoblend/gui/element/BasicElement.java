package com.minetoblend.gui.element;

import com.minetoblend.gui.render.Color;
import com.minetoblend.gui.render.Graphics;

import java.awt.color.ColorSpace;

public class BasicElement extends Element {


    @Override
    void paint(Graphics g) {
        g.setColor(new Color(1, 0, 0, 1));
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
