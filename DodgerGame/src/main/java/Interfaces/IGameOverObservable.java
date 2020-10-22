package Interfaces;

public interface IGameOverObservable {

    /**
     * @param isGameOver if the game is over
     * @param finalScore the final score from when the game is ended
     */
    void notifyGameOverObservers(boolean isGameOver, int finalScore);

    /**
     * @param obs the observer to be added to a list of observers
     */
    void addGameOverObserver(IGameOverObserver obs);

    /**
     * @param obs the observer to be removed from a list of observers
     */
    void removeGameOverObserver(IGameOverObserver obs);
}
