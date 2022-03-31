package scenes;

import core.Camera;
import org.joml.Vector2f;
import renderer.RenderBatch;
import renderer.Sprite;
import renderer.Texture;

public class TextureScene extends Scene{
    private RenderBatch renderBatch;
    private Sprite sprite;

    @Override
    public void init() {
        camera = new Camera(new Vector2f());
        renderBatch = new RenderBatch(1, 1, new Texture("assets/images/img.png"));
        renderBatch.start();
        sprite = new Sprite(new Texture("assets/images/img.png"));
    }


    @Override
    public void update(float dt) {
        renderBatch.render();
        float[] pos = renderBatch.getVertecies();


        renderBatch.setVertecies(pos);

    }
}
