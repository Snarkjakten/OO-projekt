package Movements;

import javafx.geometry.Point2D;

/*
 * @Author Irja Vuorela
 */

public abstract class AbstractMovable implements IMovable {

    // Position (x, y)
    public Point2D position = new Point2D(0, 0);
    // Velocity (horizontal, vertical)
    public Point2D velocity = new Point2D(0, 0);
    // Movement speed
    public double speed = 5;

    // Move self to a new position
    // @Author Irja Vuorela
    public void move() {
        updatePosition();
    }

    // Update position
    // @Author Irja Vuorela
    public void updatePosition() {
        this.position = position.add(velocity.getX(), velocity.getY()); // add() returns a new Point2D
    }
}
