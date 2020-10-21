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

    private List<IGameObjectObserver> gameObjectObservers = new ArrayList<>();

    public boolean checkCollision(AbstractGameObject g, AbstractGameObject a) {
        for (HitBox hitBox1 : g.getHitBoxes())
            for (HitBox hitBox2 : a.getHitBoxes())
                return hitBox1.getHitBox().intersects(hitBox2.getHitBox()) && a != g;
        return false;
    }

    public void collide(AbstractGameObject g, AbstractGameObject a) {
        g.actOnCollision(a);
        a.actOnCollision(g);
    }

    public void handleCollision(List<AbstractGameObject> gameObjects) {
        List<AbstractGameObject> toBeRemoved;
        toBeRemoved = new ArrayList<>();

        for (AbstractGameObject gameObject : gameObjects) {

            for (AbstractGameObject a : gameObjects) {
                if (checkCollision(gameObject, a) && !gameObject.getCollided() && !a.getCollided()) {
                    if (a instanceof Spaceship) {
                        notifyGameObjectObservers(gameObject.getClass());
                        ((Spaceship) a).actOnCollisionEvent(gameObject);
                        if (!(gameObject instanceof LaserBeam)) {
                            toBeRemoved.add(gameObject);
                        }
                    } else if (gameObject instanceof Spaceship) {
                        notifyGameObjectObservers(a.getClass());
                        ((Spaceship) gameObject).actOnCollisionEvent(a);
                        if (!(a instanceof LaserBeam)) {
                            toBeRemoved.add(a);
                        }
                    }
                    collide(a, gameObject);
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
