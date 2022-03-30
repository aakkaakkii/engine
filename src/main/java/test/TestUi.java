package test;

import renderer.RenderBatch;

public class TestUi {
    private RenderBatch renderer;

    public TestUi() {
        renderer = new RenderBatch(1, 1);
        renderer.start();
    }

    public void update() {
        renderer.render();
    }

}
