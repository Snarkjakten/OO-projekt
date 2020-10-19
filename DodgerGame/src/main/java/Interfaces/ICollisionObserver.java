package Interfaces;

import Model.Movement.AbstractGameObject;

public interface ICollisionObserver {

    void actOnCollisionEvent(AbstractGameObject gameObject);
}
