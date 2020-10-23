import Model.Entities.Player.Spaceship;
import Model.Entities.Point2D;
import Model.Entities.Projectiles.LaserBeam;
import Model.PlayingField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LaserBeamTest {

    LaserBeam laserBeam;
    Spaceship spaceship = new Spaceship(0, 0, 64, 64);
    LaserBeam verticalLaserBeam;
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * @Author Olle Westerlund
     */
    @Before
    public void init() {
        laserBeam = new LaserBeam(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
        verticalLaserBeam = new LaserBeam(1, PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
    }

    /**
     * Test so that the laser beam don't get set to collided = true, since
     * it should not be removed when colliding with other objects.
     * @author Olle Westerlund
     */
    @Test
    public void laserBeamCollision() {
        laserBeam.actOnCollision(spaceship.toString(), spaceship.getAmount());
        assertTrue(laserBeam.getCollided() == false);
    }

    /**
     * Test that spawning on the left side creates a vertical laser beam.
     * @author Olle Westerlund
     */
    @Test
    public void laserBeamIsVertical() {
        assertTrue(verticalLaserBeam.isVertical());
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedLeft() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        // Negative horizontal value to move left
        laserBeam.setVelocity(-1, 0);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getX() < startPos.getX());
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedRight() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        // Positive horizontal value to move right
        laserBeam.setVelocity(1, 0);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getX() > startPos.getX());
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUp() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        // Negative vertical value to move up
        laserBeam.setVelocity(0, -1);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getY() < startPos.getY());
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDown() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        // Positive vertical value to move down
        laserBeam.setVelocity(0, 1);
        laserBeam.move(deltaTime);
        assertTrue(laserBeam.getHitBoxes().get(0).getY() > startPos.getY());
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpRight() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        laserBeam.setVelocity(1, -1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() > startPos.getX()) && (laserBeam.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpLeft() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        laserBeam.setVelocity(-1, -1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() < startPos.getX()) && (laserBeam.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownRight() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        laserBeam.setVelocity(1, 1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() > startPos.getX()) && (laserBeam.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownLeft() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        laserBeam.setVelocity(-1, 1);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() < startPos.getX()) && (laserBeam.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = laserBeam.getHitBoxes().get(0).getHitBox().getPosition();
        laserBeam.setVelocity(0, 0);
        laserBeam.move(deltaTime);
        assertTrue((laserBeam.getHitBoxes().get(0).getX() == startPos.getX()) && (laserBeam.getHitBoxes().get(0).getY() == startPos.getY()));
    }
}
