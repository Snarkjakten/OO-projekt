package Model.Entities.Projectiles;

import Model.Entities.Player.Spaceship;
import Model.Movement.CollisionHandler;
import javafx.geometry.Rectangle2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandlerTest {

    Asteroid asteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Spaceship spaceship;

    CollisionHandler collisionHandler = new CollisionHandler();

    @Before
    public void init() {
        asteroid = new Asteroid();
        hpUp = new HealthPowerUp();
        shieldPU = new ShieldPowerUp();
        spaceship = new Spaceship(368, 248, 64, 64);
    }

    /*
    @Test
    public void testSetGetHitbox() {
        asteroid.set(0, 0, 10, 10);
        Assert.assertEquals(asteroid.getHitBoxes().get(0), new Rectangle2D(0, 0, 10 * 0.75, 10 * 0.75));
    }

     */

    @Test
    public void testSetCollided() {
        asteroid.setCollided(true);
        Assert.assertTrue(asteroid.getCollided());
        asteroid.setCollided(false);
    }

    /*
    @Test
    public void testCheckCollisions() {
        spaceship.setHitbox(10, 10, 10, 10);
        asteroid.setHitbox(10, 10, 10, 10);
        Assert.assertTrue(collisionHandler.checkCollision(asteroid, spaceship));
    }

     */

    /*
    @Test
    public void doesNotCollide() {
        spaceship.setHitbox(200, 200, 10, 10);
        asteroid.setHitbox(10, 10, 10, 10);
        Assert.assertFalse(collisionHandler.checkCollision(asteroid, spaceship));
    }

     */

    @Test
    public void testSpaceshipCollide() {
        collisionHandler.collide(asteroid, spaceship);
        Assert.assertTrue(asteroid.getCollided());
        asteroid.setCollided(false);
    }

    @Test
    public void testProjectileCollide() {
        collisionHandler.collide(asteroid, hpUp);
        Assert.assertFalse(asteroid.getCollided());
    }
}