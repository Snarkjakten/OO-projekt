package Game.Entities.Player;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class HitBox {

    private final double width;
    private final double height;
    // Position (x, y)
    private Point2D position;
    // Velocity (horizontal, vertical)
    public Point2D velocity;
    private Rectangle2D hitBox;

    public HitBox(double xPos, double yPos, double width, double height) {
        this.width = width * 0.75;
        this.height = height * 0.75;
        this.position = new Point2D(xPos, yPos);
        this.velocity = new Point2D(0, 0);
        setHitBox(xPos, yPos, this.width, this.height);
    }

    public double getXPos() {
        return this.hitBox.getMinX();
    }

    public double getYPos() {
        return this.hitBox.getMinY();
    }

    private void setHitBox(double xPos, double yPos, double width, double height) {
        this.hitBox = new Rectangle2D(xPos, yPos, width, height);
    }

    public void setVelocity(int up, int down, int left, int right, double speed) {
        // Stop if moving in two opposite direction simultaneously
        // Normalize velocity (keep same direction and turn into a unit vector)
        this.velocity = (new Point2D((right - left), (down - up))).normalize();
        // Multiply direction with speed
        this.velocity.multiply(speed);
    }

    public void setVelocity(double horizontal, double vertical, double speed) {
        this.velocity = (new Point2D(horizontal, vertical).normalize());
        this.velocity.multiply(speed);
    }

    public Point2D getVelocity() {
        return this.velocity;
    }

    public void updatePosition(double xPos, double yPos) {
        setPosition(xPos, yPos);
        setHitBox(this.position.getX(), this.position.getY(), this.width, this.height);
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(double xPos, double yPos) {
        this.position = position.add(xPos, yPos);
    }
}
