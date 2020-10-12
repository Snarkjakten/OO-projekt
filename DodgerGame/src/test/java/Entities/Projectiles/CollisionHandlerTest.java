package Entities.Projectiles;

import Entities.Player.Spaceship;
import javafx.geometry.Rectangle2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandlerTest {

    SmallAsteroid projSmallAsteroid;
    MediumAsteroid projMediumAsteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Spaceship spaceship;

    CollisionHandler collisionHandler = new CollisionHandler();

    @Before
    public void init(){
        projSmallAsteroid = new SmallAsteroid();
        projMediumAsteroid = new MediumAsteroid();
        hpUp = new HealthPowerUp();
        shieldPU = new ShieldPowerUp();
        spaceship = new Spaceship(368, 248);
    }

    @Test
    public void testSetHitbox() {
        projSmallAsteroid.setHitbox(0,0,10,10);
        Assert.assertEquals(projSmallAsteroid.getHitbox(), new Rectangle2D(0, 0, 10 * 0.75, 10 * 0.75));
    }

    @Test
    public void testCollided() {
        projSmallAsteroid.setCollided(true);
        Assert.assertTrue(projSmallAsteroid.getCollided());
    }

    @Test
    public void testCheckCollisions() {
        spaceship.setHitbox(10,10,10,10);
        projSmallAsteroid.setHitbox(10,10,10,10);
        Assert.assertTrue(collisionHandler.checkCollision(projSmallAsteroid, spaceship));
    }

    @Test
    public void doesNotIntersect() {
        spaceship.setHitbox(200,200,10,10);
        projSmallAsteroid.setHitbox(10,10,10,10);
        Assert.assertFalse(collisionHandler.checkCollision(projSmallAsteroid, spaceship));
    }
}