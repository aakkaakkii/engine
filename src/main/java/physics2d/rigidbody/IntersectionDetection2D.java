package physics2d.rigidbody;

import org.joml.Vector2f;
import physics2d.primitives.AABB;
import physics2d.primitives.Box2D;
import physics2d.primitives.Circle;
import renderer.Line2D;
import util.JMath;


public class IntersectionDetection2D {
    public static boolean pointOnLine(Vector2f point, Line2D line) {
        float dy = line.getEnd().y - line.getStart().y;
        float dx = line.getEnd().x - line.getStart().x;

        if (dx == 0f) {
            return JMath.compare(point.x, line.getStart().x);
        }

        float m = dy / dx;
        float b = line.getEnd().y - (m * line.getEnd().x);

        return point.y == m * point.x + b;
    }

    public static boolean pointInCircle(Vector2f point, Circle circle) {
        Vector2f circleCenter = circle.getCenter();
        Vector2f centerToPoint = new Vector2f(point).sub(circleCenter);

        return centerToPoint.lengthSquared() <= circle.getRadius() * circle.getRadius();
    }

    public static boolean pointInAABB(Vector2f point, AABB box) {
        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return point.x <= max.x && min.x <= point.x
                && point.y <= max.y && min.y <= point.y;
    }

    public static boolean pointInBox2D(Vector2f point, Box2D box) {

        Vector2f pointLocalBoxSpace = new Vector2f(point);
        JMath.rotate(pointLocalBoxSpace, box.getRigidBody().getRotation(),
                box.getRigidBody().getPosition());

        Vector2f min = box.getMin();
        Vector2f max = box.getMax();

        return pointLocalBoxSpace.x <= max.x && min.x <= pointLocalBoxSpace.x
                && pointLocalBoxSpace.y <= max.y && min.y <= pointLocalBoxSpace.y;

    }

    public static boolean lineAndCircle(Line2D line, Circle circle) {
        if (pointInCircle(line.getStart(), circle) || pointInCircle(line.getEnd(), circle)) {
            return true;
        }
        Vector2f ab = new Vector2f(line.getEnd()).sub(line.getStart());

        //Projection
        Vector2f circleCenter = circle.getCenter();
        Vector2f centerToLineStart = new Vector2f(circleCenter).sub(line.getStart());
        float t = centerToLineStart.dot(ab) / ab.dot(ab);


        if (t < 0.0f || t > 1.0f) {
            return false;
        }

        // Find the closest point to the line segment
        Vector2f closestPoint = new Vector2f(line.getStart()).add(ab.mul(t));

        return pointInCircle(closestPoint, circle);
    }

    public static boolean lineAndAABB(Line2D line, AABB box) {
        if (pointInAABB(line.getStart(), box) || pointInAABB(line.getEnd(), box)) {
            return true;
        }

        Vector2f unitVector = new Vector2f(line.getEnd()).sub(line.getStart());
        unitVector.normalize();
        unitVector.x = (unitVector.x != 0) ? 1.0f / unitVector.x : 0f;
        unitVector.y = (unitVector.y != 0) ? 1.0f / unitVector.y : 0f;

        Vector2f min = box.getMin();
        min.sub(line.getStart()).mul(unitVector);
        Vector2f max = box.getMax();
        max.sub(line.getStart()).mul(unitVector);

        float tmin = Math.max(Math.min(min.x, max.x), Math.min(min.y, max.y));
        float tmax = Math.min(Math.max(min.x, max.x), Math.max(min.y, max.y));
        if (tmax < 0 || tmin > tmax) {
            return false;
        }

        float t = (tmin < 0f) ? tmax : tmin;
        return t > 0f && t * t < line.lengthSquared();
    }

    public static boolean lineAndBox2D(Line2D line, Box2D box) {
        float theta = -box.getRigidBody().getRotation();
        Vector2f center = box.getRigidBody().getPosition();
        Vector2f localStart = new Vector2f(line.getStart());
        Vector2f localEnd = new Vector2f(line.getEnd());
        JMath.rotate(localStart, theta, center);
        JMath.rotate(localEnd, theta, center);

        Line2D localLine = new Line2D(localStart, localEnd);
        AABB aabb = new AABB(box.getLocalMin(), box.getLocalMax());

        return lineAndAABB(localLine, aabb);
    }

    public static boolean circleAndLine(Circle circle, Line2D line) {
        return lineAndCircle(line, circle);
    }

    public static boolean circleAndCircle(Circle c1, Circle c2) {
        Vector2f vecBetweenCenters = new Vector2f(c1.getCenter()).sub(c2.getCenter());
        float radiiSum = c1.getRadius() + c2.getRadius();
        return vecBetweenCenters.lengthSquared() <= radiiSum * radiiSum;
    }
}
