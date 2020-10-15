package Interfaces;

public interface IGameObjectObserver {

    /**
     * @author Irja Vuorela
     */
    void actOnEvent(double x, double y, Class c, double height, double width);
}