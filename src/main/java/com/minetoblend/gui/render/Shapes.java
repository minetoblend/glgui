package com.minetoblend.gui.render;

import com.minetoblend.gui.render.gl.Shape;

public class Shapes {

    public final Shape quad = Shape.create2DShape(new float[]{0, 0, 0, 1, 1, 1, 1, 0}, new int[]{0, 1, 2, 0, 2, 3});
    public final Shape centeredQuad = Shape.create2DShape(new float[]{-.5f, -.5f, -.5f, .5f, .5f, .5f, .5f, -.5f}, new int[]{0, 1, 2, 0, 2, 3});
    public final Shape quadWire = Shape.create2DShapeWireframe(new float[]{0, 0, 0, 1, 1, 1, 1, 0}, new int[]{0, 1, 1, 2, 2, 3, 3, 0});
    public final Shape centeredQuadWire = Shape.create2DShapeWireframe(new float[]{-.5f, -.5f, -.5f, .5f, .5f, .5f, .5f, -.5f}, new int[]{0,1,1,2,2,3,3,0});

}
