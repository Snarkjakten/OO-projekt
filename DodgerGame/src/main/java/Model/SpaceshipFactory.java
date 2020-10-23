package Model;

import Model.Entities.Player.Spaceship;

/**
 * @author Tobias Engblom
 */
public abstract class SpaceshipFactory {

    public static Spaceship createSpaceship(double x, double y, double width, double height) {
        return new Spaceship(x, y, width, height);
    }
}
