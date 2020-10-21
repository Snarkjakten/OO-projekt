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

    @Test
    public void hitBoxesCollided() {
        spaceship.getHitBoxes().get(0).setHitBox(10, 10, 10, 10);
        asteroid.getHitBoxes().get(0).setHitBox(10, 10, 10, 10);
        Assert.assertTrue(collisionHandler.checkCollision(asteroid, spaceship));
    }

    @Test
    public void doesNotCollide() {
        spaceship.getHitBoxes().get(0).setHitBox(200, 200, 10, 10);
        asteroid.getHitBoxes().get(0).setHitBox(10, 10, 10, 10);
        Assert.assertFalse(collisionHandler.checkCollision(asteroid, spaceship));
    }

    @Test
    public void spaceshipCollided() {
        collisionHandler.collide(asteroid, spaceship);
        Assert.assertTrue(asteroid.getCollided());
        asteroid.setCollided(false);
    }

    @Test
    public void projectileCollided() {
        collisionHandler.collide(asteroid, hpUp);
        Assert.assertFalse(asteroid.getCollided());
    }
}