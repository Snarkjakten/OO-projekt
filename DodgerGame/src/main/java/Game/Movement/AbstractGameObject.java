package Game.Movement;

import Interfaces.ICollidable;
import Interfaces.IMovable;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * @Author Irja Vuorela
 */

public abstract class AbstractGameObject implements IMovable, ICollidable {
    protected double height;
    protected double width;
    protected Rectangle2D hitbox = new Rectangle2D( 0, 0, 1, 1);
    boolean collided = false;

    //Currently unused
    //@Author Viktor Sundberg (viktor.sundberg@icloud.com)
    public void setHitbox(double x, double y, double width, double height){
        this.hitbox = new Rectangle2D(x, y, width * 0.75, height * 0.75);
    }

    public Rectangle2D getHitbox(){
        return hitbox;
    }

    //------------------------------------------------------

    // Position (x, y)
    public Point2D position = new Point2D(0, 0);
    // Velocity (horizontal, vertical)
    public Point2D velocity = new Point2D(0, 0);
    // Game.Movement speed
    public double speed = 250;

    /**
     * Move self to a new position
     *
     * @Author Irja Vuorela
     */
    public void move(double deltaTime) {
        updatePosition(deltaTime);
    }

    /**
     * Update the position of a movable object
     *
     * @Author Irja Vuorela
     */
    protected void updatePosition(double deltaTime) {
        this.velocity = velocity.multiply(deltaTime);
        this.position = position.add(velocity.getX(), velocity.getY()); // add() returns a new Point2D
        this.setHitbox(position.getX(), position.getY(), this.width * 0.75, this.height * 0.75);
    }

    /**
     * Getter for position
     * @return position
     * @author Irja Vuorela
     */
    @Override
    public Point2D getPosition() {
        return this.position;
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

    //@Author Viktor Sundberg (viktor.sundberg@icloud.com)
    @Override
    public boolean getCollided() {
        return this.collided;
    }

    @Override
    public void setCollided(boolean b) {
        this.collided = b;
    }

    @Override
    public void actOnCollision(AbstractGameObject c){ }

    //-------------------------------------------------------
}
