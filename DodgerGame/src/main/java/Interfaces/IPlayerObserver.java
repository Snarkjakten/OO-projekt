package Interfaces;

import Model.Entities.Player.Player;

public interface IPlayerObserver {

    void actOnEvent(Player player);
}
