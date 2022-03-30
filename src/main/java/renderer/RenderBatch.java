package renderer;

import core.Window;
import util.AssetPool;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;


public class RenderBatch {
    // Vertex
    // ======
    // Pos               Color
    // float, float,     float, float, float, float
    private final int POS_SIZE = 3;
    private final int COLOR_SIZE = 4;
    private final int TEX_COORDS_SIZE = 2;
    private final int TEX_ID_SIZE = 1;

    private final int POS_OFFSET = 0;
    private final int COLOR_OFFSET = POS_OFFSET + POS_SIZE * Float.BYTES;
    private final int TEX_COORDS_OFFSET = COLOR_OFFSET + COLOR_SIZE * Float.BYTES;
    private final int TEXT_ID_OFFSET = TEX_COORDS_OFFSET + TEX_COORDS_SIZE * Float.BYTES;
//    private final int VERTEX_SIZE = 9;
    private final int VERTEX_SIZE = 7;
    private final int VERTEX_SIZE_BYTES = VERTEX_SIZE * Float.BYTES;

    private int vaoId, vboId;

    private float[] vertices = {
            100.5f, -0.5f, 0.0f,      1.0f, 1.0f, 1.0f, 1.0f,
            -0.5f, 100.5f, 0.0f,      0.0f, 0.0f, 0.0f, 1.0f,
            100.5f, 100.5f, 0.0f,      1.0f, 0.0f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.0f,      1.0f, 1.0f, 0.0f, 1.0f,
    };
    private Shader shader;

    public RenderBatch(int maxBatchSize, int zIndex) {
        shader = AssetPool.getShader("assets/shaders/test.glsl");
    }


    public void start() {
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        vboId = glGenBuffers();
        // Allocate space for vertices
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices.length * Float.BYTES, GL_DYNAMIC_DRAW);

        //Create and upload indices buffer
        int eboId = glGenBuffers();
        int[] indices = {2, 1, 0, 0, 1, 3};
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, POS_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, POS_OFFSET);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, COLOR_SIZE, GL_FLOAT, false, VERTEX_SIZE_BYTES, COLOR_OFFSET);
        glEnableVertexAttribArray(1);
    }

    public void render() {

        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

        shader.use();
        shader.uploadMat4f("uProjection", Window.getScene().camera().getProjectionMatrix());
        shader.uploadMat4f("uView", Window.getScene().camera().getViewMatrix());

        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES,  6, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        shader.detach();
    }

    private void loadVertexProperties(int index) {
//        vertices[0] = 0f;
//        vertices[1] = 0f;
//
//        vertices[2] = 1f;
//        vertices[3] = 0f;
//
//        vertices[4] = 0f;
//        vertices[5] = 1f;
//
//        vertices[6] = 0f;
//        vertices[7] = 0f;
    }
}
