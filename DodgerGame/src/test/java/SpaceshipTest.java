import Entities.Spaceship;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SpaceshipTest {

    Spaceship ship;
    Point2D startPos;

    @Before
    // @Author Irja Vuorela
    public void init() {
        ship = new Spaceship();
    }

    @Test
    // Did the ship move to the left?
    // @Author Irja Vuorela
    public void SpaceshipMovedLeft() {
        startPos = ship.position;
        ship.setLeft(1);
        // Negative x value to move to the left
        ship.velocity = new Point2D(-1, 0);
        ship.move();
        assertTrue(ship.position.getX() < startPos.getX());
    }

    @Test
    // Did the ship move to the right?
    // @Author Irja Vuorela
    public void SpaceshipMovedRight() {
        startPos = ship.position;
        ship.setRight(1);
        // Positive x value to move to the right
        ship.velocity = new Point2D(1, 0);
        ship.move();
        assertTrue(ship.position.getX() > startPos.getX());
    }

    @Test
    // Did the ship move up?
    // @Author Irja Vuorela
    public void SpaceshipMovedUp() {
        startPos = ship.position;
        ship.setUp(1);
        // Positive y value to move up
        ship.velocity = new Point2D(0, 1);
        ship.move();
        assertTrue(ship.position.getY() > startPos.getY());
    }

    @Test
    // Did the ship move down?
    // @Author Irja Vuorela
    public void SpaceshipMovedDown() {
        startPos = ship.position;
        ship.setDown(1);
        // Negative y value to move down
        ship.velocity = new Point2D(0, -1);
        ship.move();
        assertTrue(ship.position.getY() < startPos.getY());
    }

    @Test
    // Did the ship stay in place while attempting to move in two opposite directions?
    // @Author Irja Vuorela
    public void ShipStillWhileUpAndDown() {
        startPos = ship.position;
        ship.setUp(1);
        ship.setDown(1);
        ship.velocity = new Point2D(1, 1);
        ship.move();
        assertTrue((ship.position.getX() == startPos.getX()) && (ship.position.getY() == startPos.getY()));
    }

    @Test
    // Did the ship stay in place while attempting to move in two opposite directions?
    // @Author Irja Vuorela
    public void ShipStillWhileLeftAndRight() {
        startPos = ship.position;
        ship.setLeft(1);
        ship.setRight(1);
        ship.velocity = new Point2D(1, 1);
        ship.move();
        assertTrue((ship.position.getX() == startPos.getX()) && (ship.position.getY() == startPos.getY()));
    }
}
