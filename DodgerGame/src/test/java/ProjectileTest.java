import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.HealthPowerUp;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.Entities.Projectiles.ShieldPowerUp;
import Model.Movement.AbstractGameObject;
import Model.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ProjectileTest {

    Asteroid projAsteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    ShieldPowerUp shieldPowerUp;
    HealthPowerUp hpUp;
    HealthPowerUp healthPowerUp;
    Point2D startPos;
    List<AbstractGameObject> gameObjects;
    double deltaTime = 0.016;

    /**
     * @author Irja Vuorela and others
     */
    @Before
    public void init() {
        projAsteroid = new Asteroid();
        shieldPU = new ShieldPowerUp();
        shieldPowerUp = new ShieldPowerUp(400, 100, 100, 0, 1);
        hpUp = new HealthPowerUp();
        healthPowerUp = new HealthPowerUp(400, 100, 100, 0, 1);
        gameObjects = new ArrayList<>();
    }

    /**
     * Test that the shield power up gives one shield when pick up.
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
     * @author Olle Westerlund
     */
    @Test
    public void asteroidSpeed() {
        assertTrue(projAsteroid.getSpeed() > 0 );
    }


    /**
     * Test that asteroid does damage
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
     * @author Olle Westerlund
     */
    @Test
    public void testAsteroidIsNotOnScreen() {
        projAsteroid.setPosition(-130, -130);
        assertTrue(projAsteroid.isNotOnScreen());
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedLeft() {
        startPos = projAsteroid.position;
        // Negative horizontal value to move left
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getX() < startPos.getX());
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedRight() {
        startPos = projAsteroid.position;
        // Positive horizontal value to move right
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getX() > startPos.getX());
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUp() {
        startPos = projAsteroid.position;
        // Negative vertical value to move up
        projAsteroid.setYVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getY() < startPos.getY());
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDown() {
        startPos = projAsteroid.position;
        // Positive vertical value to move down
        projAsteroid.setYVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getY() > startPos.getY());
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUpRight() {
        startPos = projAsteroid.position;
        projAsteroid.setYVelocity(-1);
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() > startPos.getX()) && (projAsteroid.position.getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUpLeft() {
        startPos = projAsteroid.position;
        projAsteroid.setYVelocity(-1);
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() < startPos.getX()) && (projAsteroid.position.getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDownRight() {
        startPos = projAsteroid.position;
        projAsteroid.setYVelocity(1);
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() > startPos.getX()) && (projAsteroid.position.getY() > startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDownLeft() {
        startPos = projAsteroid.position;
        projAsteroid.setYVelocity(1);
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() < startPos.getX()) && (projAsteroid.position.getY() > startPos.getY()));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileNotMovingWhenVelocityZero() {
        startPos = projAsteroid.position;
        projAsteroid.setYVelocity(0);
        projAsteroid.setXVelocity(0);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() == startPos.getX()) && (projAsteroid.position.getY() == startPos.getY()));
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
        assertTrue(newListSize == oldListSize + 7); // 7 = times added to the list with factory
    }
}
