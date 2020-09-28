package Entities;

import javafx.geometry.Rectangle2D;

/**
 * @Author Viktor Sundeberg (viktor.sundberg@icloud.com)
 */

public interface ICollidable {

    boolean isCollided(ICollidable other);

    void onCollision(ICollidable other);

    void effectOfCollision(ICollidable other);

    Rectangle2D getBoundary();

    double getDamage();
}