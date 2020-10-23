import Model.Entities.HitBox;
import Model.Entities.Player.Spaceship;
import Model.Entities.Player.SpaceshipFactory;
import Model.Entities.Projectiles.Projectile;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.GameWorld;
import Model.Entities.Point2D;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SpaceshipTest {

    Spaceship spaceship;
    Projectile healthPowerUp = ProjectileFactory.createHealthPowerUp(20, 20, 20, 20, 20);
    Projectile shieldPowerUp = ProjectileFactory.createShieldPowerUp(20, 20, 20, 20, 20);
    Projectile slowDebuff = ProjectileFactory.createSlowDebuff();
    Projectile asteroid = ProjectileFactory.createAsteroid(20, 20, 20, 20, 20, 20, 20, 20);
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * creates a spaceship for testing
     */
    @Before
    public void init() {
        spaceship = SpaceshipFactory.createSpaceship(0, 0, 64, 64);
    }

    /**
     * Tests if the spaceship can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test

    public void spaceshipMovedLeft() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setLeft(1);
        // Negative x value to move to the left
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getX() < startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setRight(1);
        // Positive x value to move to the right
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getX() > startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUp() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        // Negative y value to move up
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getY() < startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDown() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setDown(1);
        // Positive y value to move down
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getY() > startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() > startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndLeft() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        spaceship.setLeft(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() < startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * /**
     * Tests if the spaceship can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setDown(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() > startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndLeft() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setDown(1);
        spaceship.setLeft(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() < startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move left and right simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenLeftAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setLeft(1);
        spaceship.setRight(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move up and down simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenUpAndDown() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        spaceship.setDown(1);
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenVelocityZero() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.updateVelocity();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() == startPos.getY()));
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
        spaceship.actOnCollision(healthPowerUp.getClass(), healthPowerUp.getAmount());
        assertEquals(spaceship.getHp(), 200);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithShieldPowerUpGainShield() {
        int oldNrOfShields = spaceship.getNrOfShields();
        spaceship.actOnCollision(shieldPowerUp.getClass(), shieldPowerUp.getAmount());
        int newNrOfShields = spaceship.getNrOfShields();
        assertTrue(newNrOfShields > oldNrOfShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithSlowDebuffSpeedDecreased() {
        double oldSpeed = spaceship.getSpeed();
        spaceship.actOnCollision(slowDebuff.getClass(), slowDebuff.getAmount());
        double newSpeed = spaceship.getSpeed();
        assertTrue(newSpeed < oldSpeed);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithAsteroidHealthDecreased() {
        double oldHp = spaceship.getHp();
        spaceship.actOnCollision(asteroid.getClass(), asteroid.getAmount());
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
        spaceship.actOnCollision(asteroid.getClass(), asteroid.getAmount());
        int newNrOfShields = spaceship.getNrOfShields();
        assertTrue(newNrOfShields < oldNrOfShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testLoseShield() {
        spaceship.gainShield(1);
        int oldShields = spaceship.getNrOfShields();
        spaceship.reduceShield();
        int newShields = spaceship.getNrOfShields();
        assertTrue(newShields < oldShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testLoseShieldWhenNrShieldsIsZero() {
        int oldShields = spaceship.getNrOfShields();
        spaceship.reduceShield();
        int newShields = spaceship.getNrOfShields();
        assertEquals(newShields, oldShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void westWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(-20, 300, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void northWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, -5, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void eastWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(770, 300, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void southWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, 550, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsWestWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(-20, 300, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(-20, 300, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsNorthWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, -5, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(20, -5, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsEastWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(770, 300, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(770, 300, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsSouthWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(5, 550, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(5, 550, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }
}
