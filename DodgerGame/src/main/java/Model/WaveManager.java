package Model;

import Model.Entities.Projectiles.Projectile;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.Movement.AbstractGameObject;

import java.util.List;

/**
 * @authors Viktor Sundberg & Irja Vuorela
 */

public class WaveManager {

    // gap sizes between projectiles in a wave
    private int horizontalGap = 64;
    private int verticalGap = 64;

    // time
    private long sec; // total number of seconds
    private long seconds; // current number of seconds
    private long minutes; // current number of minutes

    // The maximum number of allowed projectiles on the playing field
    private int maxNumProjectiles = 20;

    // Cooldowns for the different wave types
    private double healthPowerUpCooldown;
    private double shieldPowerUpCooldown;
    private double slowDebuffCooldown;
    private double horizontalWaveCooldown;
    private double verticalWaveCooldown;
    private double powerUpWaveCooldown;
    private double asteroidCooldown;

    /**
     * @param time elapsed time
     * @author Isak Almeros
     */
    private void calcuateTime(long time) {
        this.sec = time / 1000000000;
        this.seconds = sec % 60;
        this.minutes = sec / 60;
    }

    /**
     * Spawns projectiles according to a given scenario
     *
     * @param time        the elapsed time of the simulation
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param scenario    a customized spawn pattern
     * @authors Irja & Viktor
     */
    public void projectileSpawner(long time, List<AbstractGameObject> gameObjects, double deltaTime, int scenario) {
        calcuateTime(time);
        System.out.println("seconds: " + seconds);
        removeOffscreenProjectiles(gameObjects);
        switch (scenario) {
            case 1:
                scenario1(gameObjects, deltaTime);
                break;
            default:
                break;
        }
    }

    /**
     * A customized spawn pattern to be used by projectileSpawner
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @authors Irja & Viktor
     */
    private void scenario1(List<AbstractGameObject> gameObjects, double deltaTime) {

        if (seconds < 20 || seconds > 30) {
            // checks that max size for list of game objects isn't exceeded
            if (gameObjects.size() <= 3 + seconds && gameObjects.size() <= maxNumProjectiles) {
                addAsteroid(gameObjects, deltaTime, 0.1);
            }
            // adds a vertical wave of asteroids every five seconds
            if (seconds % 5 == 0) {
                addVerticalWave(gameObjects, deltaTime, 2);
            }
            // adds a horizontal wave of asteroids every three seconds
            if (seconds % 3 == 0) {
                addHorizontalWave(gameObjects, deltaTime, 2);
            }
            // a small break from asteroids with waves of power ups
        } else if (seconds > 25 && seconds < 30) {
            addPowerUpWave(gameObjects, deltaTime, 0.5);
        }
        // add power ups every ten seconds
        if (seconds % 10 == 0) {
            addHealthPowerUp(gameObjects, deltaTime, 1);
            addShieldPowerUp(gameObjects, deltaTime, 1);
        }
        // add a debuff every four seconds
        if (seconds % 4 == 0) {
            addSlowDebuff(gameObjects, deltaTime, 1);
        }
    }

    /**
     * Adds an asteroid to the list of game objects. Speed is increased with time
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown to prevent adding too frequently
     * @authors Viktor & Irja
     */
    private void addAsteroid(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        asteroidCooldown = asteroidCooldown - deltaTime;
        if (asteroidCooldown < 0) {
            Projectile asteroid = ProjectileFactory.createRandomizedAsteroid();
            asteroid.setSpeed(asteroid.getSpeed() + (seconds + minutes * 60) * 0.5);
            gameObjects.add(asteroid);
            asteroidCooldown = cooldown;
        }
    }

    /**
     * Adds a shield power up to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown to prevent adding too frequently
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
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown to prevent adding too frequently
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
     * Adds a speed debuff to the list of game objects.
     *
     * @param gameObjects the list of game objects in the game loop
     * @param deltaTime   length of a frame in the game loop
     * @param cooldown    an internal cooldown to prevent adding too frequently
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
     * @param cooldown    an internal cooldown to prevent adding too frequently
     * @authors Viktor & Irja
     */
    private void addPowerUpWave(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        powerUpWaveCooldown = powerUpWaveCooldown - deltaTime;
        if (powerUpWaveCooldown < 0) {
            for (int i = 0; i < 4; i++) {
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
     * @param cooldown    an internal cooldown to prevent adding too frequently
     * @authors Viktor & Irja
     */
    private void addVerticalWave(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        verticalWaveCooldown = verticalWaveCooldown - deltaTime;
        if (verticalWaveCooldown < 0) {
            for (int i = 0; i < 5; i++) {
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
     * @param cooldown    an internal cooldown to prevent adding too frequently
     * @authors Viktor & Irja
     */
    private void addHorizontalWave(List<AbstractGameObject> gameObjects, double deltaTime, double cooldown) {
        horizontalWaveCooldown = horizontalWaveCooldown - deltaTime;
        if (horizontalWaveCooldown < 0) {
            for (int i = 0; i < 6; i++) {
                gameObjects.add(ProjectileFactory.createAsteroid(200, 64, 64, 32 + (horizontalGap * 2 * i), -65, 0, 1));
            }
            horizontalWaveCooldown = cooldown;
        }
    }

    /**
     * removes offscreen projectiles
     *
     * @author Irja Vuorela
     */
    public void removeOffscreenProjectiles(List<AbstractGameObject> gameObjects) {
        for (AbstractGameObject g : gameObjects) {
            if (g instanceof Projectile) {
                if (((Projectile) g).isNotOnScreen()) {
                    gameObjects.remove(g);
                    break;
                }
            }
        }
    }
}