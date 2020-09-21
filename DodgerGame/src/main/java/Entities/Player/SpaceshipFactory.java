package Entities.Player;

public abstract class SpaceshipFactory {

    /**
     * @Author Tobias Engblom
     */
    public static Spaceship createSpaceship() {
        return new Spaceship();
    }
}
