package Interfaces;

public interface IGameOverObservable {

    void notifyGameOverObservers(boolean isGameOver, int points);

    void addGameOverObserver(IGameOverObserver obs);

    void removeGameOverObserver(IGameOverObserver obs);
}
