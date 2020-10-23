package Interfaces;

public interface IGameObjectObservable {

    /**
     * @author Irja Vuorela
     */

    /**
     * @param obs an observer to be added to a list of observers
     */
    void addGameObjectObserver(IGameObjectObserver obs);

    /**
     * @param obs an observer to be removed from a list of observers
     */
    void removeGameObjectObserver(IGameObjectObserver obs);

    /**
     * @param className the type of object which is observed
     */
    void notifyGameObjectObservers(String className, int amount);

}
