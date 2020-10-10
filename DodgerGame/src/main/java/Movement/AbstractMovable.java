package Movement;

import javafx.geometry.Point2D;

/**
 * @Author Irja Vuorela
 */

public abstract class AbstractMovable implements IMovable {
    protected double height;
    protected double width;

    // Position (x, y)
    public Point2D position = new Point2D(0, 0);
    // Velocity (horizontal, vertical)
    public Point2D velocity = new Point2D(0, 0);
    // Movement speed
    public double speed = 250;

    /**
     * Move self to a new position
     *
     * @Author Irja Vuorela
     */
    public void move(double deltaTime) {
        updatePosition(deltaTime);
    }

    /**
     * Update the position of a movable object
     *
     * @Author Irja Vuorela
     */
    public void updatePosition(double deltaTime) {
        this.velocity = velocity.multiply(deltaTime);
        this.position = position.add(velocity.getX(), velocity.getY()); // add() returns a new Point2D
    }

    /**
     * Getter for position
     * @return position
     * @author Irja Vuorela
     */
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    // Setter for self position
    // @Author Tobias Engblom
    public void setPosition(double xPos, double yPos) {
        position = new Point2D(xPos, yPos);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
