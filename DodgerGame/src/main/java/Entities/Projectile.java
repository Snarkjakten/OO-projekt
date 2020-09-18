package Entities;

import javafx.geometry.Point2D;

public class Projectile extends AbstractMovable implements IMovable {

    // Horizontal and vertical values for velocity
    public double horizontal = 0;   // Positive value: right, negative value: left
    public double vertical = 0;     // positive value: down, Negative value: up

    @Override
    // Move self
    // @Author Irja Vuorela
    public void move() {
        updateVelocity();
        updatePosition();

        System.out.println("Projectile moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // Update velocity
    // @Author Irja Vuorela
    public void updateVelocity() {
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        // Multiply with speed
        this.velocity = new Point2D(horizontal * speed, vertical * speed);
    }

    // Setters for velocity horizontal and vertical values
    public void setHorizontal(double h) {
        this.horizontal = h;
    }

    public void setVertical(double v) {
        this.vertical = v;
    }
}
