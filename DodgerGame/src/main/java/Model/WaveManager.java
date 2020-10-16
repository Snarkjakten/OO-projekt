package Model;

import Model.Entities.Projectiles.Projectile;
import Model.Entities.Projectiles.ProjectileFactory;
import Model.Movement.AbstractGameObject;

import java.util.List;

/**
 * @author Viktor Sundberg & Irja Vuorela
 *
 */

public class WaveManager {

    private int spaceshipWidth = 64;
    private int spaceshipHeight = 64;
    //private GameWorld gameWorld = GameWorld.getInstance();

    private List<AbstractGameObject> easyWave;
    private List<AbstractGameObject> hardWave;
    private List<AbstractGameObject> powerUps;
    private long seconds;
    private long minutes;
    private long oldSeconds;
    private int maxNumProjectiles = 30;


    private void calcuateTime(long time){
        long sec = time / 1000000000;
        this.seconds = sec % 60;
        this.minutes = sec / 60;
    }

    public void projectileSpawner(long time, List<AbstractGameObject> gameObjects) {
        calcuateTime(time);
        removeOffscreenProjectiles(gameObjects);
        if (gameObjects.size() <= 3 + seconds && gameObjects.size() <= maxNumProjectiles) {
            if (oldSeconds != seconds) {
                oldSeconds = seconds;
                addHealthPowerUp(gameObjects);
                addShieldPowerUp(gameObjects);
                if (seconds <= 20) {
                    addVerticalWave(gameObjects, 0, 0);
                    addVerticalWave(gameObjects, 64, 64);
                } else if (seconds > 23) {
                    addAsteroid(gameObjects);
                }
            }
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

    private void addShieldPowerUp(List<AbstractGameObject> gameObjects) {
        if (seconds % 10 == 0) {
            gameObjects.add(ProjectileFactory.createShieldPowerUp());
        }
    }

    private void addHealthPowerUp(List<AbstractGameObject> gameObjects) {
        if (seconds % 10 == 0) {
            gameObjects.add(ProjectileFactory.createHealthPowerUp());
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

    private void addVerticalWave(List<AbstractGameObject> gameObjects, int shiftY, int shiftX) { //TODO: separera på x-axel.
        for (int i = 0; i < 5; i++) {
            gameObjects.add(ProjectileFactory.createScriptedAsteroid(200, 64, 64, -69 + shiftX, 50 + shiftY + (spaceshipHeight * 2) * i, 1, 0));

            //gameObjects.add(ProjectileFactory.createScriptedAsteroid(200, 64, 64, -50 + 64, 64 + (spaceshipHeight * 2) * i, 1, 0));
        }
    }

    /**
     * removes offscreen projectiles
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