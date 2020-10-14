package Interfaces;

import Game.Movement.AbstractGameObject;

public interface ICollisionObserver {

    void actOnEvent(AbstractGameObject gameObject);
}
