package Interfaces;

import Model.Entities.Player.Spaceship;

public interface ISpaceshipObserver {

    void actOnEvent(Spaceship spaceship);
}
