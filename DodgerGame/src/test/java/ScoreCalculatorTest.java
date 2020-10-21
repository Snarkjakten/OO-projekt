import Model.ScoreCalculator;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @Author Isak Almeros
 */

public class ScoreCalculatorTest {
    ScoreCalculator scoreCalculator = new ScoreCalculator();

    /**
     * Tests that 0 seconds corresponds to 0 points
     *
     */
    @Test
    public void scoreTest() {
        long time = 0L;
        scoreCalculator.actOnEvent(time,0);
        assertTrue(scoreCalculator.getPoints() == 0);
    }

    /**
     * Tests that 5 seconds corresponds to 5 points
     */
    @Test
    public void scoreTest2() {
        long time = 5000000000L;
        scoreCalculator.actOnEvent(time,0);
        assertTrue(scoreCalculator.getPoints() == 5);
    }

    /**
     * Tests that 10 seconds corresponds to 10 points
     */
    @Test
    public void scoreTest3() {
        long time = 10000000000L;
        scoreCalculator.actOnEvent(time,0);
        assertTrue(scoreCalculator.getPoints() == 10);
    }
}
