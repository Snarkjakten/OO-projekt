package Entities.Player;

import Movement.AbstractMovable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable {

    // Movement directions
    public int up;
    public int down;
    public int left;
    public int right;

    private SimpleIntegerProperty hp = new SimpleIntegerProperty(200);

    // Spaceship constructor
    public Spaceship() {
        this.up = 0;    // moving up decreases vertical axis value
        this.down = 0;  // moving down increases vertical axis value
        this.left = 0;  // moving left decreases horizontal axis value
        this.right = 0; // moving right increases horizontal axis value
    }

    // Move self to a new position
    // @Author Irja Vuorela
    @Override
    public void move() {
        updateVelocity();
        updatePosition();

        // todo: remove print
        System.out.println("Spaceship moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // @Author Irja Vuorela
    public void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = velocity.multiply(speed);
    }

    public SimpleIntegerProperty getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp.set(hp);
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
