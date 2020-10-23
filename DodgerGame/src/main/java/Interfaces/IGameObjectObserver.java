package Interfaces;

public interface IGameObjectObserver {

    /**
     * Perform actions associated with the observed type.
     *
     * @param className the type of an object
     * @author Irja Vuorela
     */
    void actOnGameObjectEvent(String className, int amount);
}