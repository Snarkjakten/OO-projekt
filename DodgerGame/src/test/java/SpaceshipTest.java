import Model.Entities.Player.Spaceship;
import Model.Entities.Player.SpaceshipFactory;
import Model.Point2D;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SpaceshipTest {

    Spaceship spaceship;
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * creates a spaceship for testing
     */
    @Before
    public void init() {
        spaceship = SpaceshipFactory.createSpaceship(0, 0, 64, 64);
    }

    /**
     * Tests if the spaceship can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test

    public void spaceshipMovedLeft() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setLeft(1);
        // Negative x value to move to the left
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getXPos() < startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedRight() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setRight(1);
        // Positive x value to move to the right
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getXPos() > startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUp() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setUp(1);
        // Negative y value to move up
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getYPos() < startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDown() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setDown(1);
        // Positive y value to move down
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getYPos() > startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setUp(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() > startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() < startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndLeft() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setUp(1);
        spaceship.setLeft(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() < startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() < startPos.getY()));
    }

    /**
     * /**
     * Tests if the spaceship can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setDown(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() > startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() > startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndLeft() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setDown(1);
        spaceship.setLeft(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() < startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() > startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move left and right simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenLeftAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setLeft(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move up and down simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenUpAndDown() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.setUp(1);
        spaceship.setDown(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenVelocityZero() {
        startPos = spaceship.getHitBoxes().get(0).getPosition();
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getXPos() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getYPos() == startPos.getY()));
    }


    // todo: finish these

    /**
     * @author Tobias Engblom
     */
    @Test
    public void westWrapAround() {

    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void northWrapAround() {

    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void eastWrapAround() {

    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void southWrapAround() {

    }

    /**
     * @authors Viktor & Irja
     */
    @Test
    public void testGainShield() {
        int oldShields = spaceship.getNrOfShields();
        spaceship.gainShield(1);
        int newShields = spaceship.getNrOfShields();
        assertTrue(newShields > oldShields);
    }

    /**
     * Test that the health power up increases the health.
     *
     * @author Olle Westerlund, Irja Vuorela, Viktor Sundberg
     */
    @Test
    public void testGainHealth() {
        spaceship.setHp(spaceship.getMaxHp() / 2);
        int oldHealth = spaceship.getHp();
        spaceship.gainHealth(1);
        int newHealth = spaceship.getHp();
        assertTrue(newHealth > oldHealth);
    }

    /**
     * Checks that player can't heal beyond the set max hp value
     *
     * @authors Irja & Viktor
     */
    @Test
    public void didNotExceedMaxHp() {
        spaceship.setHp(spaceship.getMaxHp() - 1);
        spaceship.gainHealth(spaceship.getMaxHp());
        assertEquals(spaceship.getHp(), spaceship.getMaxHp());
    }
}
