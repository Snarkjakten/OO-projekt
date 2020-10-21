package Interfaces;

public interface IGameOverObserver {

    /**
     * Perform actions associated with the game ending.
     *
     * @param isGameOver if the game is over
     * @param finalScore the final score from when the game is over
     */
    void actOnGameOverEvent(boolean isGameOver, int finalScore);
}
