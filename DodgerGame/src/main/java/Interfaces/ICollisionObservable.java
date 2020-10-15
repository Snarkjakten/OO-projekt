package Interfaces;

import Game.Movement.AbstractGameObject;

public interface ICollisionObservable {

    void notifyCollisionObservers(AbstractGameObject gameObject);

    void addCollisionObserver(ICollisionObserver obs);

    void removeCollisionObserver(ICollisionObserver obs);
}
