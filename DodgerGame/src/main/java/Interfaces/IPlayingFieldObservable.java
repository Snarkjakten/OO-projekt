package Interfaces;

public interface IPlayingFieldObservable {

    void notifyPlayingFieldObservers(double width, double height);

    void addObserver(IPlayingFieldObserver obs);

    void removeObserver(IPlayingFieldObserver obs);
}
