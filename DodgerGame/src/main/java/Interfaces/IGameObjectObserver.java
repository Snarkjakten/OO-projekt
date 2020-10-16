package Interfaces;

import Game.Entities.Player.HitBox;

import java.util.List;

public interface IGameObjectObserver {

    /**
     * @author Irja Vuorela
     */
    void actOnEvent(List<HitBox> hitBoxes, Class c, double height, double width);
}