package Interfaces;

public interface ISpaceshipObservable {

    void notifyObserver(String event, int amount);

    void addObserver(IObserve obs);

    void removeObserver(IObserve obs);
}
