package Interfaces;

import Game.Entities.Player.Player;
import Game.Entities.Player.Spaceship;

import java.util.List;

public interface IPlayerObservable {

    void addPlayerObserver(IPlayerObserver obs);

    void removePlayerObserver(IPlayerObserver obs);

    void notifyPlayerObservers(Player player);
}
