package Entities.Player;

import Movement.AbstractMovable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable {

    // Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value
    private SimpleIntegerProperty hp = new SimpleIntegerProperty(200);

    public Spaceship(double x, double y) {
        setPosition(x, y);
        this.width = 64;
        this.height = 64;
    }


    // Move self to a new position
    // @Author Irja Vuorela
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);

        // todo: remove print
//         System.out.println("Spaceship moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // @Author Irja Vuorela
    private void updateVelocity() {
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
