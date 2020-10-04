package Movement;

import Entities.Projectiles.ICollidable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * @Author Irja Vuorela
 */

public abstract class AbstractMovable implements IMovable, ICollidable {
    protected double height;
    protected double width;
    protected Rectangle2D hitbox = new Rectangle2D( 0, 0, 1, 1);
    boolean collided = false;

    public void setHitbox(double x, double y, double width, double height){
        this.hitbox = new Rectangle2D(x, y, width, height);
    }

    public Rectangle2D getHitbox(){
        return hitbox;
    }

    // Position (x, y)
    public Point2D position = new Point2D(0, 0);
    // Velocity (horizontal, vertical)
    public Point2D velocity = new Point2D(0, 0);
    // Movement speed
    public double speed = 250;

    // Move self to a new position
    // @Author Irja Vuorela
    public void move(double deltaTime) {
        updatePosition(deltaTime);
    }

    // Update the position of a movable object
    // @Author Irja Vuorela
    public void updatePosition(double deltaTime) {
        this.velocity = velocity.multiply(deltaTime);
        this.position = position.add(velocity.getX(), velocity.getY()); // add() returns a new Point2D
        this.hitbox = new Rectangle2D(position.getX(), position.getY(), this.width, this.height);
    }

    // Setter for self position
    // @Author Tobias Engblom
    public void setPosition(double xPos, double yPos) {
        position = new Point2D(xPos, yPos);
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public boolean getCollided() {
        return this.collided;
    }

    @Override
    public void setCollided(boolean b) {
        this.collided = b;
    }

    public void actOnCollision(Class c){
        //gameObjects.remove(this);
    }
}
