
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
    ArrayList<Integer> topScores = new ArrayList<>();
    ArrayList<Integer> emptyList = new ArrayList<>();
    String fileName = "HighScores.txt";
    int nrOfTopScores = highScoreHandler.getNrOfTopScores();

    /**
     * Creates an empty high scores file for testing.
     *
     * @author Irja Vuorela
     */
    @Before
    public void init() {
        highScoreHandler.createFile(fileName);
        highScoreHandler.writeToFile(emptyList, fileName);
        topScores = emptyList;
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
     * Tests if the list is still sorted after using insertSorted().
     *
     * @author Irja Vuorela
     */
    @Test
    public void insertSortedTest() {
        highScoreHandler.insertSorted(1, topScores);
        highScoreHandler.insertSorted(2, topScores);
        highScoreHandler.insertSorted(0, topScores);
        highScoreHandler.insertSorted(1, topScores);
        highScoreHandler.insertSorted(11, topScores);
        ArrayList<Integer> sortedList = new ArrayList<>();
        sortedList.add(11);
        sortedList.add(2);
        sortedList.add(1);
        sortedList.add(1);
        sortedList.add(0);
        assertTrue(sortedList.equals(topScores));
    }
}
