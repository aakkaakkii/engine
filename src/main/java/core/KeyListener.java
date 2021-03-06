package core;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance = new KeyListener();
    private boolean keyPressed[] = new boolean[350];

    private KeyListener() {
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods ) {
        if(action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        } else if(action == GLFW_RELEASE) {
            get().keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int keyCode) {
        if(keyCode < get().keyPressed.length) {
            return get().keyPressed[keyCode];
        }
        return false;
    }

    public static KeyListener get() {
        return instance;
    }
}
