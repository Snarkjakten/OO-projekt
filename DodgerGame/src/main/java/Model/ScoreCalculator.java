package Model;

import Interfaces.ITimeObserver;

/**
 * @Author Isak Almeros
 */

public class ScoreCalculator implements ITimeObserver {
    private int points;

    private void calculateScore(long time) {
        points = (int) (time / 1000000000);
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void actOnEvent(long time, double deltaTime) {
        calculateScore(time);
    }
}
