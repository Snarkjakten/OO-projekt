package Model.Entities;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class HitBox {

    private final double width;
    private final double height;
    // Position (x, y)
    private Point2D position;
    private Rectangle2D hitBox;

    public HitBox(double xPos, double yPos, double width, double height) {
        this.width = width * 0.75;
        this.height = height * 0.75;
        this.position = new Point2D(xPos, yPos);
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

    public void updatePosition(double xVelocity, double yVelocity) {
        this.position = position.add(xVelocity, yVelocity);
        setHitBox(this.position.getX(), this.position.getY(), this.width, this.height);
    }

    public Rectangle2D getHitBox() {
        return hitBox;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public void setPosition(double xPos, double yPos) {
        this.position = new Point2D(xPos, yPos);
    }
}
