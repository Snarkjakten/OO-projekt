package Interfaces;

import Model.Movement.AbstractGameObject;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public interface ICollidable {

    boolean collided = false;

    void setCollided(boolean collided);

    boolean getCollided();

    void actOnCollision(AbstractGameObject c);

}

