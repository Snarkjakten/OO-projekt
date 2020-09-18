import Entities.Projectile;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ProjectileTest {

    Projectile proj;
    Point2D startPos;

    @Before
    // @Author Irja Vuorela
    public void init() {
        proj = new Projectile();
    }

    @Test
    // Does move() move the projectile's position to the left?
    // @Author Irja Vuorela
    public void ProjectileMovedLeft() {
        startPos = proj.position;
        // Negative horizontal value to move left
        proj.setHorizontal(-1);
        proj.move();
        assertTrue(proj.position.getX() < startPos.getX());
    }

    @Test
    // Does move() move the projectile's position to the right?
    // @Author Irja Vuorela
    public void ProjectileMovedRight() {
        startPos = proj.position;
        // Positive horizontal value to move right
        proj.setHorizontal(1);
        proj.move();
        assertTrue(proj.position.getX() > startPos.getX());
    }

    @Test
    // Does move() move the projectile's position up?
    // @Author Irja Vuorela
    public void ProjectileMovedUp() {
        startPos = proj.position;
        // Negative vertical value to move up
        proj.setVertical(-1);
        proj.move();
        assertTrue(proj.position.getY() < startPos.getY());
    }

    @Test
    // Does move() move the projectile's position down?
    // @Author Irja Vuorela
    public void ProjectileMovedDown() {
        startPos = proj.position;
        // Positive vertical value to move down
        proj.setVertical(1);
        proj.move();
        assertTrue(proj.position.getY() > startPos.getY());
    }

    @Test
    // Does move() move the projectile's position up and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpRight() {
        startPos = proj.position;
        proj.setVertical(-1);
        proj.setHorizontal(1);
        proj.move();
        assertTrue((proj.position.getX() > startPos.getX()) && (proj.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position up and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpLeft() {
        startPos = proj.position;
        proj.setVertical(-1);
        proj.setHorizontal(-1);
        proj.move();
        assertTrue((proj.position.getX() < startPos.getX()) && (proj.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position down and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownRight() {
        startPos = proj.position;
        proj.setVertical(1);
        proj.setHorizontal(1);
        proj.move();
        assertTrue((proj.position.getX() > startPos.getX()) && (proj.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position down and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownLeft() {
        startPos = proj.position;
        proj.setVertical(1);
        proj.setHorizontal(-1);
        proj.move();
        assertTrue((proj.position.getX() < startPos.getX()) && (proj.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() leave the projectile's position unchanged when its velocity was zero?
    // @Author Irja Vuorela
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = proj.position;
        proj.setVertical(0);
        proj.setHorizontal(0);
        proj.move();
        assertTrue((proj.position.getX() == startPos.getX()) && (proj.position.getY() == startPos.getY()));
    }
}
