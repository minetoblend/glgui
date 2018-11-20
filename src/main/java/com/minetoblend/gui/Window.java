package com.minetoblend.gui;

import com.minetoblend.gui.types.ObservableProperty;
import org.joml.Vector2i;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;

public class Window {

    private final ObservableProperty<Vector2i> size = new ObservableProperty<>(new Vector2i(800, 600));
    private final ObservableProperty<Vector2i> position = new ObservableProperty<>(new Vector2i());
    private final ObservableProperty<Boolean> isVisible = new ObservableProperty<>(false);
    private final Thread thread;
    private long window;

    public Window() {

        thread = new Thread(this::init);
        thread.start();

    }

    void init() {
        GLFWHandler.init();

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        window = glfwCreateWindow(size.get().x, size.get().y, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL);

        if (window == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create the GLFW window");


    }


    public Vector2i getSize() {
        return size.get();
    }

    public Vector2i getPosition() {
        return position.get();
    }

    public boolean isVisible() {
        return isVisible.get();
    }

    public void setVisible(boolean visible) {
        isVisible.set(visible);
    }
}
