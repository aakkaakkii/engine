package physics2d.forces;

import physics2d.rigidbody.RigidBody2d;

import java.util.Objects;

public class ForceRegistration {
    public ForceGenerator fg;
    public RigidBody2d rb;

    public ForceRegistration(ForceGenerator fg, RigidBody2d rb) {
        this.fg = fg;
        this.rb = rb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForceRegistration that = (ForceRegistration) o;
        return Objects.equals(fg, that.fg) && Objects.equals(rb, that.rb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fg, rb);
    }
}
