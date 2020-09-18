import Entities.Player.Spaceship;
import Entities.Player.SpaceshipFactory;
import Entities.Player.SpaceshipGUI;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTest {

    Spaceship spaceship;
    Point2D startPos;

    @Before
    // @Author Irja Vuorela
    public void init() {
        spaceship = SpaceshipFactory.createSpaceship();
    }

    @Test
    public void spaceshipAndGUISamePosition() {
        Spaceship spaceship = SpaceshipFactory.createSpaceship();
        SpaceshipGUI spaceshipGUI = new SpaceshipGUI(spaceship, 200, 200);
        System.out.println(spaceship.position.getX());
        System.out.println(spaceshipGUI.getPoint().getX());
        assertTrue(spaceship.position.getX() == spaceshipGUI.getPoint().getX() && spaceship.position.getY() == spaceshipGUI.getPoint().getY());
    }

    @Test
    // Does move() move the ship's position to the left?
    // @Author Irja Vuorela
    public void SpaceshipMovedLeft() {
        startPos = spaceship.position;
        spaceship.setLeft(1);
        // Negative x value to move to the left
        spaceship.velocity = new Point2D(-1, 0);
        spaceship.move();
        assertTrue(spaceship.position.getX() < startPos.getX());
    }

    @Test
    // Does move() move the ship's position to the right?
    // @Author Irja Vuorela
    public void SpaceshipMovedRight() {
        startPos = spaceship.position;
        spaceship.setRight(1);
        // Positive x value to move to the right
        spaceship.velocity = new Point2D(1, 0);
        spaceship.move();
        assertTrue(spaceship.position.getX() > startPos.getX());
    }

    @Test
    // Does move() move the ship's position up?
    // @Author Irja Vuorela
    public void SpaceshipMovedUp() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        // Negative y value to move up
        spaceship.velocity = new Point2D(0, -1);
        spaceship.move();
        assertTrue(spaceship.position.getY() < startPos.getY());
    }

    @Test
    // Does move() move the ship's position down?
    // @Author Irja Vuorela
    public void SpaceshipMovedDown() {
        startPos = spaceship.position;
        spaceship.setDown(1);
        // Positive y value to move down
        spaceship.velocity = new Point2D(0, 1);
        spaceship.move();
        assertTrue(spaceship.position.getY() > startPos.getY());
    }

    @Test
    // Does move() move the ship's position up and right diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedUpAndRight() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        spaceship.setRight(1);
        spaceship.velocity = new Point2D(1, -1);
        spaceship.move();
        assertTrue((spaceship.position.getX() > startPos.getX()) && (spaceship.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the ship's position up and left diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedUpAndLeft() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        spaceship.setLeft(1);
        spaceship.velocity = new Point2D(-1, -1);
        spaceship.move();
        assertTrue((spaceship.position.getX() < startPos.getX()) && (spaceship.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the ship's position down and right diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedDownAndRight() {
        startPos = spaceship.position;
        spaceship.setDown(1);
        spaceship.setRight(1);
        spaceship.velocity = new Point2D(1, 1);
        spaceship.move();
        assertTrue((spaceship.position.getX() > startPos.getX()) && (spaceship.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() move the ship's position down and left diagonally?
    // @Author Irja Vuorela
    public void SpaceshipMovedDownAndLeft() {
        startPos = spaceship.position;
        spaceship.setDown(1);
        spaceship.setLeft(1);
        spaceship.velocity = new Point2D(-1, 1);
        spaceship.move();
        assertTrue((spaceship.position.getX() < startPos.getX()) && (spaceship.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() leave the ship's position unchanged when attempting to move left and right simultaneously?
    // @Author Irja Vuorela
    public void SpaceshipNotMovingWhenLeftAndRight() {
        startPos = spaceship.position;
        spaceship.setLeft(1);
        spaceship.setRight(1);
        spaceship.velocity = new Point2D(1, 1);
        spaceship.move();
        assertTrue((spaceship.position.getX() == startPos.getX()) && (spaceship.position.getY() == startPos.getY()));
    }

    @Test
    // Does move() leave the ship's position unchanged when attempting to move up and down simultaneously?
    // @Author Irja Vuorela
    public void SpaceshipNotMovingWhenUpAndDown() {
        startPos = spaceship.position;
        spaceship.setUp(1);
        spaceship.setDown(1);
        spaceship.velocity = new Point2D(1, 1);
        spaceship.move();
        assertTrue((spaceship.position.getX() == startPos.getX()) && (spaceship.position.getY() == startPos.getY()));
    }

    @Test
    // Does move() leave the ship's position unchanged when its velocity is zero?
    // @Author Irja Vuorela
    public void SpaceshipNotMovingWhenVelocityZero() {
        startPos = spaceship.position;
        spaceship.velocity = new Point2D(0, 0);
        spaceship.move();
        assertTrue((spaceship.position.getX() == startPos.getX()) && (spaceship.position.getY() == startPos.getY()));
    }
}
