package Model.Handlers;

import Model.Entities.Projectiles.Projectile;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.Entities.AbstractGameObject;

import java.util.List;

/**
 * WaveManager will spawn projectiles in the game loop according to a given scenario (spawning pattern).
 */

public class WaveManager {

    // max number of game objects allowed on the playing field
    private int maxNumGameObjects;

    // Gap sizes between projectiles in a wave.
    private final int horizontalGap = 64;
    private final int verticalGap = 64;

    private long seconds; // Current number of seconds.
    private long minutes; // Current number of minutes.

    // CoolDown periods for the different wave types.
    private double healthPowerUpCoolDown;
    private double shieldPowerUpCoolDown;
    private double slowDebuffCoolDown;
    private double horizontalWaveCoolDown;
    private double verticalWaveCoolDown;
    private double powerUpWaveCoolDown;
    private double asteroidCoolDown;
    private double laserBeamCoolDown;

    /**
     * Spawns projectiles according to a given scenario (pawning pattern).
     *
     * @param time        the elapsed time of the simulation.
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param scenario    selects a numbered scenario (a customized spawning pattern).
     * @authors Irja & Viktor
     */
    public void projectileSpawner(long time, List<AbstractGameObject> gameObjects, double deltaTime, int scenario, int maxNumGameObjects) {
        calcuateTime(time);
        removeOffscreenProjectiles(gameObjects);
        switch (scenario) {
            case 1:
                scenario1(gameObjects, deltaTime, maxNumGameObjects);
                break;
            default:
                defaultScenario(gameObjects, deltaTime, maxNumGameObjects);
                break;
        }
    }

    /**
     * Calculates seconds and minutes from nanoseconds.
     *
     * @param time elapsed time in nanoseconds.
     * @author Isak Almeros
     */
    private void calcuateTime(long time) {
        // time
        // total number of seconds
        long sec = time / 1000000000;
        this.seconds = sec % 60;
        this.minutes = sec / 60;
    }

    /**
     * removes offscreen projectiles.
     *
     * @author Irja Vuorela
     */
    private void removeOffscreenProjectiles(List<AbstractGameObject> gameObjects) {
        for (AbstractGameObject g : gameObjects) {
            if (g instanceof Projectile) {
                if (((Projectile) g).isNotOnScreen()) {
                    gameObjects.remove(g);
                    break;
                }
            }
        }
    }

    /**
     * Checks if current number of game objects + potential additions doesn't exceed max number of game objects.
     *
     * @param maxNumGameObjects max number of game objects allowed on the playing field
     * @param currentSize       current number of game objects on the playing field
     * @param addSize           number of potential additions to the list of game objects
     * @return true if current number of game objects + potential additions doesn't exceed max number of game objects, false if not
     * @authors Isak, Viktor, Irja
     */
    private boolean doesNotExceedsMaxNumSize(int maxNumGameObjects, int currentSize, int addSize) {
        return ((currentSize + addSize) < maxNumGameObjects);
    }

    // Building blocks for creating new patterns for a scenario --------------------------------------------------------

    /**
     * Adds a randomized asteroid to the list of game objects. Speed is increased with time.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param coolDown    an internal coolDown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addAsteroid(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown) {
        asteroidCoolDown = asteroidCoolDown - deltaTime;
        if (asteroidCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), gameObjects.size() / 3)) {
            Projectile asteroid = ProjectileFactory.createRandomizedAsteroid();
            asteroid.setSpeed(asteroid.getSpeed() + (seconds + minutes * 60));
            gameObjects.add(asteroid);
            asteroidCoolDown = coolDown;
        }
    }

    /**
     * Adds a shield power up to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param coolDown    an internal coolDown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addShieldPowerUp(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown) {
        shieldPowerUpCoolDown = shieldPowerUpCoolDown - deltaTime;
        if (shieldPowerUpCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), 1)) {
            gameObjects.add(ProjectileFactory.createRandomizedShieldPowerUp());
            shieldPowerUpCoolDown = coolDown;
        }
    }

    /**
     * Adds a health power up to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param coolDown    an internal coolDown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addHealthPowerUp(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown) {
        healthPowerUpCoolDown = healthPowerUpCoolDown - deltaTime;
        if (healthPowerUpCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), 1)) {
            gameObjects.add(ProjectileFactory.createRandomizedHealthPowerUp());
            healthPowerUpCoolDown = coolDown;
        }
    }

    /**
     * Adds a slowing debuff to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param coolDown    an internal coolDown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addSlowDebuff(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown) {
        slowDebuffCoolDown = slowDebuffCoolDown - deltaTime;
        if (slowDebuffCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), 1)) {
            gameObjects.add(ProjectileFactory.createSlowDebuff());
            slowDebuffCoolDown = coolDown;
        }
    }

    /**
     * Adds a wave of power ups to the list of game objects. The wave crosses the playing field from top to bottom.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param coolDown    an internal coolDown period to prevent adding too frequently
     * @param size        the size of the wave
     * @authors Viktor & Irja
     */
    private void addPowerUpWave(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown, int size) {
        powerUpWaveCoolDown = powerUpWaveCoolDown - deltaTime;
        if (powerUpWaveCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), size * 2)) {
            for (int i = 0; i < size; i++) {
                gameObjects.add(ProjectileFactory.createHealthPowerUp(400, 128 + horizontalGap * 3 * i, -65, 0, 1));
                gameObjects.add(ProjectileFactory.createShieldPowerUp(400, 64 + horizontalGap * 3 * i, -65, 0, 1));
            }
            powerUpWaveCoolDown = coolDown;
        }
    }

    /**
     * Adds a wave of asteroids to the list of game objects. The wave crosses the playing field from left to right.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param coolDown    an internal coolDown period to prevent adding too frequently
     * @param size        the size of the wave
     * @authors Viktor & Irja
     */
    private void addVerticalWave(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown, int size) {
        verticalWaveCoolDown = verticalWaveCoolDown - deltaTime;
        if (verticalWaveCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), 1)) {
            for (int i = 0; i < size; i++) {
                gameObjects.add(ProjectileFactory.createAsteroid(200, 32, 32, -65, 32 + (verticalGap * 2 * i), 1, 0, 20));
            }
            verticalWaveCoolDown = coolDown;
        }
    }

    /**
     * Adds a wave of asteroids to the list of game objects. The wave crosses the playing field from top to bottom.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param coolDown    an internal coolDown period to prevent adding too frequently
     * @param size        the size of the wave
     * @authors Viktor & Irja
     */
    private void addHorizontalWave(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown, int size) {
        horizontalWaveCoolDown = horizontalWaveCoolDown - deltaTime;
        if (horizontalWaveCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), 1)) {
            for (int i = 0; i < size; i++) {
                gameObjects.add(ProjectileFactory.createAsteroid(200, 32, 32, 64 + (horizontalGap * 2 * i), -65, 0, 1, 20));
            }
            horizontalWaveCoolDown = coolDown;
        }
    }

    /**
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param coolDown    an internal coolDown period to prevent adding too frequently
     * @author Olle, Irja, Viktor
     */
    private void addLaserBeam(List<AbstractGameObject> gameObjects, double deltaTime, double coolDown) {
        laserBeamCoolDown = laserBeamCoolDown - deltaTime;
        if (laserBeamCoolDown < 0 && doesNotExceedsMaxNumSize(maxNumGameObjects, gameObjects.size(), 1)) {
            gameObjects.add(ProjectileFactory.createRandomizedLaserBeam());
            laserBeamCoolDown = coolDown;
        }
    }

    // Customized spawning patterns ------------------------------------------------------------------------------------

    /**
     * A customized spawn pattern to be used by projectileSpawner if no other scenario is specified.
     *
     * @param gameObjects       the list of game objects in the game loop
     * @param deltaTime         length of a frame in the game loop
     * @param maxNumGameObjects the maximum number of game objects allowed on the playing field
     * @author Irja Vuorela
     */
    private void defaultScenario(List<AbstractGameObject> gameObjects, double deltaTime, int maxNumGameObjects) {

        this.maxNumGameObjects = maxNumGameObjects;

        // Adds randomized asteroids
        if (gameObjects.size() <= 3 + seconds) {
            addAsteroid(gameObjects, deltaTime, 0.1);
        }
        // add power ups and a slowing debuff every five seconds
        if (seconds % 5 == 0) {
            addHealthPowerUp(gameObjects, deltaTime, 1);
            addShieldPowerUp(gameObjects, deltaTime, 1);
            addSlowDebuff(gameObjects, deltaTime, 1);
        }
    }

    /**
     * A customized spawn pattern to be used by projectileSpawner.
     *
     * @param gameObjects       the list of game objects in the game loop
     * @param deltaTime         length of a frame in the game loop
     * @param maxNumGameObjects the maximum number of game objects allowed on the playing field
     * @authors Irja & Viktor
     */
    private void scenario1(List<AbstractGameObject> gameObjects, double deltaTime, int maxNumGameObjects) {

        this.maxNumGameObjects = maxNumGameObjects;
        // sizes of the waves
        int verticalWaveSize = 5;
        int horizontalWaveSize = 6;
        int powerUpWaveSize = 8;

        // add power ups and a slowing debuff every ten seconds
        if (seconds % 10 == 0) {
            addHealthPowerUp(gameObjects, deltaTime, 1);
            addShieldPowerUp(gameObjects, deltaTime, 1);
            addSlowDebuff(gameObjects, deltaTime, 1);
        }
        if (seconds < 20 || seconds > 30) {
            // adds a laser beam every 7 seconds
            if (seconds % 16 == 3) {
                addLaserBeam(gameObjects, deltaTime, 2);
            }
            // adds a vertical wave of asteroids every five seconds
            if (seconds % 5 == 2) {
                addVerticalWave(gameObjects, deltaTime, 2, verticalWaveSize);
            }
            // adds a horizontal wave of asteroids every three seconds
            if (seconds % 3 == 1) {
                addHorizontalWave(gameObjects, deltaTime, 2, horizontalWaveSize);
            }
            // leaves room for the largest wave size
            if (gameObjects.size() <= 3 + seconds) {
                addAsteroid(gameObjects, deltaTime, 0.1);
            }
        }
        // a small break from asteroids with waves of power ups
        if (seconds > 25 && seconds < 30) {
            addPowerUpWave(gameObjects, deltaTime, 2, powerUpWaveSize / 2);
        }
    }
}