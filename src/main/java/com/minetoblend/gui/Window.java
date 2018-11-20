package com.minetoblend.gui;

import com.minetoblend.gui.render.Renderer;
import com.minetoblend.gui.render.gl.GLRenderer;
import com.minetoblend.gui.types.ObservableProperty;
import org.joml.Vector2i;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import java.util.ArrayDeque;

import static org.lwjgl.glfw.GLFW.*;

public class Window {

    private final ObservableProperty<Vector2i> size = new ObservableProperty<>(new Vector2i(800, 600));
    private final ObservableProperty<Vector2i> position = new ObservableProperty<>(new Vector2i());
    private final ObservableProperty<Boolean> isVisible = new ObservableProperty<>(false);
    private final Thread thread;
    ArrayDeque<Runnable> queue = new ArrayDeque<>();
    private long window;
    private Renderer renderer;

    public Window() {

        thread = new Thread(this::run);
        thread.start();

    }

    void init() {
        GLFWHandler.init();

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, 4);


        window = glfwCreateWindow(size.get().x, size.get().y, "Hello World!", MemoryUtil.NULL, MemoryUtil.NULL);

        if (window == MemoryUtil.NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        createRenderer();

    }

    private void createRenderer() {

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        if (GuiConfig.renderMode == GuiConfig.OPENGL) {
            renderer = new GLRenderer();
        }

        renderer.init();

    }

    void run() {

        init();

        while (!glfwWindowShouldClose(window)) {
            update();
            glfwSwapBuffers(window);
        }

    }

    void update() {
        for (Runnable runnable : queue) {
            queue.removeFirst().run();
        }
        glfwPollEvents();

        if (renderer.shouldRender())
            renderer.render();
    }


    public Vector2i getSize() {
        return size.get();
    }

    public void setSize(Vector2i size) {
        this.size.set(size);
        queue.addLast(() -> {
            glfwSetWindowSize(window, size.x, size.y);
        });
    }

    public Vector2i getPosition() {
        return position.get();
    }

    public boolean isVisible() {
        return isVisible.get();
    }

    public void setVisible(final boolean visible) {
        isVisible.set(visible);
        queue.addLast(() -> {
            glfwWindowHint(GLFW_VISIBLE, visible ? GLFW_TRUE : GLFW_FALSE);
        });
    }
}
