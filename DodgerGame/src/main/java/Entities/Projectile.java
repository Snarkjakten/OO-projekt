package Entities;

import javafx.geometry.Point2D;

public class Projectile extends AbstractMovable implements IMovable {

    // Horizontal and vertical values for velocity
    public double horizontal = 0;     // positive value: right, negative value: left
    public double vertical = 0;         // positive value: up, negative value: down

    @Override
    // Move self
    // @Author Irja Vuorela
    public void move() {
        updateVelocity();
        updatePosition();

        // System.out.println("Projectile moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // Update velocity
    // @Author Irja Vuorela
    public void updateVelocity() {
        // Normalize velocity
        this.velocity = (new Point2D(horizontal, vertical)).normalize();
        // Multiply with speed
        this.velocity = new Point2D(horizontal * speed, vertical * speed);
    }

    // Setters
    public void setHorizontal(double h) {
        this.horizontal = h;
    }

    public void setVertical(double v) {
        this.vertical = v;
    }
}
