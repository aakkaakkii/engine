package components;

import core.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Sprite;
import renderer.Texture;

public class SpriteRenderer {
    private Vector4f color = new Vector4f(1, 1, 1, 1);
    private Sprite sprite = new Sprite();

    private transient Transform lastTransform;

    public SpriteRenderer() {
    }

    public SpriteRenderer(Vector4f color) {
        this.color = color;
        this.sprite = new Sprite(null);

    }


    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
        this.color = new Vector4f(1, 1, 1, 1);
    }

    public Vector4f getColor() {
        return color;
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
