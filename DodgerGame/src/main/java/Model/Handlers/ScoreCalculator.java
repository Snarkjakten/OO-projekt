package Model.Handlers;

/**
 * @author Isak Almeros
 */

public class ScoreCalculator {
    private int points;

    /**
     * Calculates the score depending on the time
     * @param time the time to be converted to score.
     */
    public void calculateScore(long time) {
        points = (int) (time / 1000000000);
    }

    public int getPoints() {
        return points;
    }

}
