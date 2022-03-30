package scenes;

import org.lwjgl.BufferUtils;
import renderer.Shader;
import util.AssetPool;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;

public class TestScene extends Scene {
    Shader shader;
    private float[] vertexArray = {
            0.5f, -0.5f, 0.0f,      1.0f, 0.0f, 0.0f, 1.0f,
            -0.5f, 0.5f, 0.0f,      1.0f, 0.0f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.0f,      1.0f, 0.0f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.0f,      1.0f, 0.0f, 0.0f, 1.0f,
    };

    private int[] elementArray = {
            2, 1 , 0,
            0, 1 , 3,
    };

    private int vaoId, vboId, eboId;

    public TestScene() {
    }

    @Override
    public void init() {
        shader = AssetPool.getShader("assets/shaders/test.glsl");
        shader.compile();

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        int posSize = 3;
        int colorSize = 4;
        int vertexSizeBytes = (posSize + colorSize) * Float.BYTES;
        glVertexAttribPointer(0, posSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, posSize, GL_FLOAT, false, vertexSizeBytes, posSize * Float.BYTES);
        glEnableVertexAttribArray(1);

    }

    @Override
    public void update(float dt) {
        shader.use();

        glBindVertexArray(vaoId);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        shader.detach();

    }


}
