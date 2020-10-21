package Interfaces;

public interface ITimeObservable {

    void notifyTimeObservers(long timeInSec, double deltaTime); // DeltaTime is game loop frame duration

    void addTimeObserver(ITimeObserver obs);

    void removeTimeObserver(ITimeObserver obs);
}
