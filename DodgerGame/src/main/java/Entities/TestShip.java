package Entities;

import Entities.AbstractMovable;
import Entities.IMovable;
import javafx.geometry.Point2D;

// A ship to be controlled by the player
public class TestShip extends AbstractMovable implements IMovable {

    // Movement directions
    public int up = 0;
    public int down = 0;
    public int left = 0;
    public int right = 0;


    // Move self
    // @Author Irja Vuorela
    @Override
    public void move() {
        updateVelocity();
        updatePosition();

        // todo: remove print
        System.out.println("TestShip moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // Update velocity
    // @Author Irja Vuorela
    public void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity
        this.velocity = (new Point2D((right - left), (up - down))).normalize();
        // Multiply direction with speed
        this.velocity = new Point2D(velocity.getX() * speed, velocity.getY() * speed);
    }
}
