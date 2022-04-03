package physics2d.forces;

import physics2d.rigidbody.RigidBody2d;

public interface ForceGenerator {
    void updateForce(RigidBody2d body2d, float dt);
}
