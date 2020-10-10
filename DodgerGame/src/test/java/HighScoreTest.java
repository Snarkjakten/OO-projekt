
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * @author Irja Vuorela
 */

public class HighScoreTest {
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
        int score = 1;
        highScoreHandler.handleScore(20);
        assertTrue(highScoreHandler.getScoresFromFile(fileName).get(0) == 20);
    }

    /**
     * Tests if the scores text file doesn't exceed the defined number of top scores.
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
        highScoreHandler.addToTopScores(1, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(20, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(78, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(31, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(0, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(1, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(46, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(50, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(3, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(785, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(378, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(278, topScores, nrOfTopScores);
        highScoreHandler.addToTopScores(178, topScores, nrOfTopScores);
        ArrayList<Integer> sortedList = new ArrayList<>();
        sortedList.add(785);
        sortedList.add(378);
        sortedList.add(278);
        sortedList.add(178);
        sortedList.add(78);
        sortedList.add(50);
        sortedList.add(46);
        sortedList.add(31);
        sortedList.add(20);
        sortedList.add(3);
        System.out.println("sortedlist: " + sortedList);
        System.out.println("topscores: " + topScores);
        assertTrue(sortedList.equals(topScores));
    }
}
