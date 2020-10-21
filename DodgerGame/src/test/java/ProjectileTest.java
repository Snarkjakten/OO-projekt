import Model.Entities.Projectiles.*;
import Model.Movement.AbstractGameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ProjectileTest {

    Asteroid projAsteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    SlowDebuff slowDebuff;
    ShieldPowerUp shieldPowerUp;
    HealthPowerUp healthPowerUp;
    double x;
    double y;
    List<AbstractGameObject> gameObjects;
    double deltaTime = 0.016;

    /**
     * @author Irja Vuorela and others
     */
    @Before
    public void init() {
        projAsteroid = new Asteroid();
        shieldPU = new ShieldPowerUp();
        slowDebuff = new SlowDebuff();
        shieldPowerUp = new ShieldPowerUp(400, 100, 100, 0, 1);
        hpUp = new HealthPowerUp();
        healthPowerUp = new HealthPowerUp(400, 100, 100, 0, 1);
        gameObjects = new ArrayList<>();
    }

    /**
     * Tests that slowdebuff slows the initial speed
     *
     * @author Isak Ameros
     */
    @Test
    public void testSlowDebuff() {
        double initialSpeed = 200;
        double slowedSpeed = slowDebuff.getSlowSpeedFactor() * initialSpeed;
        assertTrue(slowedSpeed < initialSpeed);
    }

    /**
     * Test that the shield power up gives one shield when pick up.
     *
     * @author Olle Westerlund
     */
    @Test
    public void getHitCapacity() {  // todo: this is redundant?
        int shields = 0;
        int shieldsAfterShieldPU = shields + shieldPU.getHitCapacity();
        assertTrue(shieldsAfterShieldPU > shields);
    }

    /**
     * Test that the asteroid has a speed when spawned.
     *
     * @author Olle Westerlund
     */
    @Test
    public void asteroidSpeed() {
        assertTrue(projAsteroid.getSpeed() > 0);
    }


    /**
     * Test that asteroid does damage
     *
     * @author Olle Westerlund
     */
    @Test
    //@Author Olle Westerlund
    public void asteroidDamage() {
        int startHealth = 200;
        int healthAfterHit = startHealth - projAsteroid.getDamage();
        assertTrue(startHealth > healthAfterHit);
    }

    /**
     * Test if the asteroid is no longer on the map.
     *
     * @author Olle Westerlund
     */
    @Test
    public void asteroidIsNotOnScreen() {
        projAsteroid.getHitBoxes().get(0).updateHitBox(-130, -130, projAsteroid.getWidth(), projAsteroid.getHeight());
        assertTrue(projAsteroid.isNotOnScreen());
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedLeft() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        // Negative horizontal value to move left
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getX() < x);
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedRight() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        // Positive horizontal value to move right
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getX() > x);
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUp() {
        y = projAsteroid.getHitBoxes().get(0).getY();
        // Negative vertical value to move up
        projAsteroid.setYVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getY() < y);
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDown() {
        y = projAsteroid.getHitBoxes().get(0).getY();
        // Positive vertical value to move down
        projAsteroid.setYVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getY() > y);
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUpRight() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        y = projAsteroid.getHitBoxes().get(0).getY();
        projAsteroid.setYVelocity(-1);
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() > x) && (projAsteroid.getHitBoxes().get(0).getY() < y));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUpLeft() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        y = projAsteroid.getHitBoxes().get(0).getY();
        projAsteroid.setYVelocity(-1);
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() < x) && (projAsteroid.getHitBoxes().get(0).getY() < y));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDownRight() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        y = projAsteroid.getHitBoxes().get(0).getY();
        projAsteroid.setYVelocity(1);
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() > x) && (projAsteroid.getHitBoxes().get(0).getY() > y));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDownLeft() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        y = projAsteroid.getHitBoxes().get(0).getY();
        projAsteroid.setYVelocity(1);
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() < x) && (projAsteroid.getHitBoxes().get(0).getY() > y));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileNotMovingWhenVelocityZero() {
        x = projAsteroid.getHitBoxes().get(0).getX();
        y = projAsteroid.getHitBoxes().get(0).getY();
        projAsteroid.setYVelocity(0);
        projAsteroid.setXVelocity(0);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() == x) && (projAsteroid.getHitBoxes().get(0).getY() == y));
    }

    /**
     * Checks if all factory methods can successfully add to the list of game objects
     *
     * @authors Irja & Viktor
     */
    @Test
    public void addedProjectilesWithFactory() {
        int oldListSize = gameObjects.size();
        gameObjects.add(ProjectileFactory.createRandomizedAsteroid());
        gameObjects.add(ProjectileFactory.createAsteroid(1, 1, 1, 1, 1, 1, 1));
        gameObjects.add(ProjectileFactory.createRandomizedHealthPowerUp());
        gameObjects.add(ProjectileFactory.createHealthPowerUp(1, 1, 1, 1, 1));
        gameObjects.add(ProjectileFactory.createRandomizedShieldPowerUp());
        gameObjects.add(ProjectileFactory.createShieldPowerUp(1, 1, 1, 1, 1));
        gameObjects.add(ProjectileFactory.createSlowDebuff());
        int newListSize = gameObjects.size();
        assertEquals(newListSize, oldListSize + 7); // 7 = times added to the list with factory
    }
}
