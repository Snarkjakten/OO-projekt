package Interfaces;

public interface IGameObjectObservable {

    /**
     * @author Irja Vuorela
     */

    void addGameObjectObserver(IGameObjectObserver obs);

    void removeGameObjectObserver(IGameObjectObserver obs);

    void notifyGameObjectObservers(Class c);

}
