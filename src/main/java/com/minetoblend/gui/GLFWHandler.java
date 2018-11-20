package com.minetoblend.gui;

import static org.lwjgl.glfw.GLFW.*;

public class GLFWHandler {


    private static boolean initialized = false;

    public static void init() {
        if (!initialized) {
            if (!glfwInit())
                throw new IllegalStateException("Unable to initialize GLFW");
        }
    }
}


