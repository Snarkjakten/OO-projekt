import Model.Entities.Projectiles.LaserBeam;
import Model.Point2D;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class LaserBeamTest {

    LaserBeam laserBeam;
    Point2D startPos;
    double deltaTime = 0.016;

    @Before
    //@Author Olle Westerlund
    public void init() {
        laserBeam = new LaserBeam();
    }

    @Test
    // Does move() move the projectile's position to the left?
    // @Author Irja Vuorela
    public void ProjectileMovedLeft() {
        startPos = laserBeam.position;
        // Negative horizontal value to move left
        laserBeam.setStopPosition(-1, 0);
        laserBeam.move(deltaTime);
        TestCase.assertTrue(laserBeam.position.getX() < startPos.getX());
    }

    @Test
    // Does move() move the projectile's position to the right?
    // @Author Irja Vuorela
    public void ProjectileMovedRight() {
        startPos = laserBeam.position;
        // Positive horizontal value to move right
        laserBeam.setStopPosition(1, 0);
        laserBeam.move(deltaTime);
        TestCase.assertTrue(laserBeam.position.getX() > startPos.getX());
    }

    @Test
    // Does move() move the projectile's position up?
    // @Author Irja Vuorela
    public void ProjectileMovedUp() {
        startPos = laserBeam.position;
        // Negative vertical value to move up
        laserBeam.setStopPosition(0, -1);
        laserBeam.move(deltaTime);
        TestCase.assertTrue(laserBeam.position.getY() < startPos.getY());
    }

    @Test
    // Does move() move the projectile's position down?
    // @Author Irja Vuorela
    public void ProjectileMovedDown() {
        startPos = laserBeam.position;
        // Positive vertical value to move down
        laserBeam.setStopPosition(0, 1);
        laserBeam.move(deltaTime);
        TestCase.assertTrue(laserBeam.position.getY() > startPos.getY());
    }

    @Test
    // Does move() move the projectile's position up and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpRight() {
        startPos = laserBeam.position;
        laserBeam.setStopPosition(1, -1);
        laserBeam.move(deltaTime);
        TestCase.assertTrue((laserBeam.position.getX() > startPos.getX()) && (laserBeam.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position up and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpLeft() {
        startPos = laserBeam.position;
        laserBeam.setStopPosition(-1, -1);
        laserBeam.move(deltaTime);
        TestCase.assertTrue((laserBeam.position.getX() < startPos.getX()) && (laserBeam.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position down and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownRight() {
        startPos = laserBeam.position;
        laserBeam.setStopPosition(1, 1);
        laserBeam.move(deltaTime);
        TestCase.assertTrue((laserBeam.position.getX() > startPos.getX()) && (laserBeam.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position down and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownLeft() {
        startPos = laserBeam.position;
        laserBeam.setStopPosition(-1, 1);
        laserBeam.move(deltaTime);
        TestCase.assertTrue((laserBeam.position.getX() < startPos.getX()) && (laserBeam.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() leave the projectile's position unchanged when its velocity was zero?
    // @Author Irja Vuorela
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = laserBeam.position;
        laserBeam.setStopPosition(0, 0);
        laserBeam.move(deltaTime);
        TestCase.assertTrue((laserBeam.position.getX() == startPos.getX()) && (laserBeam.position.getY() == startPos.getY()));
    }

}
