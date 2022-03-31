package core;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import scenes.Scene;
import scenes.TextureScene;
import scenes.TransformationScreen;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private static Window window = new Window();
    private long glfwWindow;

    private int width;
    private int height;
    private String title;

    private float r, g, b, a;

    private static Scene currentScene;

    private Window() {
        this.width = 1280;
        this.height = 720;
        this.title = "Engine";

        r = 1f;
        g = 1f;
        b = 1f;
        a = 1f;
    }


    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            throw new IllegalStateException("Unable to init GLFW");
        }

        //config GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //create the window

        glfwWindow = glfwCreateWindow(this.width, this.height, title, NULL, NULL);

        if (glfwWindow == NULL) {
            throw new IllegalStateException("Filed to create the GLFW window");
        }

        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetWindowSizeCallback(glfwWindow, (w, newWidth, newHeight) -> {
            Window.setWidth(newWidth);
            Window.setHeight(newHeight);
        });

        //Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        //Enable v-sync
        glfwSwapInterval(1);

        glfwShowWindow(glfwWindow);

        //IMPORTANT
        GL.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

        Window.changeScene(0);
    }

    private void loop() {
        float beginTime = (float) glfwGetTime();
        float endTime ;
        float dt = -1.0f;


        while (!glfwWindowShouldClose(glfwWindow)) {
            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if(dt >= 0) {
                currentScene.update(dt);
            }


            glfwSwapBuffers(glfwWindow);

            glfwPollEvents();

            endTime = (float) glfwGetTime();

            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    public void run() {
        System.out.println("Hello LWJGL" + Version.getVersion());

        init();
        loop();

        //Free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        //Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public static void changeScene(int newScene) {
//        currentScene = new TestScene();
//        currentScene = new TransformationScreen();
        currentScene = new TextureScene();
        currentScene.init();
    }

    public static Scene getScene() {
        return currentScene;
    }

    private static void setWidth(int newWidth) {
        get().width = newWidth;
    }

    private static void setHeight(int newHeight) {
        get().height = newHeight;
    }

    public static int getWidth() {
        return get().width;
    }

    public static int getHeight() {
        return get().height;
    }

    public static Window get() {
        return window;
    }
}
