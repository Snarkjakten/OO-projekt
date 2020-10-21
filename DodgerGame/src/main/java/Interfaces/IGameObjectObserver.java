package Interfaces;

import Model.Entities.HitBox;
import Model.Movement.AbstractGameObject;

import java.util.List;

public interface IGameObjectObserver {

    /**
     * Perform actions associated with the observed type.
     *
     * @param c the type of an object
     * @author Irja Vuorela
     */
    void actOnGameObjectEvent(Class c);
}