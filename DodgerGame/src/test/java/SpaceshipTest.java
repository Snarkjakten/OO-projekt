import Model.Entities.Player.Spaceship;
import Model.Entities.Player.SpaceshipFactory;
import Model.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTest {

    Spaceship spaceship;
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * creates a spaceship for testing
     */
    @Before
    public void init() {
        spaceship = SpaceshipFactory.createSpaceship(0, 0);
    }

    /**
     * Tests if the spaceship can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedLeft() {
        startPos = spaceship.position;
        spaceship.setLeft(1);
        // Negative x value to move to the left
        spaceship.velocity = new Point2D(-1, 0);
        spaceship.move(deltaTime);
        assertTrue(spaceship.position.getX() < startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedRight() {
        startPos = spaceship.position;
        spaceship.setRight(1);
        // Positive x value to move to the right
        spaceship.velocity = new Point2D(1, 0);
        spaceship.move(deltaTime);
        assertTrue(spaceship.position.getX() > startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUp() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        // Negative y value to move up
        spaceship.velocity = new Point2D(0, -1);
        spaceship.move(deltaTime);
        assertTrue(spaceship.position.getY() < startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDown() {
        startPos = spaceship.position;
        spaceship.setDown(1);
        // Positive y value to move down
        spaceship.velocity = new Point2D(0, 1);
        spaceship.move(deltaTime);
        assertTrue(spaceship.position.getY() > startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndRight() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        spaceship.setRight(1);
        spaceship.velocity = new Point2D(1, -1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() > startPos.getX()) && (spaceship.position.getY() < startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndLeft() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        spaceship.setLeft(1);
        spaceship.velocity = new Point2D(-1, -1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() < startPos.getX()) && (spaceship.position.getY() < startPos.getY()));
    }

    /**
     * /**
     * Tests if the spaceship can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndRight() {
        startPos = spaceship.position;
        spaceship.setDown(1);
        spaceship.setRight(1);
        spaceship.velocity = new Point2D(1, 1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() > startPos.getX()) && (spaceship.position.getY() > startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndLeft() {
        startPos = spaceship.position;
        spaceship.setDown(1);
        spaceship.setLeft(1);
        spaceship.velocity = new Point2D(-1, 1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() < startPos.getX()) && (spaceship.position.getY() > startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move left and right simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenLeftAndRight() {
        startPos = spaceship.position;
        spaceship.setLeft(1);
        spaceship.setRight(1);
        spaceship.velocity = new Point2D(1, 1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() == startPos.getX()) && (spaceship.position.getY() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move up and down simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenUpAndDown() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        spaceship.setDown(1);
        spaceship.velocity = new Point2D(1, 1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() == startPos.getX()) && (spaceship.position.getY() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenVelocityZero() {
        startPos = spaceship.position;
        spaceship.velocity = new Point2D(0, 0);
        spaceship.move(deltaTime);
        assertTrue((spaceship.position.getX() == startPos.getX()) && (spaceship.position.getY() == startPos.getY()));
    }


    // todo: finish these
    @Test
    public void westWrapAround() {

    }

    @Test
    public void northWrapAround() {

    }

    @Test
    public void eastWrapAround() {

    }

    @Test
    public void southWrapAround() {

    }
}
