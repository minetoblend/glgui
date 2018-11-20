package com.minetoblend.gui.render.gl.shader;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20C.*;

public class GLShader {

    public static int PROJECTION_MATRIX_LOCATION = 100;
    public static int VIEW_MATRIX_LOCATION = 101;
    public static int MODEL_MATRIX_LOCATION = 102;
    public static int COLOR_LOCATION = 200;

    boolean active = false;
    private Matrix4f projectionMatrix = new Matrix4f();
    private Matrix4f transformationMatrix = new Matrix4f();
    private Matrix4f viewMatrix = new Matrix4f();
    private int program;

    public GLShader(String fragmentShader) {
        this("gui.vert", fragmentShader);
    }

    public GLShader(String vertexShader, String fragmentShader) {

        int vert = loadShader(vertexShader, GL_VERTEX_SHADER);
        int frag = loadShader(fragmentShader, GL_FRAGMENT_SHADER);
        int program = glCreateProgram();
        glAttachShader(program, vert);
        glAttachShader(program, frag);
        glLinkProgram(program);
        glValidateProgram(program);
        this.program = program;
    }

    private static int loadShader(String name, int type) {
        String source = loadResource(name);
        if (type != GL_VERTEX_SHADER && type != GL_FRAGMENT_SHADER)
            throw new IllegalArgumentException(type + " is neither Vertex nor Fragment Shader");


        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);
        if (glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE) {
            throw new UnsupportedOperationException(glGetShaderInfoLog(shader));
        }
        return shader;
    }

    private static String loadResource(String name) {
        return new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("shaders/" + name), "UTF-8").useDelimiter("\\A").next();
    }


    public void bind() {
        active = true;
        glUseProgram(program);
        loadMatrix(PROJECTION_MATRIX_LOCATION, projectionMatrix);
        loadMatrix(VIEW_MATRIX_LOCATION, viewMatrix);
        loadMatrix(MODEL_MATRIX_LOCATION, transformationMatrix);
    }

    private void loadMatrix(int uniformLocation, Matrix4f matrix) {
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrix.get(matrixBuffer);
        glUniformMatrix4fv(uniformLocation, false, matrixBuffer);
    }

    public void unbind() {
        active = false;
        glUseProgram(0);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setProjectionMatrix(Matrix4f projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
        if (active)
            loadMatrix(PROJECTION_MATRIX_LOCATION, projectionMatrix);
    }

    public Matrix4f getTransformationMatrix() {
        return transformationMatrix;
    }

    public void setModelMatrix(Matrix4f matrix) {
        this.transformationMatrix = matrix;
        if (active)
            loadMatrix(MODEL_MATRIX_LOCATION, matrix);
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public void setViewMatrix(Matrix4f matrix) {
        this.viewMatrix = matrix;
        if (active)
            loadMatrix(MODEL_MATRIX_LOCATION, matrix);
    }

    public void setColor(float[] rgba) {
        glUniform4fv(COLOR_LOCATION, rgba);
    }
}
