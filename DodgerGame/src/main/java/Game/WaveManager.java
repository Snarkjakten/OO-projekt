package Game;

import Game.Entities.Projectiles.Projectile;
import Game.Entities.Projectiles.ProjectileFactory;
import Game.Movement.AbstractGameObject;

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


    private void calcuateTime(long time){
        long sec = time / 1000000000;
        this.seconds = sec % 60;
        this.minutes = sec / 60;
    }

    public void projectileSpawner(long time, List<AbstractGameObject> gameObjects){
        calcuateTime(time);
        removeOffscreenProjectiles(gameObjects);
        if(gameObjects.size() <= 13) {
            addHealthPowerUp(gameObjects);
            addShieldPowerUp(gameObjects);
            addEasyWave(gameObjects); //TODO: spawna bara en av dessa, hur?
            addHardWave(gameObjects);
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
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());
        }
    }

    private void addHardWave(List<AbstractGameObject> gameObjects) {
        if(seconds >= 21 || minutes > 0) {
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createSmallAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());
            gameObjects.add(ProjectileFactory.createMediumAsteroid());
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