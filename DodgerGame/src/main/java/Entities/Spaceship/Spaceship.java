package Entities.Spaceship;

import Movements.AbstractMovable;
import javafx.geometry.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable {

    // Movement directions
    public int up;
    public int down;
    public int left;
    public int right;

    // Spaceship constructor
    public Spaceship() {
        this.up = 0;
        this.down = 0;
        this.left = 0;
        this.right = 0;
    }

    // Setter for spaceship position
    // @Author Tobias Engblom
    protected void setPosition(double xPos, double yPos) {
        position = new Point2D(xPos, yPos);
    }

    // Move self
    // @Author Irja Vuorela
    @Override
    public void move() {
        updateVelocity();
        updatePosition();
        System.out.println("Ship moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // @Author Irja Vuorela
    public void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = velocity.multiply(speed);
    }

    // Setters for directions
    // @Author Irja Vuorela
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
