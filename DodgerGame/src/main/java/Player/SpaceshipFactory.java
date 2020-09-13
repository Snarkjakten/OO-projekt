package Player;

public abstract class SpaceshipFactory {

    public static Spaceship createSpaceship() {
        return new Spaceship();
    }
}
