package Interfaces;

import Model.Entities.Player.Spaceship;

public interface ISpaceshipObservable {

    void addSpaceshipObserver(ISpaceshipObserver obs);

    void removeSpaceshipObserver(ISpaceshipObserver obs);

    void notifySpaceshipObservers(Spaceship spaceship);
}
