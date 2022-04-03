package physics2d.primitives;

import org.joml.Vector2f;
import physics2d.rigidbody.RigidBody2d;
import util.JMath;

public class Box2D {
    private Vector2f size = new Vector2f();
    private Vector2f halfsize = new Vector2f();
    private RigidBody2d rigidBody = null;

    public Box2D() {
        this.halfsize = new Vector2f(size).mul(0.5f);
    }

    public Box2D(Vector2f mim, Vector2f max) {
        this.size = new Vector2f(max).sub(mim);
        this.halfsize = new Vector2f(size).mul(0.5f);

    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidBody.getPosition()).sub(this.halfsize);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidBody.getPosition()).add(this.halfsize);
    }

    public Vector2f getLocalMin() {
        return new Vector2f(this.rigidBody.getPosition()).sub(this.halfsize);
    }

    public Vector2f getLocalMax() {
        return new Vector2f(this.rigidBody.getPosition()).add(this.halfsize);
    }

    public Vector2f[] getVertices() {
        Vector2f min = getLocalMin();
        Vector2f max = getLocalMax();

        Vector2f[] vertices = {
                new Vector2f(min.x, min.y), new Vector2f(min.x, max.y),
                new Vector2f(max.x, min.y), new Vector2f(max.x, max.y),
        };

        if(rigidBody.getRotation() != 0.0f) {
            for (Vector2f vertex : vertices) {
                JMath.rotate(vertex, this.rigidBody.getRotation(), this.rigidBody.getPosition());
            }
        }

        return vertices;

    }

    public void setRigidBody(RigidBody2d rb) {
        this.rigidBody = rb;
    }

    public RigidBody2d getRigidBody() {
        return rigidBody;
    }
}
