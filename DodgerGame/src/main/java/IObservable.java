public interface IObservable {

    /**
     * @author Irja Vuorela
     */

    void addObserver(IObserver o);

    void removeObserver(IObserver o);

    void notifyObservers(double x, double y);

}
