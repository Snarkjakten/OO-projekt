package Interfaces;

public interface ITimeObservable {

    void notifyTimeObservers(long timeInSec, double deltaTime); // DeltaTime is frame duration

    void addTimeObserver(ITimeObserver obs);

    void removeObserver(ITimeObserver obs);
}
