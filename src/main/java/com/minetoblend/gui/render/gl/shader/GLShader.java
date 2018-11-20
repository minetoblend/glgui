package com.minetoblend.gui.render.gl.shader;

import static org.lwjgl.opengl.GL20C.*;

public class GLShader {

    public static int PROJECTION_MATRIX_LOCATION = 200;
    public static int TRANSFORMATION_MATRIX_LOCATION = 201;

    private int program;

    GLShader(String fragmentShader) {
        this("gui.vert", fragmentShader);
    }

    GLShader(String vertexShader, String fragmentShader) {

        

    }


    void bind() {
        glUseProgram(program);

    }

    void unbind() {
        glUseProgram(0);
    }
}
