package Entities.Player;

/**
 * @author Tobias Engblom
 */
public abstract class SpaceshipFactory {

    public static Spaceship createSpaceship(double x, double y) {
        return new Spaceship(x, y);
    }
}
