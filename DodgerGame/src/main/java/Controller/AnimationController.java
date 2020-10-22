package Controller;

import Model.Game;

/**
 * @author Olle Westerlund
 */
public class AnimationController {
    private final PausableAnimationTimer animationLoop;
    private Game game;

    public AnimationController(Game game) {
        this.game = game;

        animationLoop = new PausableAnimationTimer() {

            final long currentNanoTime = System.nanoTime();
            long previousNanoTime = currentNanoTime;

            @Override
            public void tick(long currentNanoTime) {

                /**
                 * Calculates time since last update
                 *  @author Irja Vuorela
                 */
                currentNanoTime = System.nanoTime();
                double deltaTime = (currentNanoTime - previousNanoTime) / 1e9;
                long elapsedTime = calculateElapsedTime(getStartNanoTime());

                game.updateWorld(deltaTime, elapsedTime);

                isGameOver();

                previousNanoTime = currentNanoTime;
            }
        };
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
}
