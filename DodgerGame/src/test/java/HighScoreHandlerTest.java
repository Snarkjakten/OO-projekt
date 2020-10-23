
import Model.Handlers.HighScoreHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Irja Vuorela
 */

public class HighScoreHandlerTest {
    HighScoreHandler highScoreHandler = new HighScoreHandler();
    String fileName = highScoreHandler.getFileName();
    int nrOfTopScores = highScoreHandler.getMaxNrOfTopScores();
    List<Integer> emptyList = new ArrayList<>();

    /**
     * Creates an empty high scores file for testing.
     *
     * @author Irja Vuorela
     */
    @Before
    @After
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
     * Tests that the number of scores in the text file is correct when attempting to exceed the defined number of top scores.
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
     * Tests if the list is sorted correctly.
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
        List<Integer> sortedList = new ArrayList<>();
        sortedList.add(11);
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(1);
        sortedList.add(0);
        assertTrue(sortedList.equals(highScoreHandler.getScoresFromFile(fileName)));
    }

    /**
     * Tests if trimScoresList successfully trims the scores list down to the max number of top scores
     *
     * @author Irja Vuorela
     */
    @Test
    public void trimmedScoresFile() {
        int max = highScoreHandler.getMaxNrOfTopScores();
        for (int i = 0; i < (max + 20); i++) {
            highScoreHandler.handleScore(100 * i);
        }
        assertTrue(highScoreHandler.getScoresFromFile(fileName).size() == max);
    }

    /**
     * Tests if getScoresFromList throws an IOException when calling it with a non-filename.
     *
     * @authoer Irja Vuorela
     */
    @Test
    public void getEmptyListWithBadFilename() {
        // ".txt" missing, can't be a file name
        List<Integer> scoresList = highScoreHandler.getScoresFromFile("badFilename");
        assertEquals(scoresList, emptyList);
    }
}
