package Model;

import Interfaces.*;
import Model.Entities.AbstractGameObject;
import Model.Entities.HitBox;
import Model.Entities.Player.Spaceship;
import Model.Entities.Projectiles.LaserBeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks hitBoxes Rectangle2D for intersection (collision)
 * Calls class specific actOnCollision methods to execute on impact
 *
 * @author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandler implements IGameObjectObservable {

    private final List<IGameObjectObserver> gameObjectObservers = new ArrayList<>();
    List<AbstractGameObject> toBeRemoved = new ArrayList<>();

    public boolean checkCollision(AbstractGameObject g, AbstractGameObject a) {
        for (HitBox hitBox1 : g.getHitBoxes()) {
            for (HitBox hitBox2 : a.getHitBoxes()) {
                if ((hitBox1.getHitBox().intersects(hitBox2.getHitBox())) && a != g) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sends notifies to the objects that have collided
     * @param g first object that collided
     * @param a second object that collided
     */
    public void collide(AbstractGameObject g, AbstractGameObject a) {
        g.actOnCollision(a.toString(), a.getAmount());
        a.actOnCollision(g.toString(), g.getAmount());
    }

    /**
     * Handles the collision for all objects in a list
     * @param gameObjects The list that is to be checked for collisions
     */
    public void handleCollision(List<AbstractGameObject> gameObjects) {

        for (AbstractGameObject g : gameObjects) {

            for (AbstractGameObject a : gameObjects) {
                if (checkCollision(g, a) && !g.getCollided() && !a.getCollided()) {
                    if (a instanceof Spaceship) {
                        notifyGameObjectObservers(g.toString(), g.getAmount());
                    } else if (g instanceof Spaceship) {
                        notifyGameObjectObservers(a.toString(), a.getAmount());
                    } else if (g instanceof LaserBeam || a instanceof LaserBeam) {
                        notifyGameObjectObservers(a.toString(), a.getAmount());
                        notifyGameObjectObservers(g.toString(), g.getAmount());
                    }
                    collide(a, g);
                }
                if (a.getCollided()) {
                    toBeRemoved.add(a);
                }
            }
        }
        for (AbstractGameObject a : toBeRemoved) {
            gameObjects.remove(a);
        }
    }

    /**
     * @param obs the observer to be added to a list of observers
     */
    @Override
    public void addGameObjectObserver(IGameObjectObserver obs) {
        gameObjectObservers.add(obs);
    }

    /**
     * @param obs the observer to be removed from a list of observers
     */
    @Override
    public void removeGameObjectObserver(IGameObjectObserver obs) {
        gameObjectObservers.remove(obs);
    }

    /**
     * @param className the class type to notify observers with
     */
    @Override
    public void notifyGameObjectObservers(String className, int amount) {
        for (IGameObjectObserver obs : gameObjectObservers) {
            obs.actOnGameObjectEvent(className, amount);
        }
    }

    /**
     * @return the list of game object observers
     * @author Tobias Engblom
     */
    public List<IGameObjectObserver> getGameObjectObservers() {
        return gameObjectObservers;
    }
}
