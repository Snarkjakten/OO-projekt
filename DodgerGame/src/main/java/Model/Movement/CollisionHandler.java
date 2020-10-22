package Model.Movement;

import Interfaces.*;
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

    public void collide(AbstractGameObject g, AbstractGameObject a) {
        g.actOnCollision(a);
        a.actOnCollision(g);
    }

    public void handleCollision(List<AbstractGameObject> gameObjects) {

        for (AbstractGameObject gameObject : gameObjects) {

            for (AbstractGameObject a : gameObjects) {
                if (checkCollision(gameObject, a) && !gameObject.getCollided() && !a.getCollided()) {
                    if (a instanceof Spaceship) {
                        notifyGameObjectObservers(gameObject.getClass());
                        a.actOnCollision(gameObject);
                    } else if (gameObject instanceof Spaceship) {
                        notifyGameObjectObservers(a.getClass());
                        gameObject.actOnCollision(a);
                    } else if (gameObject instanceof LaserBeam || a instanceof LaserBeam) {
                        notifyGameObjectObservers(a.getClass());
                        notifyGameObjectObservers(gameObject.getClass());
                        collide(a, gameObject);
                    } else {
                        collide(a, gameObject);
                    }
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
     * @param c the class type to notify observers with
     */
    @Override
    public void notifyGameObjectObservers(Class c) {
        for (IGameObjectObserver obs : gameObjectObservers) {
            obs.actOnGameObjectEvent(c);
        }
    }
}
