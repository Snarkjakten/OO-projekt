package Model;

import Model.Entities.Projectiles.Projectile;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.Movement.AbstractGameObject;

import java.util.List;

/**
 * WaveManager will spawn projectiles in the game loop according to a given scenario (spawning pattern).
 */

public class WaveManager {

    // Gap sizes between projectiles in a wave.
    private int horizontalGap = 64;
    private int verticalGap = 64;

    private long seconds; // Current number of seconds.
    private long minutes; // Current number of minutes.

    // Cooldown periods for the different wave types.
    private double healthPowerUpCooldown;
    private double shieldPowerUpCooldown;
    private double slowDebuffCooldown;
    private double horizontalWaveCooldown;
    private double verticalWaveCooldown;
    private double powerUpWaveCooldown;
    private double asteroidCooldown;

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

    // Building blocks for creating new patterns for a scenario --------------------------------------------------------

    /**
     * Adds a randomized asteroid to the list of game objects. Speed is increased with time.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param cooldown    an internal cooldown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addAsteroid(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        asteroidCooldown = asteroidCooldown - deltaTime;
        if (asteroidCooldown < 0) {
            Projectile asteroid = ProjectileFactory.createRandomizedAsteroid();
            asteroid.setSpeed(asteroid.getSpeed() + (seconds + minutes * 60));
            gameObjects.add(asteroid);
            asteroidCooldown = cooldown;
        }
    }

    /**
     * Adds a shield power up to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param cooldown    an internal cooldown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addShieldPowerUp(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        shieldPowerUpCooldown = shieldPowerUpCooldown - deltaTime;
        if (shieldPowerUpCooldown < 0) {
            gameObjects.add(ProjectileFactory.createRandomizedShieldPowerUp());
            shieldPowerUpCooldown = cooldown;
        }
    }

    /**
     * Adds a health power up to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param cooldown    an internal cooldown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addHealthPowerUp(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        healthPowerUpCooldown = healthPowerUpCooldown - deltaTime;
        if (healthPowerUpCooldown < 0) {
            gameObjects.add(ProjectileFactory.createRandomizedHealthPowerUp());
            healthPowerUpCooldown = cooldown;
        }
    }

    /**
     * Adds a slowing debuff to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop.
     * @param deltaTime   length of a frame in the game loop.
     * @param cooldown    an internal cooldown period to prevent adding too frequently.
     * @authors Viktor & Irja
     */
    private void addSlowDebuff(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        slowDebuffCooldown = slowDebuffCooldown - deltaTime;
        if (slowDebuffCooldown < 0) {
            gameObjects.add(ProjectileFactory.createSlowDebuff());
            slowDebuffCooldown = cooldown;
        }
    }

    /**
     * Adds a wave of power ups to the list of game objects. The wave crosses the playing field from top to bottom.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown period to prevent adding too frequently
     * @param size        the size of the wave
     * @authors Viktor & Irja
     */
    private void addPowerUpWave(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown, int size) {
        powerUpWaveCooldown = powerUpWaveCooldown - deltaTime;
        if (powerUpWaveCooldown < 0) {
            for (int i = 0; i < size; i++) {
                gameObjects.add(ProjectileFactory.createHealthPowerUp(400, 128 + horizontalGap * 3 * i, -65, 0, 1));
                gameObjects.add(ProjectileFactory.createShieldPowerUp(400, 64 + horizontalGap * 3 * i, -65, 0, 1));
            }
        }
        powerUpWaveCooldown = cooldown;
    }

    /**
     * Adds a wave of asteroids to the list of game objects. The wave crosses the playing field from left to right.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown period to prevent adding too frequently
     * @param size        the size of the wave
     * @authors Viktor & Irja
     */
    private void addVerticalWave(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown, int size) {
        verticalWaveCooldown = verticalWaveCooldown - deltaTime;
        if (verticalWaveCooldown < 0) {
            for (int i = 0; i < size; i++) {
                gameObjects.add(ProjectileFactory.createAsteroid(200, 64, 64, -65, 32 + (verticalGap * 2 * i), 1, 0));
            }
            verticalWaveCooldown = cooldown;
        }
    }

    /**
     * Adds a wave of asteroids to the list of game objects. The wave crosses the playing field from top to bottom.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown period to prevent adding too frequently
     * @param size        the size of the wave
     * @authors Viktor & Irja
     */
    private void addHorizontalWave(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown, int size) {
        horizontalWaveCooldown = horizontalWaveCooldown - deltaTime;
        if (horizontalWaveCooldown < 0) {
            for (int i = 0; i < size; i++) {
                gameObjects.add(ProjectileFactory.createAsteroid(200, 64, 64, 32 + (horizontalGap * 2 * i), -65, 0, 1));
            }
            horizontalWaveCooldown = cooldown;
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
        // Adds randomized asteroids as long as the maximum number of projectiles isn't exceeded
        if (gameObjects.size() < maxNumGameObjects) {
            if (gameObjects.size() <= 3 + seconds) {
                addAsteroid(gameObjects, deltaTime, 0.1);
            }
            // add power ups and a slowing debuff every five seconds
            if (seconds % 5 == 0 && gameObjects.size() < (maxNumGameObjects - 3)) {
                addHealthPowerUp(gameObjects, deltaTime, 1);
                addShieldPowerUp(gameObjects, deltaTime, 1);
                addSlowDebuff(gameObjects, deltaTime, 1);
            }
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

        // sizes of the waves
        int verticalWaveSize = 5;
        int horizontalWaveSize = 6;
        int powerUpWaveSize = 4;

        // add power ups and a slowing debuff every ten seconds
        if (seconds % 10 == 0 && gameObjects.size() < maxNumGameObjects - powerUpWaveSize) {
            addHealthPowerUp(gameObjects, deltaTime, 1);
            addShieldPowerUp(gameObjects, deltaTime, 1);
            addSlowDebuff(gameObjects, deltaTime, 1);
        }
        if (seconds < 20 || seconds > 30) {
            // adds a vertical wave of asteroids every five seconds
            if (seconds % 5 == 0 && gameObjects.size() < maxNumGameObjects - verticalWaveSize) {
                addVerticalWave(gameObjects, deltaTime, 2, verticalWaveSize);
            }
            // adds a horizontal wave of asteroids every three seconds
            if (seconds % 3 == 0 && gameObjects.size() < maxNumGameObjects - horizontalWaveSize) {
                addHorizontalWave(gameObjects, deltaTime, 2, horizontalWaveSize);
            }
            // leaves room for the largest wave size
            if (gameObjects.size() <= 3 + seconds && gameObjects.size() < maxNumGameObjects - horizontalWaveSize) {
                addAsteroid(gameObjects, deltaTime, 0.1);
            }
            // a small break from asteroids with waves of power ups
        } else if (seconds > 25 && seconds < 30 && gameObjects.size() < maxNumGameObjects - powerUpWaveSize) {
            addPowerUpWave(gameObjects, deltaTime, 0.5, powerUpWaveSize);
        }
    }
}