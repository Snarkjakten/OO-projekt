package Interfaces;

import Game.Movement.AbstractGameObject;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public interface ICollidable {

    void setCollided(boolean collided);

    boolean getCollided();

    void actOnCollision(AbstractGameObject c);

}

