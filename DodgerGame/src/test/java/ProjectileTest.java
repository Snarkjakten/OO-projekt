import Entities.Projectiles.*;
import View.GameObjectGUI;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;

public class ProjectileTest {

    Asteroid projAsteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * @author Irja Vuorela and others
     */
    @Before
    public void init() {
        projAsteroid = new Asteroid();
//        smallAsteroidGUI = new ProjectileGUI();
        hpUp = new HealthPowerUp();
        shieldPU = new ShieldPowerUp();
    }

    @Test
    //@Author Olle Westerlund
    public void testGainShield() {
        int shields = 0;
        shields = shieldPU.gainShield();
        assertTrue(shields == 1);
    }

    @Test
    //@Author Olle Westerlund
    public void testGainHealth() {
        int totalHealth = 200;
        int currentHealth = 100;
        currentHealth += hpUp.gainHealth(totalHealth);
        assertTrue(currentHealth == 150);
    }


    @Test
    public void testAsteroidSpeed() {
        assertTrue(projAsteroid.speed > 0 );
    }

//    @Test
//    //@Author Olle Westerlund
//    public void testAsteroidDamage() {
//        assertTrue(projSmallAsteroid.getDamage() == 20);
//        assertTrue(projMediumAsteroid.getDamage() == 35);
//    }

    @Test
    //@Author Olle Westerlund
    public void testAsteroidIsNotOnScreen() {
        projAsteroid.setPosition(-80, -80);
        assertTrue(projAsteroid.isNotOnScreen());
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedLeft() {
        startPos = projAsteroid.position;
        // Negative horizontal value to move left
        projAsteroid.setHorizontal(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getX() < startPos.getX());
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedRight() {
        startPos = projAsteroid.position;
        // Positive horizontal value to move right
        projAsteroid.setHorizontal(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getX() > startPos.getX());
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUp() {
        startPos = projAsteroid.position;
        // Negative vertical value to move up
        projAsteroid.setVertical(-1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getY() < startPos.getY());
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDown() {
        startPos = projAsteroid.position;
        // Positive vertical value to move down
        projAsteroid.setVertical(1);
        projAsteroid.move(deltaTime);
        assertTrue(projAsteroid.position.getY() > startPos.getY());
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpRight() {
        startPos = projAsteroid.position;
        projAsteroid.setVertical(-1);
        projAsteroid.setHorizontal(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() > startPos.getX()) && (projAsteroid.position.getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpLeft() {
        startPos = projAsteroid.position;
        projAsteroid.setVertical(-1);
        projAsteroid.setHorizontal(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() < startPos.getX()) && (projAsteroid.position.getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownRight() {
        startPos = projAsteroid.position;
        projAsteroid.setVertical(1);
        projAsteroid.setHorizontal(1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() > startPos.getX()) && (projAsteroid.position.getY() > startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownLeft() {
        startPos = projAsteroid.position;
        projAsteroid.setVertical(1);
        projAsteroid.setHorizontal(-1);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() < startPos.getX()) && (projAsteroid.position.getY() > startPos.getY()));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = projAsteroid.position;
        projAsteroid.setVertical(0);
        projAsteroid.setHorizontal(0);
        projAsteroid.move(deltaTime);
        assertTrue((projAsteroid.position.getX() == startPos.getX()) && (projAsteroid.position.getY() == startPos.getY()));
    }
}
