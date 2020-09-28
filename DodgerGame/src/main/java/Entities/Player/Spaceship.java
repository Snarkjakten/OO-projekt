package Entities.Player;

import Entities.ICollidable;
import Movement.AbstractMovable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.InputStream;

// A spaceship to be controlled by the player
public class Spaceship extends AbstractMovable implements ICollidable {

    // Movement directions
    private int up = 0; // moving up decreases vertical axis value
    private int down = 0; // moving down increases vertical axis value
    private int left = 0; // moving left decreases horizontal axis value
    private int right = 0; // moving right increases horizontal axis value
    private boolean isActive;
    private Image image;
    private SimpleIntegerProperty hp = new SimpleIntegerProperty(200);

    public Spaceship(boolean isActive, double x, double y) {
        this.image = addImageToSpaceship();
        this.isActive = isActive;
        setPosition(x, y);
    }

    private Image addImageToSpaceship() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("spaceship.gif");
        image = new Image(inputStream);
        return image;
    }

    // Move self to a new position
    // @Author Irja Vuorela
    @Override
    public void move(double deltaTime) {
        updateVelocity();
        updatePosition(deltaTime);

        // todo: remove print
        // System.out.println("Spaceship moved to (" + position.getX() + ", " + position.getY() + ")");
    }

    // @Author Irja Vuorela
    private void updateVelocity() {
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
    public Image getImage() {
        return image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    @Override
    public boolean isCollided(ICollidable other) {
        return this.getBoundary().intersects(other.getBoundary());
    }

    @Override
    public void onCollision(ICollidable other) {

    }

    @Override
    public void effectOfCollision(ICollidable other) {

    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.position.getX(), this.position.getY(), this.image.getWidth()/2, this.image.getWidth()/2);
    }

    @Override
    public double getDamage() {
        return 0;
    }
}
