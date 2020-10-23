import Model.Entities.HitBox;
import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.HealthPowerUp;
import Model.Entities.Projectiles.LaserBeam;
import Model.Entities.Projectiles.ShieldPowerUp;
import Model.GameWorld;
import Model.CollisionHandler;
import Model.PlayingField;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandlerTest {

    Asteroid asteroid;
    HealthPowerUp hpUp;
    ShieldPowerUp shieldPU;
    LaserBeam laserBeam;
    Spaceship spaceship;

    CollisionHandler collisionHandler = new CollisionHandler();

    @Before
    public void init() {
        asteroid = new Asteroid(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldWidth());
        hpUp = new HealthPowerUp();
        shieldPU = new ShieldPowerUp(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
        laserBeam = new LaserBeam(1, PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
        spaceship = new Spaceship(368, 248, 64, 64);
    }

    @Test
    public void hitBoxesCollided() {
        spaceship.getHitBoxes().get(0).updateHitBox(10, 10, 10, 10);
        asteroid.getHitBoxes().get(0).updateHitBox(10, 10, 10, 10);
        assertTrue(collisionHandler.checkCollision(asteroid, spaceship));
    }

    @Test
    public void doesNotCollide() {
        spaceship.getHitBoxes().get(0).updateHitBox(200, 200, 10, 10);
        asteroid.getHitBoxes().get(0).updateHitBox(10, 10, 10, 10);
        assertFalse(collisionHandler.checkCollision(asteroid, spaceship));
    }

    @Test
    public void spaceshipCollided() {
        collisionHandler.collide(asteroid, spaceship);
        assertTrue(asteroid.getCollided());
        asteroid.setCollided(false);
    }

    @Test
    public void projectileCollided() {
        collisionHandler.collide(asteroid, hpUp);
        assertFalse(asteroid.getCollided());
    }

    //----------------------------------------------------------------------------------------------------------------

    /**
     * Tests if checkCollisions works for objects with multiple hitBoxes.
     *
     * @author Irja Vuorela
     */
    @Test
    public void testCheckCollisionWithMultipleHitBoxes() {
        asteroid = new Asteroid(0, 10, 10, 500, 500, 0, 0, 20);
        spaceship = new Spaceship(0, 0, 10, 10);
        HitBox hitBox2 = new HitBox(500, 500, 10, 10);
        spaceship.getHitBoxes().add(hitBox2);
        boolean intersects = spaceship.getHitBoxes().get(0).getHitBox().intersects(asteroid.getHitBoxes().get(0).getHitBox());
        boolean intersects2 = spaceship.getHitBoxes().get(1).getHitBox().intersects(asteroid.getHitBoxes().get(0).getHitBox());
        boolean checkCollision = collisionHandler.checkCollision(spaceship, asteroid);
        assertTrue(checkCollision == intersects2 && !intersects);
    }

    /**
     * Tests if the method adds an observer by checking the size of the list
     *
     * @author Tobias Engblom
     */
    @Test
    public void addGameObjectObserverSizeOne() {
        Main main = new Main();
        collisionHandler.addGameObjectObserver(main);
        assertEquals(1, collisionHandler.getGameObjectObservers().size());
    }

    /**
     * Tests if the method removes an observer by checking the size of the list
     *
     * @author Tobias Engblom
     */
    @Test
    public void removeGameObjectObserverSizeZero() {
        Main main = new Main();
        collisionHandler.addGameObjectObserver(main);
        collisionHandler.removeGameObjectObserver(main);
        assertEquals(0, collisionHandler.getGameObjectObservers().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void handleSpaceshipCollisionIfIsFirstGameObject() {
        spaceship.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        asteroid.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        GameWorld.getInstance().getGameObjects().clear();
        GameWorld.getInstance().getGameObjects().add(asteroid);
        GameWorld.getInstance().getGameObjects().add(spaceship);
        collisionHandler.handleCollision(GameWorld.getInstance().getGameObjects());
        assertTrue(asteroid.getCollided());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void handleSpaceshipCollisionIfIsSecondGameObject() {
        spaceship.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        asteroid.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        GameWorld.getInstance().getGameObjects().clear();
        GameWorld.getInstance().getGameObjects().add(spaceship);
        GameWorld.getInstance().getGameObjects().add(asteroid);
        collisionHandler.handleCollision(GameWorld.getInstance().getGameObjects());
        assertTrue(asteroid.getCollided());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void handleLaserBeamCollision() {
        asteroid.setCollided(false);
        laserBeam.getHitBoxes().get(0).updateHitBoxPosition(20, -50);
        asteroid.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        GameWorld.getInstance().getGameObjects().clear();
        GameWorld.getInstance().getGameObjects().add(asteroid);
        GameWorld.getInstance().getGameObjects().add(laserBeam);
        collisionHandler.handleCollision(GameWorld.getInstance().getGameObjects());
        assertTrue(asteroid.getCollided());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void handleTwoAsteroidsCollision() {
        Asteroid asteroid2 = new Asteroid(PlayingField.getPlayingFieldWidth(), PlayingField.getPlayingFieldHeight());
        asteroid.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        asteroid2.getHitBoxes().get(0).updateHitBoxPosition(20, 20);
        GameWorld.getInstance().getGameObjects().clear();
        GameWorld.getInstance().getGameObjects().add(asteroid);
        GameWorld.getInstance().getGameObjects().add(asteroid2);
        collisionHandler.handleCollision(GameWorld.getInstance().getGameObjects());
        assertFalse(asteroid.getCollided());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testNotifyGameObjectObservers() {
        Main main = new Main();
        collisionHandler.addGameObjectObserver(main);
        collisionHandler.notifyGameObjectObservers(spaceship.toString(), spaceship.getAmount());
    }
}