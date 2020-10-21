package Model.Movement;

import Interfaces.ICollidable;
import Interfaces.IMovable;
import Model.Entities.HitBox;
import Model.Point2D;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameObject implements IMovable, ICollidable {
    private double width;
    private double height;
    private final List<HitBox> hitBoxes;
    private boolean collided;
    // Velocity (horizontal, vertical)
    public Point2D velocity;
    // movement speed
    private double speed;

    public CollisionHandler collisionHandler = new CollisionHandler();

    public AbstractGameObject(double width, double height) {
        this.width = width;
        this.height = height;
        this.hitBoxes = new ArrayList<>();
        this.collided = false;
        this.speed = 250;
        this.velocity = new Point2D(0, 0);
    }

    //------------------------------------------------------

    /**
     * Move self to a new position.
     *
     * @param deltaTime the length of a frame in the game loop
     * @author Irja Vuorela
     */
    public void move(double deltaTime) {
        updatePosition(deltaTime);
    }

    /**
     * Update the position of a movable object
     *
     * @param deltaTime the length of a frame in the game loop
     * @authors Irja Vuorela and Tobias Engblom
     */
    protected void updatePosition(double deltaTime) {
        this.velocity = velocity.multiply(deltaTime);
        for (HitBox hitBox : getHitBoxes()) {
            hitBox.updatePosition(velocity.getX(), velocity.getY());
        }
    }

    /**
     * Updates width both for the object and its hitBoxes
     *
     * @param width the new width
     * @author Tobias Engblom
     */
    public void updateWidthHitboxes(double width) {
        this.width = width;
        for (HitBox hitBox : hitBoxes) {
            hitBox.setWidth(width);
        }
    }

    /**
     * Updates height both for the object and its hitBoxes
     *
     * @param height the new height
     * @author Tobias Engblom
     */
    public void updateHeightHitboxes(double height) {
        this.height = height;
        for (HitBox hitBox : hitBoxes) {
            hitBox.setHeight(height);
        }
    }

    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param c The gameObject this gameObject collided with
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public void actOnCollision(AbstractGameObject c) {
        //todo: this is empty
    }

    // Getters and setters -------------------------------------------------------

    /**
     * @return the hitBoxes for this game object
     * @author Tobias Engblom
     */
    public List<HitBox> getHitBoxes() {
        return this.hitBoxes;
    }

    /**
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public boolean getCollided() {
        return this.collided;
    }

    @Override
    public void setCollided(boolean b) {
        this.collided = b;
    }

    /**
     * @param width the width of this object
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the width of this game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param height the height of this object
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the height of this game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the speed of this game object
     * @author Isak Almeros
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the new speed to this game object
     *
     * @param speed the new speed
     * @author Isak Almeros
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

}
