package Controller;

import Model.Game;

/**
 * @author Olle Westerlund
 */
public class AnimationController {
    private final PausableAnimationTimer animationLoop;
    private Game game;
    private double animationTime;
    private double deltaTime;
    private long elapsedTime;

    public AnimationController(Game game) {
        this.game = game;

        animationLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            final long animationNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;

            @Override
            public void tick(long currentNanoTime) {

                currentNanoTime = System.nanoTime();
                deltaTime = calculateDeltaTime(currentNanoTime, previousNanoTime);
                elapsedTime = calculateElapsedTime(getStartNanoTime());
                animationTime = calculateAnimationTime(currentNanoTime, animationNanoTime);

                game.updateWorld(deltaTime, elapsedTime);

                isGameOver();

                previousNanoTime = currentNanoTime;
            }
        };
    }

    private double calculateAnimationTime(long currentTime, long startAnimationTime ) {
        return (currentTime - startAnimationTime) / 1e9;
    }

    /**
     * Calculates time since last update
     *  @author Irja Vuorela
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
            animationLoop.stop();
        }
    }

    public PausableAnimationTimer getAnimationLoop() {
        return animationLoop;
    }

    public double getAnimationTime() {
        return animationTime;
    }
}
