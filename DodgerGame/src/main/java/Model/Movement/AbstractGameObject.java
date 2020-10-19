package Model.Movement;

import Model.Entities.HitBox;
import Interfaces.ICollidable;
import Interfaces.IMovable;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Irja Vuorela
 */

public abstract class AbstractGameObject implements IMovable, ICollidable {
    protected double width;
    protected double height;
    private final List<HitBox> hitBoxes;
    private boolean collided;
    // Velocity (horizontal, vertical)
    public Point2D velocity;
    // Game movement speed
    private double speed;

    public AbstractGameObject() {
        this.hitBoxes = new ArrayList<>();
        this.collided = false;
        this.speed = 250;
        this.velocity = new Point2D(0, 0);
    }

    public List<HitBox> getHitBoxes() {
        return this.hitBoxes;
    }

    //------------------------------------------------------

    /**
     * Move self to a new position
     *
     * @author Irja Vuorela
     */
    public void move(double deltaTime) {
        updatePosition(deltaTime);
    }

    /**
     * Update the position of a movable object
     *
     * @author Irja Vuorela and Tobias Engblom
     */
    protected void updatePosition(double deltaTime) {
        this.velocity = velocity.multiply(deltaTime);
        for (HitBox hitBox : getHitBoxes()) {
            hitBox.updatePosition(velocity.getX(), velocity.getY());
        }
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    //-------------------------------------------------------

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

    // @Author Isak Almeros
    public double getSpeed() {
        return speed;
    }

    // @Author Isak Almeros
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param c The gameobject this gameobject collided with
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public void actOnCollision(AbstractGameObject c) {
    }
}
