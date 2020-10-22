package Interfaces;

public interface ITimeObservable {

    /**
     * @param time      an amount of time
     * @param deltaTime a difference in time
     */
    void notifyTimeObservers(long time, double deltaTime);

    /**
     * @param obs an observer to be added to a list of observers
     */
    void addTimeObserver(ITimeObserver obs);

    /**
     * @param obs an observer to be removed from a list of observers
     */
    void removeTimeObserver(ITimeObserver obs);
}
