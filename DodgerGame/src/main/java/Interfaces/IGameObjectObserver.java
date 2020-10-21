package Interfaces;

import Model.Entities.HitBox;
import Model.Movement.AbstractGameObject;

import java.util.List;

public interface IGameObjectObserver {

    /**
     * @author Irja Vuorela
     */
    void actOnGameObjectEvent(Class c);
}