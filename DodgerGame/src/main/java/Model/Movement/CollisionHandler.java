package Model.Movement;

import Interfaces.ICollisionObservable;
import Interfaces.ICollisionObserver;
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

public class CollisionHandler implements ICollisionObservable {

    private List<ICollisionObserver> collisionObservers;

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
                        //notifySoundObservers(gameObject.getClass());
                        //notifyCollisionObservers(gameObject);
                        ((Spaceship) a).actOnCollisionEvent(gameObject);
                        if (!(gameObject instanceof LaserBeam)) {
                            toBeRemoved.add(gameObject);
                        }
                    } else if (gameObject instanceof Spaceship) {
                        //notifySoundObservers(a.getClass());
                        //notifyCollisionObservers(a);
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

    @Override
    public void notifyCollisionObservers(AbstractGameObject gameObject) {

    }

    @Override
    public void addCollisionObserver(ICollisionObserver obs) {

    }

    @Override
    public void removeCollisionObserver(ICollisionObserver obs) {

    }
}
