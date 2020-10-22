package Interfaces;

import Model.Movement.AbstractGameObject;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public interface ICollidable {

    /**
     * If this object has collided or not
     */
    boolean collided = false;

    /**
     * @param collided if this object has collided
     */
    void setCollided(boolean collided);

    /**
     * @return boolean isCollided
     */
    boolean getCollided();

    /**
     * @param c the type of object this object has collided with
     */
    void actOnCollision(AbstractGameObject c);

}

