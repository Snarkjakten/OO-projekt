import Model.Entities.HitBox;
import Model.Entities.Player.Spaceship;
import Model.Entities.Player.SpaceshipFactory;
import Model.Entities.Projectiles.*;
import Model.GameWorld;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SpaceshipTest {

    GameWorld gameWorld;
    Spaceship spaceship;
    Projectile healthPowerUp;
    Projectile shieldPowerUp;
    Projectile slowDebuff;
    Projectile asteroid;
    double x;
    double y;
    double deltaTime = 0.016;

    /**
     * creates a spaceship for testing
     */
    @Before
    public void init() {
        gameWorld = GameWorld.getInstance();
        spaceship = SpaceshipFactory.createSpaceship(0, 0, 64, 64);
        healthPowerUp = ProjectileFactory.createHealthPowerUp(200, 20, 20, 20, 20);
        shieldPowerUp = ProjectileFactory.createShieldPowerUp(200, 20, 20, 20, 20);
        slowDebuff = ProjectileFactory.createSlowDebuff();
        asteroid = ProjectileFactory.createAsteroid(20, 20, 20, 20, 20, 20, 20);
    }

    /**
     * Tests if the spaceship can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test

    public void spaceshipMovedLeft() {
        x = spaceship.getHitBoxes().get(0).getX();
        spaceship.setLeft(1);
        // Negative x value to move to the left
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getX() < x);
    }

    /**
     * Tests if the spaceship can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedRight() {
        x = spaceship.getHitBoxes().get(0).getX();
        spaceship.setRight(1);
        // Positive x value to move to the right
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getX() > x);
    }

    /**
     * Tests if the spaceship can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUp() {
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setUp(1);
        // Negative y value to move up
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getY() < y);
    }

    /**
     * Tests if the spaceship can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDown() {
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setDown(1);
        // Positive y value to move down
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getY() > y);
    }

    /**
     * Tests if the spaceship can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndRight() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setUp(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() > x) && (spaceship.getHitBoxes().get(0).getY() < y));
    }

    /**
     * Tests if the spaceship can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndLeft() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setUp(1);
        spaceship.setLeft(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() < x) && (spaceship.getHitBoxes().get(0).getY() < y));
    }

    /**
     * /**
     * Tests if the spaceship can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndRight() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setDown(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() > x) && (spaceship.getHitBoxes().get(0).getY() > y));
    }

    /**
     * Tests if the spaceship can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndLeft() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setDown(1);
        spaceship.setLeft(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() < x) && (spaceship.getHitBoxes().get(0).getY() > y));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move left and right simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenLeftAndRight() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setLeft(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == x) && (spaceship.getHitBoxes().get(0).getY() == y));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move up and down simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenUpAndDown() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.setUp(1);
        spaceship.setDown(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == x) && (spaceship.getHitBoxes().get(0).getY() == y));
    }

    /**
     * Tests if the spaceship stays in place when its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenVelocityZero() {
        x = spaceship.getHitBoxes().get(0).getX();
        y = spaceship.getHitBoxes().get(0).getY();
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == x) && (spaceship.getHitBoxes().get(0).getY() == y));
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void westWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(-20, 300, 64, 64);
        gameWorld.wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void northWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, -5, 64, 64);
        gameWorld.wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void eastWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(770, 300, 64, 64);
        gameWorld.wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void southWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, 550, 64, 64);
        gameWorld.wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsWestWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(-20, 300, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(-20, 300, 64, 64));
        gameWorld.wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsNorthWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, -5, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(20, -5, 64, 64));
        gameWorld.wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsEastWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(770, 300, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(770, 300, 64, 64));
        gameWorld.wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsSouthWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(5, 550, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(5, 550, 64, 64));
        gameWorld.wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @authors Viktor & Irja
     */
    @Test
    public void testGainShield() {
        int oldShields = spaceship.getNrOfShields();
        spaceship.gainShield(1);
        int newShields = spaceship.getNrOfShields();
        assertTrue(newShields > oldShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testLoseShield() {
        spaceship.gainShield(1);
        int oldShields = spaceship.getNrOfShields();
        spaceship.loseShield();
        int newShields = spaceship.getNrOfShields();
        assertTrue(newShields < oldShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testLoseShieldWhenNrShieldsIsZero() {
        int oldShields = spaceship.getNrOfShields();
        spaceship.loseShield();
        int newShields = spaceship.getNrOfShields();
        assertEquals(newShields, oldShields);
    }

    /**
     * Test that the health power up increases the health.
     *
     * @author Olle Westerlund, Irja Vuorela, Viktor Sundberg
     */
    @Test
    public void testGainHealth() {
        spaceship.setHp(spaceship.getMaxHp() / 2);
        int oldHealth = spaceship.getHp();
        spaceship.gainHealth(1);
        int newHealth = spaceship.getHp();
        assertTrue(newHealth > oldHealth);
    }

    /**
     * Checks that player can't heal beyond the set max hp value
     *
     * @authors Irja & Viktor
     */
    @Test
    public void didNotExceedMaxHp() {
        spaceship.setHp(spaceship.getMaxHp() - 1);
        spaceship.gainHealth(spaceship.getMaxHp());
        assertEquals(spaceship.getHp(), spaceship.getMaxHp());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithHealthPowerUpGainHealth() {
        spaceship.setHp(150);
        spaceship.actOnCollisionEvent(healthPowerUp);
        assertEquals(spaceship.getHp(), 200);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithShieldPowerUpGainShield() {
        int oldNrOfShields = spaceship.getNrOfShields();
        spaceship.actOnCollisionEvent(shieldPowerUp);
        int newNrOfShields = spaceship.getNrOfShields();
        assertTrue(newNrOfShields > oldNrOfShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithSlowDebuffSpeedDecreased() {
        double oldSpeed = spaceship.getSpeed();
        spaceship.actOnCollisionEvent(slowDebuff);
        double newSpeed = spaceship.getSpeed();
        assertTrue(newSpeed < oldSpeed);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithAsteroidHealthDecreased() {
        double oldHp = spaceship.getHp();
        spaceship.actOnCollisionEvent(asteroid);
        double newHp = spaceship.getHp();
        assertTrue(newHp < oldHp);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithAsteroidLoseShield() {
        spaceship.gainShield(1);
        int oldNrOfShields = spaceship.getNrOfShields();
        spaceship.actOnCollisionEvent(asteroid);
        int newNrOfShields = spaceship.getNrOfShields();
        assertTrue(newNrOfShields < oldNrOfShields);
    }
}
