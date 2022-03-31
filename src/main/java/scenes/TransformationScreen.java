package scenes;

import core.Camera;
import core.KeyListener;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import renderer.RenderBatch;

import static org.lwjgl.glfw.GLFW.*;

public class TransformationScreen extends Scene {
    private RenderBatch renderBatch;

    @Override
    public void init() {
        camera = new Camera(new Vector2f());
        renderBatch = new RenderBatch(1, 1, null);
        renderBatch.start();
    }


    @Override
    public void update(float dt) {
        renderBatch.render();
        float[] pos = renderBatch.getVertecies();

        transform(pos);

        renderBatch.setVertecies(pos);

    }

    private void transform(float[] pos) {

        int offset = 9;
        Vector3f[] transform = {
                new Vector3f(pos[0], pos[1], 1),
                new Vector3f(pos[offset], pos[offset + 1], 1),
                new Vector3f(pos[offset * 2], pos[offset * 2 + 1], 1),
                new Vector3f(pos[offset * 3], pos[offset * 3 + 1], 1)
        };


        float ceneterx = Math.abs(transform[0].x - transform[1].x) / 2;
        float cenetery = Math.abs(transform[0].y - transform[1].y) / 2;

        for (int i = 0; i < transform.length; i++) {
            Matrix3f matrix3f = new Matrix3f();
            matrix3f.identity();

            if (KeyListener.isKeyPressed(GLFW_KEY_W)) {
                matrix3f.m21 = 6;
            } else if (KeyListener.isKeyPressed(GLFW_KEY_A)) {
                matrix3f.m20 = -6;
            } else if (KeyListener.isKeyPressed(GLFW_KEY_S)) {
                matrix3f.m21 = -6;
            } else if (KeyListener.isKeyPressed(GLFW_KEY_D)) {
                matrix3f.m20 = 6;
            } else if (KeyListener.isKeyPressed(GLFW_KEY_Z)) {
                Matrix3f translate = new Matrix3f();
                translate.identity();

                translate.m20 = transform[3].x;
                translate.m21 = transform[3].y;

                Matrix3f translateBack = new Matrix3f();
                translateBack.identity();

                translateBack.m20 = -1 * transform[3].x;
                translateBack.m21 = -1 * transform[3].y;

                matrix3f.m00 = 1.04f;
                matrix3f.m11 = 1.04f;


                matrix3f = translate.mul(matrix3f).mul(translateBack);

            } else if (KeyListener.isKeyPressed(GLFW_KEY_X)) {
                Matrix3f translate = new Matrix3f();
                translate.identity();

                translate.m20 = transform[3].x;
                translate.m21 = transform[3].y;

                Matrix3f translateBack = new Matrix3f();
                translateBack.identity();

                translateBack.m20 = -1 * transform[3].x;
                translateBack.m21 = -1 * transform[3].y;

                matrix3f.m00 = 0.96f;
                matrix3f.m11 = 0.96f;

                matrix3f = translate.mul(matrix3f).mul(translateBack);


            } else if (KeyListener.isKeyPressed(GLFW_KEY_O)) {
                Matrix3f translate = new Matrix3f();
                translate.identity();

                translate.m20 = transform[3].x + ceneterx;
                translate.m21 = transform[3].y + cenetery;

                Matrix3f translateBack = new Matrix3f();
                translateBack.identity();

                translateBack.m20 = -1 * (transform[3].x + ceneterx);
                translateBack.m21 = -1 * (transform[3].y + cenetery);

                matrix3f.m00 = 1.04f;
                matrix3f.m11 = 1.04f;


                matrix3f = translate.mul(matrix3f).mul(translateBack);
            } else if (KeyListener.isKeyPressed(GLFW_KEY_Q)) {

                Matrix3f translate = new Matrix3f();
                translate.identity();

                translate.m20 = transform[3].x;
                translate.m21 = transform[3].y;

                Matrix3f translateBack = new Matrix3f();
                translateBack.identity();

                translateBack.m20 = -1 * transform[3].x;
                translateBack.m21 = -1 * transform[3].y;

                matrix3f.m00 = (float) Math.cos(0.1);
                matrix3f.m01 = (float) Math.sin(0.1) * -1.0f;
                matrix3f.m10 = (float) Math.sin(0.1);
                matrix3f.m11 = (float) Math.cos(0.1);

                matrix3f = translate.mul(matrix3f).mul(translateBack);

            } else if (KeyListener.isKeyPressed(GLFW_KEY_E)) {

                Matrix3f translate = new Matrix3f();
                translate.identity();

                translate.m20 = transform[3].x;
                translate.m21 = transform[3].y;

                Matrix3f translateBack = new Matrix3f();
                translateBack.identity();

                translateBack.m20 = -1 * transform[3].x;
                translateBack.m21 = -1 * transform[3].y;

                matrix3f.m00 = (float) Math.cos(0.1);
                matrix3f.m01 = (float) Math.sin(0.1);
                matrix3f.m10 = (float) Math.sin(0.1) * -1.0f;
                matrix3f.m11 = (float) Math.cos(0.1);

                matrix3f = translate.mul(matrix3f).mul(translateBack);

            }

            transform[i] = transform[i].mul(matrix3f);
        }

        if (KeyListener.isKeyPressed(GLFW_KEY_P)) {
            System.out.println("position");
            for (int i = 0; i < transform.length; i++) {
                System.out.println(transform[i].x + " " + transform[i].y);
            }

        }


        pos[0] = transform[0].x;
        pos[1] = transform[0].y;
        pos[offset] = transform[1].x;
        pos[offset + 1] = transform[1].y;
        pos[offset * 2] = transform[2].x;
        pos[offset * 2 + 1] = transform[2].y;
        pos[offset * 3] = transform[3].x;
        pos[offset * 3 + 1] = transform[3].y;
    }
}
