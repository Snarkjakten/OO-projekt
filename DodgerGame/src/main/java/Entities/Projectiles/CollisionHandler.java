package Entities.Projectiles;

import Movement.AbstractMovable;


public class CollisionHandler {

    public boolean checkCollision(AbstractMovable g, AbstractMovable a){
        return g.getHitbox().intersects(a.getHitbox()) && a != g;
    }

    public void collide(AbstractMovable g, AbstractMovable a) {
        g.actOnCollision(a.getClass());
        a.actOnCollision(g.getClass());

    }
}
