package Model.Entities.Player;

import Model.Movement.AbstractGameObject;
import Model.Point2D;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractGameObject {

    // Game.Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value

    public Spaceship(double x, double y) {
        setPosition(x, y);
        this.width = 64;
        this.height = 64;
        this.setSpeed(300);
    }

    /**
     * Move self to a new position
     *
     * @Author Irja Vuorela
     */
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);
    }

    /**
     * Updates velocity
     *
     * @Author Irja Vuorela
     */
    private void updateVelocity() {
        // Stop if moving in two opposite directions simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity = velocity.multiply(getSpeed());
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

    // @Author Tobias Engblom
    // Sets this direction to the spaceships direction
    public void setDirection(Spaceship spaceship) {
        this.up = spaceship.up;
        this.down = spaceship.down;
        this.left = spaceship.left;
        this.right = spaceship.right;
    }

    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param c
     * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public void actOnCollision(AbstractGameObject c) {
        c.setCollided(true);
    }

    public void resetDirection() {
        this.up = 0;
        this.down = 0;
        this.left = 0;
        this.right = 0;
    }
}
