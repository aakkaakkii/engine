package scenes;

import core.Camera;

public abstract class Scene {
    protected Camera camera;

    public Scene() {

    }

    public abstract void update(float dt);

    public void init() {

    };

    public Camera camera() {
        return this.camera;
    }

}
