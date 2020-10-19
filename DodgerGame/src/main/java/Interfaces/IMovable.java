package Interfaces;

import Model.Point2D;

/**
 * @Author Irja Vuorela
 */

public interface IMovable {

    // Move self
    void move(double deltaTime);

    Point2D getPosition();
}
