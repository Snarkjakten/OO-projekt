package Entities;

import Entities.AbstractMovable;
import Entities.IMovable;
import javafx.geometry.Point2D;

// A ship to be controlled by the player
public class Spaceship extends AbstractMovable implements IMovable {

    // Movement directions
    public int up = 0;      // moving up decreases vertical axis value
    public int down = 0;    // moving down increases vertical axis value
    public int left = 0;    // moving left decreases horizontal axis value
    public int right = 0;   // moving right increases horizontal axis value

    // Move self to a new position
    // @Author Irja Vuorela
    @Override
    public void move() {
        updateVelocity();
        updatePosition();

        // todo: remove print
        System.out.println("Spaceship moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // Update velocity
    // @Author Irja Vuorela
    public void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = new Point2D(velocity.getX() * speed, velocity.getY() * speed);
    }

    // Setters for movement directions
    public void setUp(int up) {
        this.up = up;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
