package com.minetoblend.gui;

import org.joml.Vector2i;

import static org.lwjgl.glfw.GLFW.*;

public class GLFWHandler {


    private static boolean initialized = false;
    Window window;

    public GLFWHandler(Window window) {
        this.window = window;

        glfwSetKeyCallback(window.window, this::onKeyEvent);
        glfwSetMouseButtonCallback(window.window, this::onMouseEvent);
        glfwSetCursorPosCallback(window.window, this::onMouseMoveEvent);
        glfwSetCursorEnterCallback(window.window, this::onMouseEnterEvent);
        glfwSetWindowSizeCallback(window.window, this::onResize);
    }

    public static void init() {
        if (!initialized) {
            if (!glfwInit())
                throw new IllegalStateException("Unable to initialize GLFW");
        }
    }

    private void onResize(long l, int x, int y) {
        window.size.setWithoutNotice(new Vector2i(x, y));
    }

    private void onMouseEnterEvent(long l, boolean b) {

    }

    private void onMouseMoveEvent(long l, double v, double v1) {

    }

    private void onMouseEvent(long l, int i, int i1, int i2) {

    }

    private void onKeyEvent(long l, int i, int i1, int i2, int i3) {

    }
}


