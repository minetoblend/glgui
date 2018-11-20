package com.minetoblend.gui;

import com.minetoblend.gui.element.Scene;
import com.minetoblend.gui.render.Renderer;
import com.minetoblend.gui.render.gl.GLRenderer;
import com.minetoblend.gui.types.ObjectProperty;
import org.joml.Vector2i;
import org.lwjgl.system.MemoryUtil;

import java.util.ArrayDeque;

import static org.lwjgl.glfw.GLFW.*;

public class Window {

    public final ObjectProperty<Vector2i> size = new ObjectProperty<>(new Vector2i(800, 600));
    public final ObjectProperty<Vector2i> position = new ObjectProperty<>(new Vector2i());
    public final ObjectProperty<Boolean> isVisible = new ObjectProperty<>(false);
    private final Thread thread;
    private final ObjectProperty<Scene> scene = new ObjectProperty<>(null);
    public long window;
    ArrayDeque<Runnable> queue = new ArrayDeque<>();
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
            throw new RuntimeException("Failed to createGraphics the GLFW window");

        createRenderer();
    }

    private void createRenderer() {

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        if (GuiConfig.renderMode == GuiConfig.OPENGL) {
            renderer = new GLRenderer(this);
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
        queue.addLast(() -> glfwSetWindowSize(window, size.x, size.y));
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

    public Scene getScene() {
        return this.scene.get();
    }

    public void setScene(Scene scene) {
        scene.setWindow(this);
        this.scene.set(scene);
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
