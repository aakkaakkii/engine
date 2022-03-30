package scenes;

import core.Camera;
import org.joml.Vector2f;
import renderer.RenderBatch;

public class TestScene2 extends Scene {
    RenderBatch renderBatch;

    @Override
    public void init() {
        camera = new Camera(new Vector2f());
        renderBatch = new RenderBatch(1, 1);
        renderBatch.start();
    }

    @Override
    public void update(float dt) {
        renderBatch.start();
    }
}
