package Interfaces;

public interface IGameOverObservable {

    void notifyGameOverObservers(boolean isGameOver);

    void addObserver(IGameOverObserver obs);

    void removeObserver(IGameOverObserver obs);
}
