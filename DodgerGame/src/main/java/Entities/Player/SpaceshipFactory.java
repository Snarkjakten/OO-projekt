package Entities.Player;

public abstract class SpaceshipFactory {

    /**
     * @Author Tobias Engblom
     */
    public static Spaceship createSpaceship(double x, double y) {
        return new Spaceship(x, y);
    }
}
