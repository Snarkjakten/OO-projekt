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

    public void projectileSpawner(long time, List<AbstractGameObject> gameObjects){
        calcuateTime(time);
        removeOffscreenProjectiles(gameObjects);
        if(oldSeconds != seconds) {
            oldSeconds = seconds;
            if (gameObjects.size() <= 10 + seconds && gameObjects.size() <= maxNumProjectiles) {
            }
            addHealthPowerUp(gameObjects);
            addShieldPowerUp(gameObjects);
        }
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

    private void addHardWave(List<AbstractGameObject> gameObjects) {
        if(seconds >= 21 || minutes > 0) {
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
            gameObjects.add(ProjectileFactory.createAsteroid());
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