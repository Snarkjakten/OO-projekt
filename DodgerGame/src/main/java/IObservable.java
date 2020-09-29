import View.IObserver;

public interface IObservable {

    /**
     * @author Irja Vuorela
     */

    void addObserver(IObserver obs);

    void removeObserver(IObserver obs);

    void notifyObservers(double x, double y, Class c, double height, double width);

}