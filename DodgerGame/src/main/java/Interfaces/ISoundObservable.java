package Interfaces;

public interface ISoundObservable {

    void removeObserver(ISoundObserve iso);

    void addObserver(ISoundObserve iso);

    void notifySoundObservers(Class c);
}
