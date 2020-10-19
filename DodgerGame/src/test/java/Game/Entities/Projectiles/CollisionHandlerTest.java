package Game.Entities.Projectiles;

import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.HealthPowerUp;
import Model.Entities.Projectiles.ShieldPowerUp;
import Model.Movement.CollisionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandlerTest {

    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Asteroid projAsteroid;
    Spaceship spaceship;

    CollisionHandler collisionHandler = new CollisionHandler();

    @Before
    public void init() {
        projAsteroid = new Asteroid();
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
        projAsteroid.setCollided(true);
        Assert.assertTrue(projAsteroid.getCollided());
        projAsteroid.setCollided(false);
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
        collisionHandler.collide(projAsteroid, spaceship);
        Assert.assertTrue(projAsteroid.getCollided());
        projAsteroid.setCollided(false);
    }

    @Test
    public void testProjectileCollide() {
        collisionHandler.collide(projAsteroid, hpUp);
        Assert.assertFalse(projAsteroid.getCollided());
    }
}