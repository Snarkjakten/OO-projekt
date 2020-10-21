package Model;

import Interfaces.ITimeObserver;

/**
 * @Author Isak Almeros
 */

public class ScoreCalculator {
    private int points;

    public void calculateScore(long time) {
        points = (int) (time / 1000000000);
    }

    public int getPoints() {
        return points;
    }

}
