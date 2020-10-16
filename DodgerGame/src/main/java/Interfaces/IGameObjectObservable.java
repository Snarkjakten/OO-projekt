package Interfaces;

import Game.Entities.Player.HitBox;

import java.util.List;

public interface IGameObjectObservable {

    /**
     * @author Irja Vuorela
     */

    void addObserver(IGameObjectObserver obs);

    void removeObserver(IGameObjectObserver obs);

    void notifyGameObjectObservers(List<HitBox> hitBoxes, Class c, double height, double width);

}
