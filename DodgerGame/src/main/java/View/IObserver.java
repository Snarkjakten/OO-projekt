package View;

public interface IObserver {

    /**
     * @author Irja Vuorela
     */
    void actOnEvent(double x, double y, Class c, double height, double width);
}