package Entities.Player;

public abstract class SpaceshipFactory {

    /**
     * @Author Tobias Engblom
     */
    public static Spaceship createSpaceship(boolean isActive, double x, double y) {
        return new Spaceship(isActive, x, y);
    }
}
