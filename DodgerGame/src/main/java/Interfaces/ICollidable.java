package Interfaces;

import Model.Entities.AbstractGameObject;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public interface ICollidable {

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

