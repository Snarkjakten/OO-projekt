package Interfaces;

public interface IGameObjectObservable {

    /**
     * @author Irja Vuorela
     */

    void addObserver(IGameObjectObserver obs);

    void removeObserver(IGameObjectObserver obs);

    void notifyGameObjectObservers(double x, double y, Class c, double height, double width);

}
