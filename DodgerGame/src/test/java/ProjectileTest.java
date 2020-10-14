import Game.Entities.Projectiles.*;
import View.GameObjectGUI;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;

public class ProjectileTest {

    SmallAsteroid projSmallAsteroid;
    MediumAsteroid projMediumAsteroid;
    GameObjectGUI smallAsteroidGUI;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Point2D startPos;
    double deltaTime = 0.016;

    /**
     * @author Irja Vuorela and others
     */
    @Before
    public void init() {
        projSmallAsteroid = new SmallAsteroid();
        projMediumAsteroid = new MediumAsteroid();
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

//    @Test
//    //@Author Olle Westerlund
//    public void testProjectileAndGuiPosition() {
//        Point2D projPosition = smallAsteroidGUI.getProjectile().position;
//        Point2D projGuiPosition = smallAsteroidGUI.getPoint();
//        assertTrue(projPosition.getX() == projGuiPosition.getX() &&
//                   projPosition.getY() == projGuiPosition.getY());
//    }

    /* hardcoded values
    @Test
    public void testAsteroidSpeed() {
        assertTrue(projSmallAsteroid.getSpeed() == 5);
        assertTrue(projMediumAsteroid.getSpeed() == 3);
    }
    */

    @Test
    //@Author Olle Westerlund
    public void testAsteroidDamage() {
        assertTrue(projSmallAsteroid.getDamage() == 20);
        assertTrue(projMediumAsteroid.getDamage() == 35);
    }

    @Test
    //@Author Olle Westerlund
    public void testAsteroidIsNotOnScreen() {
        projSmallAsteroid.setPosition(-80, -80);
        assertTrue(projSmallAsteroid.isNotOnScreen());
    }

    /**
     * Tests if a projectile can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedLeft() {
        startPos = projSmallAsteroid.position;
        // Negative horizontal value to move left
        projSmallAsteroid.setHorizontal(-1);
        projSmallAsteroid.move(deltaTime);
        assertTrue(projSmallAsteroid.position.getX() < startPos.getX());
    }

    /**
     * Tests if a projectile can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedRight() {
        startPos = projSmallAsteroid.position;
        // Positive horizontal value to move right
        projSmallAsteroid.setHorizontal(1);
        projSmallAsteroid.move(deltaTime);
        assertTrue(projSmallAsteroid.position.getX() > startPos.getX());
    }

    /**
     * Tests if a projectile can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUp() {
        startPos = projSmallAsteroid.position;
        // Negative vertical value to move up
        projSmallAsteroid.setVertical(-1);
        projSmallAsteroid.move(deltaTime);
        assertTrue(projSmallAsteroid.position.getY() < startPos.getY());
    }

    /**
     * Tests if a projectile can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDown() {
        startPos = projSmallAsteroid.position;
        // Positive vertical value to move down
        projSmallAsteroid.setVertical(1);
        projSmallAsteroid.move(deltaTime);
        assertTrue(projSmallAsteroid.position.getY() > startPos.getY());
    }

    /**
     * Tests if a projectile can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpRight() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(-1);
        projSmallAsteroid.setHorizontal(1);
        projSmallAsteroid.move(deltaTime);
        assertTrue((projSmallAsteroid.position.getX() > startPos.getX()) && (projSmallAsteroid.position.getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedUpLeft() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(-1);
        projSmallAsteroid.setHorizontal(-1);
        projSmallAsteroid.move(deltaTime);
        assertTrue((projSmallAsteroid.position.getX() < startPos.getX()) && (projSmallAsteroid.position.getY() < startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownRight() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(1);
        projSmallAsteroid.setHorizontal(1);
        projSmallAsteroid.move(deltaTime);
        assertTrue((projSmallAsteroid.position.getX() > startPos.getX()) && (projSmallAsteroid.position.getY() > startPos.getY()));
    }

    /**
     * Tests if a projectile can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileMovedDownLeft() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(1);
        projSmallAsteroid.setHorizontal(-1);
        projSmallAsteroid.move(deltaTime);
        assertTrue((projSmallAsteroid.position.getX() < startPos.getX()) && (projSmallAsteroid.position.getY() > startPos.getY()));
    }

    /**
     * Tests if the projectile's position is left unchanged when attempting to move while its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(0);
        projSmallAsteroid.setHorizontal(0);
        projSmallAsteroid.move(deltaTime);
        assertTrue((projSmallAsteroid.position.getX() == startPos.getX()) && (projSmallAsteroid.position.getY() == startPos.getY()));
    }
}
