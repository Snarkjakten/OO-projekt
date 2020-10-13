package View;

public interface ITimeObservable {

    void notifyTimeObservers(long timeInSec);

    void addObserver(ITimeObserver ito);

    void removeObserver(ITimeObserver ito);
}
