import Model.Entities.Projectiles.LaserBeam;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LaserBeamTest {

    LaserBeam laserBeam;
    double x;
    double y;
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
        x = laserBeam.getHitBoxes().get(0).getX();
        // Negative horizontal value to move left
        laserBeam.targetDirection(-1, 0);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getX() < x);
    }

    @Test
    // Does move() move the projectile's position to the right?
    // @Author Irja Vuorela
    public void ProjectileMovedRight() {
        x = laserBeam.getHitBoxes().get(0).getX();
        // Positive horizontal value to move right
        laserBeam.targetDirection(1, 0);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getX() > x);
    }

    @Test
    // Does move() move the projectile's position up?
    // @Author Irja Vuorela
    public void ProjectileMovedUp() {
        y = laserBeam.getHitBoxes().get(0).getY();
        // Negative vertical value to move up
        laserBeam.targetDirection(0, -1);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getY() < y);
    }

    @Test
    // Does move() move the projectile's position down?
    // @Author Irja Vuorela
    public void ProjectileMovedDown() {
        y = laserBeam.getHitBoxes().get(0).getY();
        // Positive vertical value to move down
        laserBeam.targetDirection(0, 1);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getY() > y);
    }

    @Test
    // Does move() move the projectile's position up and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpRight() {
        x = laserBeam.getHitBoxes().get(0).getX();
        y = laserBeam.getHitBoxes().get(0).getY();
        laserBeam.targetDirection(1, -1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() > x) && (laserBeam.getHitBoxes().get(0).getY() < y));
    }

    @Test
    // Does move() move the projectile's position up and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpLeft() {
        x = laserBeam.getHitBoxes().get(0).getX();
        y = laserBeam.getHitBoxes().get(0).getY();
        laserBeam.targetDirection(-1, -1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() < x) && (laserBeam.getHitBoxes().get(0).getY() < y));
    }

    @Test
    // Does move() move the projectile's position down and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownRight() {
        x = laserBeam.getHitBoxes().get(0).getX();
        y = laserBeam.getHitBoxes().get(0).getY();
        laserBeam.targetDirection(1, 1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() > x) && (laserBeam.getHitBoxes().get(0).getY() > y));
    }

    @Test
    // Does move() move the projectile's position down and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownLeft() {
        x = laserBeam.getHitBoxes().get(0).getX();
        y = laserBeam.getHitBoxes().get(0).getY();
        laserBeam.targetDirection(-1, 1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() < x) && (laserBeam.getHitBoxes().get(0).getY() > y));
    }

    @Test
    // Does move() leave the projectile's position unchanged when its velocity was zero?
    // @Author Irja Vuorela
    public void ProjectileNotMovingWhenVelocityZero() {
        x = laserBeam.getHitBoxes().get(0).getX();
        y = laserBeam.getHitBoxes().get(0).getY();
        laserBeam.targetDirection(0, 0);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() == x) && (laserBeam.getHitBoxes().get(0).getY() == y));
    }
}
