package components;

import org.joml.Vector2f;
import renderer.Texture;

public class Sprite {
    private float width, height;

    private Texture texture = null;
    private Vector2f[] texCoords = {
            new Vector2f(1, 1),
            new Vector2f(1, 0),
            new Vector2f(0, 0),
            new Vector2f(0, 1)
    };

    public Sprite() {

    }

    public Sprite(Texture texture) {
        this.texture = texture;
    }

    public Sprite(Texture texture, Vector2f[] texCoords) {
        this.texture = texture;
        this.texCoords = texCoords;
    }

    public int getTextId() {
        return texture == null ? -1 :texture.getId();
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2f[] getTexCoords() {
        return texCoords;
    }


    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
