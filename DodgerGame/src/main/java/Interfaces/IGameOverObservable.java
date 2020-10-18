package Interfaces;

public interface IGameOverObservable {

    void notifyGameOverObservers(boolean isGameOver, int points);

    void addObserver(IGameOverObserver obs);

    void removeObserver(IGameOverObserver obs);
}
