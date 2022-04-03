package physics2d.primitives;

import org.joml.Vector2f;
import physics2d.rigidbody.RigidBody2d;

public class Circle {
    private float radius = 1.0f;
    private RigidBody2d rigidBody = null;

    public float getRadius() {
        return radius;
    }

    public Vector2f getCenter() {
        return rigidBody.getPosition();
    }

    public void setRigidBody(RigidBody2d rb) {
        this.rigidBody = rb;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
