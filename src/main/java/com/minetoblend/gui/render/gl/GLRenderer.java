package com.minetoblend.gui.render.gl;

import com.minetoblend.gui.Window;
import com.minetoblend.gui.element.Element;
import com.minetoblend.gui.render.Graphics;
import com.minetoblend.gui.render.Renderer;
import com.minetoblend.gui.render.Shapes;
import com.minetoblend.gui.render.gl.shader.GLShader;
import org.lwjgl.opengl.GL;


import static org.lwjgl.opengl.GL11C.*;

public class GLRenderer extends Renderer {


    private GLShader shader;

    public GLRenderer(Window window) {
        super(window);
    }

    @Override
    public Graphics createGraphics(Element element) {
        return new GLGraphics(element, this);
    }


    @Override
    public void init() {
        GL.createCapabilities();
        new GLFbo(40, 40, 1);

        setShader(new GLShader("gui.vert", "gui.frag"));

        shader.getProjectionMatrix().identity().ortho(0, window.getSize().x, window.getSize().y, 0, -1, 1);

        window.size.subscribe((newValue, oldValue, property) -> shader.getProjectionMatrix().identity().ortho(0, window.getSize().x, window.getSize().y, 0, -1, 1));

        glEnable(GL_CULL_FACE);

        shapes = new Shapes();

        glLineWidth(0);
    }

    public GLShader getShader() {
        return shader;
    }

    private void setShader(GLShader glShader) {
        shader = glShader;
        shader.bind();
    }

    @Override
    public void render() {
        glViewport(0, 0, window.getSize().x, window.getSize().y);
        glClearColor(0, 0, 0, 0);
        glClear(GL_COLOR_BUFFER_BIT);
        shader.getTransformationMatrix().identity().scale(100, 100, 0);

        shader.bind();

        var scene = window.getScene();
        if (scene != null) {
            scene.draw();
        }

        shader.unbind();
    }


}
