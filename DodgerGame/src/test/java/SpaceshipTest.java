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
    // Does move() move the ship's position to the left?
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
    // Does move() move the ship's position to the right?
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
    // Does move() move the ship's position up?
    // @Author Irja Vuorela
    public void SpaceshipMovedUp() {
        startPos = ship.position;
        ship.setUp(1);
        // Negative y value to move up
        ship.velocity = new Point2D(0, -1);
        ship.move();
        assertTrue(ship.position.getY() < startPos.getY());
    }

    @Test
    // Does move() move the ship's position down?
    // @Author Irja Vuorela
    public void SpaceshipMovedDown() {
        startPos = ship.position;
        ship.setDown(1);
        // Positive y value to move down
        ship.velocity = new Point2D(0, 1);
        ship.move();
        assertTrue(ship.position.getY() > startPos.getY());
    }

    @Test
    // Does move() move the ship's position up and right diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedUpAndRight() {
        startPos = ship.position;
        ship.setUp(1);
        ship.setRight(1);
        ship.velocity = new Point2D(1, -1);
        ship.move();
        assertTrue((ship.position.getX() > startPos.getX()) && (ship.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the ship's position up and left diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedUpAndLeft() {
        startPos = ship.position;
        ship.setUp(1);
        ship.setLeft(1);
        ship.velocity = new Point2D(-1, -1);
        ship.move();
        assertTrue((ship.position.getX() < startPos.getX()) && (ship.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the ship's position down and right diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedDownAndRight() {
        startPos = ship.position;
        ship.setDown(1);
        ship.setRight(1);
        ship.velocity = new Point2D(1, 1);
        ship.move();
        assertTrue((ship.position.getX() > startPos.getX()) && (ship.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() move the ship's position down and left diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedDownAndLeft() {
        startPos = ship.position;
        ship.setDown(1);
        ship.setLeft(1);
        ship.velocity = new Point2D(-1, 1);
        ship.move();
        assertTrue((ship.position.getX() < startPos.getX()) && (ship.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() leave the ship's position unchanged when attempting to move left and right simultaneously?
    // @Author Irja Vuorela
    public void SpaceshipNotMovingWhenLeftAndRight() {
        startPos = ship.position;
        ship.setLeft(1);
        ship.setRight(1);
        ship.velocity = new Point2D(1, 1);
        ship.move();
        assertTrue((ship.position.getX() == startPos.getX()) && (ship.position.getY() == startPos.getY()));
    }

    @Test
    // Does move() leave the ship's position unchanged when attempting to move up and down simultaneously?
    // @Author Irja Vuorela
    public void SpaceshipNotMovingWhenUpAndDown() {
        startPos = ship.position;
        ship.setUp(1);
        ship.setDown(1);
        ship.velocity = new Point2D(1, 1);
        ship.move();
        assertTrue((ship.position.getX() == startPos.getX()) && (ship.position.getY() == startPos.getY()));
    }

    @Test
    // Does move() leave the ship's position unchanged when its velocity is zero?
    // @Author Irja Vuorela
    public void SpaceshipNotMovingWhenVelocityZero() {
        startPos = ship.position;
        ship.velocity = new Point2D(0, 0);
        ship.move();
        assertTrue((ship.position.getX() == startPos.getX()) && (ship.position.getY() == startPos.getY()));
    }
}
