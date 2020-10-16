package Game.Movement;

import Game.Entities.Player.HitBox;
import Interfaces.ICollidable;
import Interfaces.IMovable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Irja Vuorela
 */

public abstract class AbstractGameObject implements IMovable, ICollidable {
    private double width;
    private double height;
    private final List<HitBox> hitBoxes;
    private boolean collided;
    // Game movement speed
    private double speed;

    public AbstractGameObject() {
        this.hitBoxes = new ArrayList<>();
        this.collided = false;
        this.speed = 250;
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
        for (HitBox hitBox : getHitBoxes()) {
            hitBox.velocity = hitBox.getVelocity().multiply(deltaTime);
            hitBox.updatePosition(hitBox.getVelocity().getX(), hitBox.getVelocity().getY());
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


    /**
     * Acts upon the collision based on instance of projectile
     *
     * @param c The gameobject this gameobject collided with
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public void actOnCollision(AbstractGameObject c) {
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }
}