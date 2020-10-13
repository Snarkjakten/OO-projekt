package Interfaces;

public interface ITimeObservable {

    void notifyTimeObservers(long timeInSec);

    void addObserver(ITimeObserver obs);

    void removeObserver(ITimeObserver obs);
}
