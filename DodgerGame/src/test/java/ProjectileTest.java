import Entities.Projectiles.*;
import javafx.geometry.Point2D;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;

public class ProjectileTest {

    SmallAsteroid projSmallAsteroid;
    MediumAsteroid projMediumAsteroid;
    ProjectileGUI smallAsteroidGUI;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    Point2D startPos;

    @Before
    // @Author Irja Vuorela
    public void init() {
        projSmallAsteroid = new SmallAsteroid();
        projMediumAsteroid = new MediumAsteroid();
        smallAsteroidGUI = new ProjectileGUI(ProjectileFactory.createSmallAsteroid());
        hpUp = new HealthPowerUp();
        shieldPU = new ShieldPowerUp();
    }

    @Test
    public void testGainShield() {
        int shields = 0;
        shields = shieldPU.gainShield();
        assertTrue(shields == 1);
    }

    @Test
    public void testGainHealth() {
        int totalHealth = 200;
        int currentHealth = 100;
        currentHealth += hpUp.gainHealth(totalHealth);
        assertTrue(currentHealth == 150);
    }

    @Test
    public void testProjectileAndGuiPosition() {
        Point2D projPosition = smallAsteroidGUI.getProjectile().position;
        Point2D projGuiPosition = smallAsteroidGUI.getPoint();
        assertTrue(projPosition.getX() == projGuiPosition.getX() &&
                   projPosition.getY() == projGuiPosition.getY());
    }

    @Test
    public void testAsteroidSpeed() {
        assertTrue(projSmallAsteroid.getSpeed() == 5);
        assertTrue(projMediumAsteroid.getSpeed() == 3);
    }

    @Test
    public void testAsteroidDamage() {
        assertTrue(projSmallAsteroid.getDamage() == 20);
        assertTrue(projMediumAsteroid.getDamage() == 35);
    }

    @Test
    public void testAsteroidIsNotOnScreen() {
        projSmallAsteroid.setPosition(-80, -80);
        assertTrue(projSmallAsteroid.isNotOnScreen());
    }

    @Test
    // Does move() move the projectile's position to the left?
    // @Author Irja Vuorela
    public void ProjectileMovedLeft() {
        startPos = projSmallAsteroid.position;
        // Negative horizontal value to move left
        projSmallAsteroid.setHorizontal(-1);
        projSmallAsteroid.move();
        assertTrue(projSmallAsteroid.position.getX() < startPos.getX());
    }

    @Test
    // Does move() move the projectile's position to the right?
    // @Author Irja Vuorela
    public void ProjectileMovedRight() {
        startPos = projSmallAsteroid.position;
        // Positive horizontal value to move right
        projSmallAsteroid.setHorizontal(1);
        projSmallAsteroid.move();
        assertTrue(projSmallAsteroid.position.getX() > startPos.getX());
    }

    @Test
    // Does move() move the projectile's position up?
    // @Author Irja Vuorela
    public void ProjectileMovedUp() {
        startPos = projSmallAsteroid.position;
        // Negative vertical value to move up
        projSmallAsteroid.setVertical(-1);
        projSmallAsteroid.move();
        assertTrue(projSmallAsteroid.position.getY() < startPos.getY());
    }

    @Test
    // Does move() move the projectile's position down?
    // @Author Irja Vuorela
    public void ProjectileMovedDown() {
        startPos = projSmallAsteroid.position;
        // Positive vertical value to move down
        projSmallAsteroid.setVertical(1);
        projSmallAsteroid.move();
        assertTrue(projSmallAsteroid.position.getY() > startPos.getY());
    }

    @Test
    // Does move() move the projectile's position up and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpRight() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(-1);
        projSmallAsteroid.setHorizontal(1);
        projSmallAsteroid.move();
        assertTrue((projSmallAsteroid.position.getX() > startPos.getX()) && (projSmallAsteroid.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position up and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedUpLeft() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(-1);
        projSmallAsteroid.setHorizontal(-1);
        projSmallAsteroid.move();
        assertTrue((projSmallAsteroid.position.getX() < startPos.getX()) && (projSmallAsteroid.position.getY() < startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position down and right diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownRight() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(1);
        projSmallAsteroid.setHorizontal(1);
        projSmallAsteroid.move();
        assertTrue((projSmallAsteroid.position.getX() > startPos.getX()) && (projSmallAsteroid.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() move the projectile's position down and left diagonally?
    // @Author Irja Vuorela
    public void ProjectileMovedDownLeft() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(1);
        projSmallAsteroid.setHorizontal(-1);
        projSmallAsteroid.move();
        assertTrue((projSmallAsteroid.position.getX() < startPos.getX()) && (projSmallAsteroid.position.getY() > startPos.getY()));
    }

    @Test
    // Does move() leave the projectile's position unchanged when its velocity was zero?
    // @Author Irja Vuorela
    public void ProjectileNotMovingWhenVelocityZero() {
        startPos = projSmallAsteroid.position;
        projSmallAsteroid.setVertical(0);
        projSmallAsteroid.setHorizontal(0);
        projSmallAsteroid.move();
        assertTrue((projSmallAsteroid.position.getX() == startPos.getX()) && (projSmallAsteroid.position.getY() == startPos.getY()));
    }
}
