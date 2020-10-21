import Model.Entities.Projectiles.LaserBeam;
import Model.Point2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LaserBeamTest {

    LaserBeam laserBeam;
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * @Author Olle Westerlund
     */
    @Before
    public void init() {
        laserBeam = new LaserBeam();
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedLeft() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        // Negative horizontal value to move left
        laserBeam.setVelocity(-1, 0);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getXPos() < startPos.getX());
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedRight() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        // Positive horizontal value to move right
        laserBeam.setVelocity(1, 0);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getXPos() > startPos.getX());
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUp() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        // Negative vertical value to move up
        laserBeam.setVelocity(0, -1);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getYPos() < startPos.getY());
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDown() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        // Positive vertical value to move down
        laserBeam.setVelocity(0, 1);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getYPos() > startPos.getY());
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpRight() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        laserBeam.setVelocity(1, -1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getXPos() > startPos.getX()) && (laserBeam.getHitBoxes().get(0).getYPos() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpLeft() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        laserBeam.setVelocity(-1, -1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getXPos() < startPos.getX()) && (laserBeam.getHitBoxes().get(0).getYPos() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownRight() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        laserBeam.setVelocity(1, 1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getXPos() > startPos.getX()) && (laserBeam.getHitBoxes().get(0).getYPos() > startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownLeft() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        laserBeam.setVelocity(-1, 1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getXPos() < startPos.getX()) && (laserBeam.getHitBoxes().get(0).getYPos() > startPos.getY()));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = laserBeam.getHitBoxes().get(0).getPosition();
        laserBeam.setVelocity(0, 0);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getXPos() == startPos.getX()) && (laserBeam.getHitBoxes().get(0).getYPos() == startPos.getY()));
    }
}
