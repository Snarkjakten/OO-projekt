package Interfaces;

/**
 * @author Irja Vuorela
 */
public interface IMovable {

    /**
     * Move self to a new position. DeltaTime helps you calculate how far you should move in each frame of a game loop
     *
     * @param deltaTime the length of time since the last frame in a game loop
     */
    void move(double deltaTime);
}
