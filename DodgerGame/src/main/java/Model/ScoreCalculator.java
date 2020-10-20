package Model;

import Interfaces.ITimeObserver;
import Model.Entities.Player.Player;

/**
 * @Author Isak Almeros
 */

public class ScoreCalculator implements ITimeObserver {
    Player player;

    public ScoreCalculator(Player player) {
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void calculateScore(long time) {
        int points = (int) (time / 1000000000);
        player.setPoints(points);
    }

    @Override
    public void actOnEvent(long time, double deltaTime) {
        calculateScore(time);
    }
}
