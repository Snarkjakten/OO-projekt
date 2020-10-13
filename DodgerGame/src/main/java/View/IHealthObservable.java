package View;

public interface IHealthObservable {

    void notifyHealthObservers(int health);

    void addObserver(IHealthObserver iho);

    void removeObserver(IHealthObserver iho);

}
