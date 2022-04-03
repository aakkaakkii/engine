package physics2d.primitives;

import org.joml.Vector2f;
import physics2d.rigidbody.RigidBody2d;

public class AABB {
    private Vector2f size = new Vector2f();
    private Vector2f halfsize = new Vector2f();
    private RigidBody2d rigidBody = null;

    public AABB() {
        this.halfsize = new Vector2f(size).mul(0.5f);
    }

    public AABB(Vector2f mim, Vector2f max) {
        this.size = new Vector2f(max).sub(mim);
        this.halfsize = new Vector2f(size).mul(0.5f);

    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidBody.getPosition()).sub(this.halfsize);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidBody.getPosition()).add(this.halfsize);
    }

    public void setRigidBody(RigidBody2d rb) {
        this.rigidBody = rb;
    }

}
