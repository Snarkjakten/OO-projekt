package View.Sound;

public interface ISoundObservable {

    void removeSoundObserver(ISoundObserve iso);

    void addSoundObserver(ISoundObserve iso);

    void notifySoundObservers(Class c);
}
