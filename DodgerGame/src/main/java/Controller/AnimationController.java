package Controller;

import Model.Game;
import javafx.animation.AnimationTimer;

/**
 * @author Olle Westerlund
 */
public class AnimationController {
    private final AnimationTimer animationLoop;
    private final Game game;
    private double animationTime;
    private double deltaTime;
    private long elapsedTime;
    private long startNanoTime;

    public AnimationController(Game game) {
        this.game = game;

        animationLoop = new AnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            final long animationNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;

            @Override
            public void handle(long currentNanoTime) {

                currentNanoTime = System.nanoTime();
                deltaTime = calculateDeltaTime(currentNanoTime, previousNanoTime);
                elapsedTime = calculateElapsedTime(startNanoTime);
                animationTime = calculateAnimationTime(currentNanoTime, animationNanoTime);

                game.updateWorld(deltaTime, elapsedTime);

                isGameOver();

                previousNanoTime = currentNanoTime;
            }
        };
    }

    /**
     * Calculates the animation time
     *
     * @param currentTime        the current time
     * @param startAnimationTime the starting time of the simulation
     * @return the animation time
     * @author Olle Westerlund
     */
    private double calculateAnimationTime(long currentTime, long startAnimationTime) {
        return (currentTime - startAnimationTime) / 1e9;
    }

    /**
     * Calculates time since last update
     *
     * @param currentTime  the current time
     * @param previousTime the time of the last update
     * @author Irja Vuorela
     */
    private double calculateDeltaTime(long currentTime, long previousTime) {
        double deltaTime = (currentTime - previousTime) / 1e9;
        return deltaTime;
    }

    /**
     * Calculates elapsed time.
     *
     * @param startNanoTime time at the start of the simulation
     * @return elapsed time since start of the simulation
     * @author Isak Almeros
     */
    private long calculateElapsedTime(long startNanoTime) {
        long currentNanoTime = System.nanoTime();
        return currentNanoTime - startNanoTime;
    }

    private void isGameOver() {
        if (game.isGameOver()) {
            stopAnimationLoop();
        }
    }

    public double getAnimationTime() {
        return animationTime;
    }

    public void startAnimationLoop() {
        startNanoTime = System.nanoTime();
        game.startGame();
        animationLoop.start();
    }

    public void stopAnimationLoop() {
        animationLoop.stop();
    }


}
