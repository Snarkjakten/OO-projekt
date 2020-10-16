package Game.Entities.Projectiles;

import Game.Entities.Player.Spaceship;
import Game.Movement.CollisionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandlerTest {

    SmallAsteroid projSmallAsteroid;
    MediumAsteroid projMediumAsteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Spaceship spaceship;

    CollisionHandler collisionHandler = new CollisionHandler();

    @Before
    public void init() {
        projSmallAsteroid = new SmallAsteroid();
        projMediumAsteroid = new MediumAsteroid();
        hpUp = new HealthPowerUp();
        shieldPU = new ShieldPowerUp();
        spaceship = new Spaceship(368, 248, 64, 64);
    }

    /*
    @Test
    public void testSetGetHitbox() {
        projSmallAsteroid.setHitbox(0, 0, 10, 10);
        Assert.assertEquals(projSmallAsteroid.getHitbox(), new Rectangle2D(0, 0, 10 * 0.75, 10 * 0.75));
    }

     */

    @Test
    public void testSetCollided() {
        projSmallAsteroid.setCollided(true);
        Assert.assertTrue(projSmallAsteroid.getCollided());
        projSmallAsteroid.setCollided(false);
    }

    /*
    @Test
    public void testCheckCollisions() {
        spaceship.setHitbox(10, 10, 10, 10);
        projSmallAsteroid.setHitbox(10, 10, 10, 10);
        Assert.assertTrue(collisionHandler.checkCollision(projSmallAsteroid, spaceship));
    }
     */
    /*
    @Test
    public void doesNotCollide() {
        spaceship.setHitbox(200, 200, 10, 10);
        projSmallAsteroid.setHitbox(10, 10, 10, 10);
        Assert.assertFalse(collisionHandler.checkCollision(projSmallAsteroid, spaceship));
    }

     */

    @Test
    public void testSpaceshipCollide() {
        collisionHandler.collide(projMediumAsteroid, spaceship);
        Assert.assertTrue(projMediumAsteroid.getCollided());
        projMediumAsteroid.setCollided(false);
    }

    @Test
    public void testProjectileCollide() {
        collisionHandler.collide(projMediumAsteroid, hpUp);
        Assert.assertFalse(projMediumAsteroid.getCollided());
    }
}