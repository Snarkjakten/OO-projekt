
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * @author Irja Vuorela
 */

public class HighScoreHandlerTest {
    HighScoreHandler highScoreHandler = new HighScoreHandler();
    String fileName = "HighScores.txt";
    int nrOfTopScores = highScoreHandler.getNrOfTopScores();

    /**
     * Creates an empty high scores file for testing.
     *
     * @author Irja Vuorela
     */
    @Before
    public void init() {
        highScoreHandler.clearScores(fileName);
    }

    /**
     * Tests if adding a score works.
     *
     * @author Irja Vuorela
     */
    @Test
    public void scoreAdded() {
        highScoreHandler.handleScore(20);
        assertTrue(highScoreHandler.getScoresFromFile(fileName).get(0) == 20);
    }

    /**
     * Tests that the number of scores in the text file doesn't exceed the defined number of top scores.
     *
     * @author Irja Vuorela
     */
    @Test
    public void correctNrOfTopScores() {
        for (int i = 0; i <= nrOfTopScores; i++) {
            highScoreHandler.handleScore(1);
        }
        assertTrue(nrOfTopScores == highScoreHandler.getScoresFromFile(fileName).size());
    }

    /**
     * Tests if the list is still sorted correctly.
     *
     * @author Irja Vuorela
     */
    @Test
    public void isSorted() {
        highScoreHandler.handleScore(1);
        highScoreHandler.handleScore(2);
        highScoreHandler.handleScore(0);
        highScoreHandler.handleScore(1);
        highScoreHandler.handleScore(11);
        ArrayList<Integer> sortedList = new ArrayList<>();
        sortedList.add(11);
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(1);
        sortedList.add(0);
        assertTrue(sortedList.equals(highScoreHandler.getScoresFromFile(fileName)));
    }
}
