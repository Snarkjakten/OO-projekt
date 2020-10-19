package Interfaces;

import Model.Entities.HitBox;

import java.util.List;

public interface IGameObjectObserver {

    /**
     * @author Irja Vuorela
     */
    void actOnEvent(List<HitBox> hitBoxes, Class c, double width, double height);
}