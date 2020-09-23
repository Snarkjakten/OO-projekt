package Entities.Player;

import Movement.AbstractMovable;
import javafx.geometry.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable {

    // Movement directions
    public int up;      // moving up decreases vertical axis value
    public int down;    // moving down increases vertical axis value
    public int left;    // moving left decreases horizontal axis value
    public int right;   // moving right increases horizontal axis value

    // Spaceship constructor
    public Spaceship() {
        this.up = 0;
        this.down = 0;
        this.left = 0;
        this.right = 0;
    }

    // Move self to a new position
    // @Author Irja Vuorela
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    // @Author Irja Vuorela
    public void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = velocity.multiply(speed);
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
