package physics2d.forces;

import physics2d.rigidbody.RigidBody2d;

import java.util.List;

public class ForceRegistry {
    private List<ForceRegistration> registry;

    public ForceRegistry(List<ForceRegistration> registry) {
        this.registry = registry;
    }

    public void add(RigidBody2d rb, ForceGenerator fg) {
        ForceRegistration fr = new ForceRegistration(fg, rb);
        registry.add(fr);
    }

    public void remove(RigidBody2d rb, ForceGenerator fg) {
        ForceRegistration fr = new ForceRegistration(fg, rb);
        registry.remove(fr);
    }

    public void clear() {
        registry.clear();
    }

    public void updateForces(float dt) {
        for (ForceRegistration fr : registry) {
            fr.fg.updateForce(fr.rb, dt);
        }
    }

    public void zeroForces() {
        for (ForceRegistration forceRegistration : registry) {

        }
    }
}
