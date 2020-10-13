package View;

public interface IGameOverObservable {

    void notifyGameOverObservers(boolean isGameOver);

    void addObserver(IGameOverObserver igo);

    void removeObserver(IGameOverObserver igo);
}
