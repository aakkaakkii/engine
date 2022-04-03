package scenes;

import components.SpriteRenderer;
import core.Camera;
import core.GameObject;
import core.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import components.Sprite;
import components.Spritesheet;
import util.AssetPool;

public class TextureScene extends Scene {
    private GameObject testObj;
    private GameObject testObj2;
    private GameObject testObj3;
    private GameObject testObj4;
    private Vector2f posVec;

    @Override
    public void init() {
        loadResources();

        posVec=new Vector2f(300f);

        this.testObj = new GameObject("Test Obj", new Transform(new Vector2f(0, 0), new Vector2f(100, 100)), 1);
        this.testObj.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/images/img.png"))));
        this.addGameObjectToScene(testObj);


        this.testObj2 = new GameObject("Test Obj", new Transform(new Vector2f(200, 300), new Vector2f(100, 100)), 1);
        this.testObj2.addComponent(new SpriteRenderer(new Vector4f(1, 1, 0, 1)));
        this.addGameObjectToScene(testObj2);

        Spritesheet spritesheet = AssetPool.getSpritesheet("assets/images/spritesheets/spritesheet.png");

        this.testObj3 = new GameObject("Test Obj", new Transform(new Vector2f(300, 0), new Vector2f(300, 300)), 1);
        this.testObj3.addComponent(new SpriteRenderer(
                spritesheet.getSprite(3)
        ));
        this.addGameObjectToScene(testObj3);

        this.testObj4 = new GameObject("Test Obj", new Transform(new Vector2f(300, 300), new Vector2f(300, 300)), 1);
        this.testObj4.addComponent(new SpriteRenderer(
                spritesheet.getSprite(3)
        ));
        this.addGameObjectToScene(testObj4);

        camera = new Camera(new Vector2f());
    }

    private void loadResources() {
        AssetPool.getTexture("assets/images/img.png");
        AssetPool.addSpritesheet("assets/images/spritesheets/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheets/spritesheet.png"),
                        16, 16, 26, 0));
    }


private Vector2f gravityVector = new Vector2f(1, -250);


    public static float Approach(float goal, float current, float dt) {
        float diff = goal - current;

        if(diff > dt) {
            return current + dt;
        }
        if(diff < -dt) {
            return current - dt;
        }

        return goal;
    }

    @Override
    public void update(float dt) {

//        System.out.println(new Vector2f(posVec).mul(dt));

//        posVec = posVec.add(new Vector2f(gravityVector).mul(dt));

//        Vector2f newPosition = new Vector2f(testObj2.transform.position).add(new Vector2f(posVec).mul(dt));
//        testObj2.transform.position.set(newPosition);


        testObj3.transform.position.x += Approach(  1, 1, 0);
        testObj4.transform.position.x += 50*dt;

//        testObj3.transform.position.x += 10*dt;

        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }


        this.renderer.render();

    }
}
