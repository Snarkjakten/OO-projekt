import Entities.Projectile;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ProjectileTest {

    Projectile proj;
    Point2D startPos;

    @Before
    // @Author Irja
    public void init() {
        proj = new Projectile();
    }

    @Test
    // Did the proj move to the left?
    // @Author Irja Vuorela
    public void ProjectileMovedLeft() {
        startPos = proj.position;
        // Negative horizontal value to move left
        proj.setHorizontal(-1);
        proj.move();
        assertTrue(proj.position.getX() < startPos.getX());
    }

    @Test
    // Did the proj move to the right?
    // @Author Irja Vuorela
    public void ProjectileMovedRight() {
        startPos = proj.position;
        // Positive horizontal value to move right
        proj.setHorizontal(1);
        proj.move();
        assertTrue(proj.position.getX() > startPos.getX());
    }

    @Test
    // Did the proj move up?
    // @Author Irja Vuorela
    public void ProjectileMovedUp() {
        startPos = proj.position;
        // Positive vertical value to move up
        proj.setVertical(1);
        proj.move();
        assertTrue(proj.position.getY() > startPos.getY());
    }

    @Test
    // Did the proj move down?
    // @Author Irja Vuorela
    public void ProjectileMovedDown() {
        startPos = proj.position;
        // Negative vertical value to move down
        proj.setVertical(-1);
        proj.move();
        assertTrue(proj.position.getY() < startPos.getY());
    }

    @Test
    // Did the projectile move diagonally up and right?
    // @Author Irja Vuorela
    public void ProjectileMovedUpRight() {
        startPos = proj.position;
        proj.setVertical(1);
        proj.setHorizontal(1);
        proj.move();
        assertTrue((proj.position.getX() > startPos.getX()) && (proj.position.getY() > startPos.getY()));
    }

    @Test
    // Did the projectile move diagonally up and left?
    // @Author Irja Vuorela
    public void ProjectileMovedUpLeft() {
        startPos = proj.position;
        proj.setVertical(1);
        proj.setHorizontal(-1);
        proj.move();
        assertTrue((proj.position.getX() < startPos.getX()) && (proj.position.getY() > startPos.getY()));
    }

    @Test
    // Did the projectile move diagonally down and right?
    // @Author Irja Vuorela
    public void ProjectileMovedDownRight() {
        startPos = proj.position;
        proj.setVertical(-1);
        proj.setHorizontal(1);
        proj.move();
        assertTrue((proj.position.getX() > startPos.getX()) && (proj.position.getY() < startPos.getY()));
    }

    @Test
    // Did the projectile move diagonally down and left?
    // @Author Irja Vuorela
    public void ProjectileMovedDownLeft() {
        startPos = proj.position;
        proj.setVertical(-1);
        proj.setHorizontal(-1);
        proj.move();
        assertTrue((proj.position.getX() < startPos.getX()) && (proj.position.getY() < startPos.getY()));
    }

    @Test
    // Did the projectile stay in place when its velocity was (0,0)?
    // @Author Irja Vuorela
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = proj.position;
               proj.setVertical(0);
        proj.setHorizontal(0);
        proj.move();
        assertTrue((proj.position.getX() == startPos.getX()) && (proj.position.getY() == startPos.getY()));
    }

}
