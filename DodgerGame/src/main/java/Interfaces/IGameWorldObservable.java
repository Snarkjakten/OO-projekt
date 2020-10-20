package Interfaces;

public interface IGameWorldObservable {

    public void notifyGameWorldObservers();

    public void addGameWorldObserver(IGameWorldObserver obs);

    public void removeGameWorldObserver(IGameWorldObserver obs);
}
