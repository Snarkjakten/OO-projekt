package Entities.Projectiles;

import Entities.Player.Player;
import Movement.AbstractMovable;

/**
 * Checks hitboxes Rectangle2D for intersection (collision)
 * Calls class specific actOnCollision methods to execute on impact
 * @Author Viktor Sundberg (viktor.sundberg@icloud.com)
 */

public class CollisionHandler {

    public boolean checkCollision(AbstractMovable g, AbstractMovable a){
        return g.getHitbox().intersects(a.getHitbox()) && a != g;
    }

    public void collide(AbstractMovable g, AbstractMovable a) {
        g.actOnCollision(a);
        a.actOnCollision(g);


    }
}
