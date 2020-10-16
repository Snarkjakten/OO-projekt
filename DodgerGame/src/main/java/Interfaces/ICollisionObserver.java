package Interfaces;

import Model.Movement.AbstractGameObject;

public interface ICollisionObserver {

    void actOnEvent(AbstractGameObject gameObject);
}
