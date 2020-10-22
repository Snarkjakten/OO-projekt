package Model.Movement;

import Model.Entities.HitBox;
import Interfaces.ICollidable;
import Interfaces.IMovable;

import java.util.ArrayList;
import java.util.List;

import Model.Point2D;

public abstract class AbstractGameObject implements IMovable, ICollidable {
    private final List<HitBox> hitBoxes;
    private HitBox unitHitBox;
    private boolean collided = false;
    // Velocity (horizontal, vertical)
    public Point2D velocity;
    // movement speed
    private double speed;

    public AbstractGameObject() {
        this.hitBoxes = new ArrayList<>();
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
     * @param xPos
     * @param yPos
     * @param width
     * @param height
     * @authors Irja & Viktor
     */
    protected void updateHitBoxes(double xPos, double yPos, double width, double height) {
        for (HitBox hitBox : hitBoxes) {
            hitBox.updateHitBox(xPos, yPos, width, height);
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
     * Updates width both for the object and its hitBoxes
     *
     * @param width the new width
     * @author Tobias Engblom
     */
    public void setWidthHitBoxes(double width) {
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
    public void setHeightHitBoxes(double height) {
        for (HitBox hitBox : hitBoxes) {
            hitBox.setHeight(height);
        }
    }

    /**
     * @return the width of the first hitbox
     * @auhtor Irja & Viktor
     */
    public double getWidth() {
        return hitBoxes.get(0).getWidth();
    }

    /**
     * @return the height of the first hitbox
     * @auhtor Irja & Viktor
     */
    public double getHeight() {
        return hitBoxes.get(0).getHeight();

    }

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
