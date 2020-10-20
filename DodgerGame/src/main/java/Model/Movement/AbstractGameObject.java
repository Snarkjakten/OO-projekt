package Model.Movement;

import Model.Entities.HitBox;
import Interfaces.ICollidable;
import Interfaces.IMovable;
import com.sun.javafx.scene.text.TextLayout;
import javafx.geometry.Point2D;

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
    // Velocity (horizontal, vertical)
    public Point2D velocity;
    // Game movement speed
    private double speed;

    public AbstractGameObject(double width, double height) {
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }

    public void updateWidth(double width) {
        this.width = width;
        for (HitBox hitBox : hitBoxes) {
            hitBox.setWidth(width);
        }
    }

    public double getHeight() {
        return height;
    }

    public void updateHeight(double height) {
        this.height = height;
        for (HitBox hitBox : hitBoxes) {
            hitBox.setHeight(height);
        }
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
