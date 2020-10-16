package Interfaces;

import Model.Entities.Player.Player;

public interface IPlayerObservable {

    void addPlayerObserver(IPlayerObserver obs);

    void removePlayerObserver(IPlayerObserver obs);

    void notifyPlayerObservers(Player player);
}
