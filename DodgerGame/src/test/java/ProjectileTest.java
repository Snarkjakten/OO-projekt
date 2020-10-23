import Model.Entities.AbstractGameObject;
import Model.Entities.Player.Spaceship;
import Model.Entities.Point2D;
import Model.Entities.Projectiles.*;
import Model.CollisionHandler;
import Model.PlayingField;
import Model.ProjectileFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ProjectileTest {

    CollisionHandler collisionHandler;
    Asteroid projAsteroid;
    Spaceship spaceship;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    SlowDebuff slowDebuff;
    ShieldPowerUp shieldPowerUp;
    HealthPowerUp healthPowerUp;
    Point2D startPos;
    List<AbstractGameObject> gameObjects;
    double deltaTime = 0.016;

    /**
     * @author Irja Vuorela and others
     */
    @Before
    public void init() {
        collisionHandler = new CollisionHandler();
        projAsteroid = new Asteroid(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
        spaceship = new Spaceship(0,0,20,20);
        shieldPU = new ShieldPowerUp(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
        slowDebuff = new SlowDebuff();
        shieldPowerUp = new ShieldPowerUp(400, 100, 100, 0, 1);
        hpUp = new HealthPowerUp();
        healthPowerUp = new HealthPowerUp(400, 100, 100, 0, 1);
        gameObjects = new ArrayList<>();
    }

    /**
     * Tests that slowdebuff slows the initial speed
     *
     * @author Everyone
     */
    @Test
    public void testSlowDebuff() {
        double initialSpeed = spaceship.getSpeed();
        slowDebuff.getHitBoxes().get(0).updateHitBox(0,0,20,20);
        gameObjects.add(slowDebuff);
        gameObjects.add(spaceship);
        collisionHandler.handleCollision(gameObjects);
        double slowedSpeed = spaceship.getSpeed();
        assertTrue(slowedSpeed < initialSpeed);
    }

    /**
     * Test that the shield power up gives one shield when pick up.
     *
     * @author Everyone
     */
    @Test
    public void getHitCapacity() {
        int shields = spaceship.getNrOfShields();
        shieldPowerUp.getHitBoxes().get(0).updateHitBox(0,0,20,20);
        gameObjects.add(shieldPowerUp);
        gameObjects.add(spaceship);
        collisionHandler.handleCollision(gameObjects);
        int shieldsAfterShieldPU = spaceship.getNrOfShields();
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
     * @author Everyone
     */
    @Test
    public void asteroidDamage() {
        int startHealth = spaceship.getHp();
        projAsteroid.getHitBoxes().get(0).updateHitBox(0,0,20,20);
        gameObjects.add(projAsteroid);
        gameObjects.add(spaceship);
        collisionHandler.handleCollision(gameObjects);
        int healthAfterHit = spaceship.getHp();
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
        assertTrue(projAsteroid.isNotOnPlayingField(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight()));
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedLeft() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        // Negative horizontal value to move left
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getX() < startPos.getX());
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedRight() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        // Positive horizontal value to move right
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getX() > startPos.getX());
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUp() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        // Negative vertical value to move up
        projAsteroid.setYVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getY() < startPos.getY());
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDown() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        // Positive vertical value to move down
        projAsteroid.setYVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.getHitBoxes().get(0).getY() > startPos.getY());
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUpRight() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        projAsteroid.setYVelocity(-1);
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() > startPos.getX()) && (projAsteroid.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedUpLeft() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        projAsteroid.setYVelocity(-1);
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() < startPos.getX()) && (projAsteroid.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDownRight() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        projAsteroid.setYVelocity(1);
        projAsteroid.setXVelocity(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() > startPos.getX()) && (projAsteroid.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileMovedDownLeft() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        projAsteroid.setYVelocity(1);
        projAsteroid.setXVelocity(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() < startPos.getX()) && (projAsteroid.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void projectileNotMovingWhenVelocityZero() {
        startPos = projAsteroid.getHitBoxes().get(0).getHitBox().getPosition();
        projAsteroid.setYVelocity(0);
        projAsteroid.setXVelocity(0);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.getHitBoxes().get(0).getX() == startPos.getX()) && (projAsteroid.getHitBoxes().get(0).getY() == startPos.getY()));
    }

    /**
     * Checks if all factory methods can successfully create objects to add to the list of game objects
     *
     * @authors Irja & Viktor
     */
    @Test
    public void addedProjectilesWithFactory() {
        int oldListSize = gameObjects.size();
        gameObjects.add(ProjectileFactory.createRandomizedAsteroid(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight()));
        gameObjects.add(ProjectileFactory.createAsteroid(1, 1, 1, 1, 1, 1, 1, 20));
        gameObjects.add(ProjectileFactory.createRandomizedHealthPowerUp());
        gameObjects.add(ProjectileFactory.createHealthPowerUp(1, 1, 1, 1, 1));
        gameObjects.add(ProjectileFactory.createRandomizedShieldPowerUp(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight()));
        gameObjects.add(ProjectileFactory.createShieldPowerUp(1, 1, 1, 1, 1));
        gameObjects.add(ProjectileFactory.createSlowDebuff());
        gameObjects.add(ProjectileFactory.createRandomizedLaserBeam());
        gameObjects.add(ProjectileFactory.createLaserBeam(1));
        int newListSize = gameObjects.size();
        assertEquals(newListSize, oldListSize + 9); // 9 times added to the list with factories
    }
}
