package Model;

import Model.Entities.Projectiles.Projectile;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.Movement.AbstractGameObject;

import java.util.Random;

import java.util.List;

/**
 * @author Viktor Sundberg & Irja Vuorela
 */

public class WaveManager {

    private int spaceshipWidth = 64;
    private int spaceshipHeight = 64;
    //private GameWorld gameWorld = GameWorld.getInstance();

    private List<AbstractGameObject> easyWave;
    private List<AbstractGameObject> hardWave;
    private List<AbstractGameObject> powerUps;
    private long sec;
    private long seconds;
    private long minutes;
    private int maxNumProjectiles = 40;
    private double healthPowerUpCooldown = 1;
    private double shieldPowerUpCooldown = 1;
    private double waveCooldown = 0.5;

    private Random random = new Random();


    private void calcuateTime(long time) {
        this.sec = time / 1000000000;
        this.seconds = sec % 60;
        this.minutes = sec / 60;
    }

    public void projectileSpawner(long time, List<AbstractGameObject> gameObjects, double deltaTime) {
        calcuateTime(time);
        removeOffscreenProjectiles(gameObjects);
        if (/*gameObjects.size() <= 3 + seconds &&*/ gameObjects.size() <= maxNumProjectiles) {
            //addAsteroid(gameObjects);

            if (seconds < 5 && minutes < 1) {
                if (seconds % 1 == 0) {
                    addVerticalWave(gameObjects, random.nextInt(3), deltaTime);
                }
            } else if (seconds > 7 && seconds < 16 && minutes < 1) {
                if (seconds % 1 == 0) {
                    addHorizontalWave(gameObjects, random.nextInt(3), deltaTime);
                }
            }
           /* if (seconds % 2 == 1) {
                addVerticalWave(gameObjects, 64, deltaTime);
            }*/
            //addAsteroid(gameObjects);
        } else if (seconds > 23) {
            addAsteroid(gameObjects);

        }

        if (seconds % 10 == 0) {
            addHealthPowerUp(gameObjects, deltaTime);
            addShieldPowerUp(gameObjects, deltaTime);
        }

    }


    /*private void addLaserBeam(List<AbstractGameObject> gameObjects) {
        LaserBeam laserBeam = new LaserBeam();
        if(seconds % 20 == 0) {
            gameObjects.add(laserBeam);
        }
    }*/

    private void addAsteroid(List<AbstractGameObject> gameObjects) {
        Projectile asteroid = ProjectileFactory.createAsteroid();
        asteroid.setSpeed(asteroid.getSpeed() + seconds + minutes * 60);
        gameObjects.add(asteroid);
    }

    private void addShieldPowerUp(List<AbstractGameObject> gameObjects, double deltaTime) {
        shieldPowerUpCooldown = shieldPowerUpCooldown - deltaTime;
        if (shieldPowerUpCooldown < 0) {
            gameObjects.add(ProjectileFactory.createShieldPowerUp());
            shieldPowerUpCooldown = 1;
        }
    }

    private void addHealthPowerUp(List<AbstractGameObject> gameObjects, double deltaTime) {
        healthPowerUpCooldown = healthPowerUpCooldown - deltaTime;

        if (healthPowerUpCooldown < 0) {
            gameObjects.add(ProjectileFactory.createHealthPowerUp());
            healthPowerUpCooldown = 1;
        }
    }

    private void addEasyWave(List<AbstractGameObject> gameObjects) {
        if (seconds >= 0 && seconds <= 20 && minutes < 1) {
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
        }
    }

    private void addVerticalWave(List<AbstractGameObject> gameObjects, int shiftY, double deltaTime) {
        waveCooldown = waveCooldown - deltaTime;
        if (waveCooldown < 0) {
            for (int i = 0; i < 5; i++) {
                gameObjects.add(ProjectileFactory.createScriptedAsteroid(200, 64, 64, -65, shiftY * 32 + (spaceshipHeight * 2 * i), 1, 0));
            }
            waveCooldown = 0.5;
        }
    }

    private void addHorizontalWave(List<AbstractGameObject> gameObjects, int shiftX, double deltaTime) {
        waveCooldown = waveCooldown - deltaTime;
        if (waveCooldown < 0) {
            for (int i = 0; i < 5; i++) {
                gameObjects.add(ProjectileFactory.createScriptedAsteroid(200, 64, 64, shiftX * 32 + (spaceshipHeight * 2 * i), -65, 0, 1));
            }
            waveCooldown = 0.5;
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