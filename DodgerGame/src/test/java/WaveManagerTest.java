import Model.Entities.Projectiles.Asteroid;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.WaveManager;
import Model.GameWorld;
import Model.Movement.AbstractGameObject;
import Model.Entities.Player.SpaceshipFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class WaveManagerTest {
    WaveManager waveManager = new WaveManager();
    List<AbstractGameObject> gameObjects = new ArrayList<>();
    double playingFieldWidth = GameWorld.getInstance().getPlayingFieldWidth();
    double playingFieldHeight = GameWorld.getInstance().getPlayingFieldHeight();
    int maxNumGameObjects;
    long time; // elapsed time in nanoseconds
    double deltaTime;  // the length of a frame (one iteration of the game loop in the simulation)
    int scenario; // 0 selects the default scenario for the projectile spawner.

    /**
     * @author Irja Vuorela
     */
    @Before
    public void init() {
        time = 0;
        gameObjects.add(SpaceshipFactory.createSpaceship(0, 0, 64, 64));
        deltaTime = 0.016; // approximate length of a frame in 60 FPS.
        scenario = 0; // selects the default scenario for the projectile spawner.
        maxNumGameObjects = 25;
    }

    /**
     * Checks if offscreen projectiles are successfully removed from the list of game objects.
     *
     * @authors Irja & Viktor
     */
    @Test
    public void removedOffscreenAsteroids() {
        boolean offscreenAsteroidExists = false;
        gameObjects.add(ProjectileFactory.createAsteroid(0, 64, 64, playingFieldWidth * 2, playingFieldHeight * 2, 0, 0));
        // waveManager removes offscreen projectiles
        waveManager.projectileSpawner(time, gameObjects, deltaTime, scenario, maxNumGameObjects);
        for (AbstractGameObject gameObject : gameObjects) {
            if (gameObject instanceof Asteroid) {
                if (((Asteroid) gameObject).isNotOnScreen()) {
                    offscreenAsteroidExists = true;
                }
            }
            assertFalse(offscreenAsteroidExists);
        }
    }

    /**
     * Checks if projectileSpawner successfully adds projectiles to the list of game objects.
     *
     * @authors Irja & Viktor
     */
    @Test
    public void spawnedProjectiles() {
        int oldSize = gameObjects.size();
        // waveManager spawns projectiles
        waveManager.projectileSpawner(time, gameObjects, deltaTime, scenario, maxNumGameObjects);
        int newSize = gameObjects.size();
        assertTrue(oldSize < newSize);
    }

    /**
     * Checks that the size of the game objects list does not exceed the defined max size limit for scenario0.
     *
     * @authors Irja & Viktor
     */
    @Test
    public void scenario0DoesNotExceedMaxNumGameObjects() {
        int maxLimit = 5;
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 0, maxLimit);
        assertTrue(gameObjects.size() <= maxLimit);
    }

    /**
     * Checks that the size of the game objects list does not exceed the defined max size limit for scenario1.
     *
     * @authors Irja & Viktor
     */
    @Test
    public void scenario1DoesNotExceedMaxNumGameObjects() {
        int maxLimit = 5;
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        waveManager.projectileSpawner(time, gameObjects, deltaTime, 1, maxLimit);
        assertTrue(gameObjects.size() <= maxLimit);
    }
}
